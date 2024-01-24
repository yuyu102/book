package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    // manytoone 내가 다수이고 니가 하나라는 뜻. 대출기록은 여러개, 소유자는 1명.
    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn; // boolean을 사용하면 DB의 tinyint에 맵핑, DB에 0이 들어가면 true, 1이 들어가면 false

    // 기본 생성자가 필요함
    protected UserLoanHistory() {

    }
    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    // 대출 기록을 반납처리
    // isReturn을 true로 만들어줘야함
    public void doReturn() {
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
