package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    //        = new BookMysqlRepository();
    private final UserLoanHistoryRepository userLoanHistoryRepository;

    private final UserRepository userRepository;

    public BookService(
            BookRepository bookRepository,
            UserLoanHistoryRepository userLoanHistoryRepository,
            UserRepository userRepository
    ) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }
// 생성자를 받음

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName())); // repository를 호출해서 새로운 book을 저장
    }

    @Transactional
    public void loanBook(BookLoanRequest request) { //loanBook 대출된 책
        // 1. 책 정보를 가져온다. // 책 정보가 있다면 들고옴
        Book book = bookRepository.findByName(request.getBookName())
            .orElseThrow(IllegalArgumentException::new);

        // 2. 대출기록 정보를 확인해서 대출중인지 확인합니다.
        // 3. 만약에 확인했는데 대출 중이라면 예외를 발생시킨다.
        // exist~를 호출했을때 책 이름과 대여중을 준다면, 전체 결과면 true면 대여중 책 존재, false는 대여중 책 없음
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("진작 대출되어 있는 책입니다"); //있는 경우
        }

        // 4. 유저 정보를 가져온다.

        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);


        // 5. 유저 정보와 책 정보를 기반으로 UserLoanHistory를 저장 // UserLoanHistory는 무조건 false니까 정리
        userLoanHistoryRepository.save(new UserLoanHistory(user.getId(), book.getName()));

    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        history.doReturn();
      //  userLoanHistoryRepository.save(history); // 사용 안해도 됨.
        // Transactional을 하였기에 영속성이라 감지기능이 있기에 변경을 감지하여 자동으로 업뎃해줌.



    }
}
