package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User { // 테이블과 맵핑된 개체

    @Id //primary key로 간주
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key는 자동생성 됨.
    private Long id = null;
    @Column(nullable = false, length = 20) // name varchar(20) // 객체 name과 테이블의 name을 맵핑함. // name 부분이 같으니까 생략가능
    private String name;
    private Integer age;

    // 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // UserLoanHistory와 반대로 onetomany, 또한 연관관계의 주인이 아닌 user에서 mapped해줘야함
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User() {
        // JPA를 사용하기 위해서는 기본 생성자가 필요!
    }


    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    } // domain 계층에 비지니스 로직이 들어감.

    // user 이랑 userloanhistory가 협업해서 들어오는 책이름과 같은 걸 반납하는 로직
    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName)) // 충족되는 것만 필터링 됨. 뒤에 조건이 충족
                .findFirst()
                .orElseThrow(IllegalArgumentException::new); // 예외를 둔다
        targetHistory.doReturn(); // 반납처리
    }
}
