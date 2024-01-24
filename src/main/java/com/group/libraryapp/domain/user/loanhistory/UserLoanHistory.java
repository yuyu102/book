package com.group.libraryapp.domain.user.loanhistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private long userId;

    private String bookName;

    private boolean isReturn; // boolean을 사용하면 DB의 tinyint에 맵핑, DB에 0이 들어가면 true, 1이 들어가면 false

    // 기본 생성자가 필요함
    protected UserLoanHistory() {

    }
    public UserLoanHistory(long userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn = false;
    }

    // 대출 기록을 반납처리
    // isReturn을 true로 만들어줘야함
    public void doReturn() {
        this.isReturn = true;
    }
}
