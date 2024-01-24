package com.group.libraryapp.domain.user.loanhistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    // SELECT * FROM user_loan_history where book_name = ? and is_return = ?
    boolean existsByBookNameAndIsReturn(String name, boolean isReturn);

//    Optional<UserLoanHistory> findByUserIdAndBookName(long userId, String bookName);
    // 이제 사용 안함. user, userloanrepository 둘의 로직이 간단해짐
}
