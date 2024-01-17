package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity
public class User {

    @Id //primary key로 간주
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key는 자동생성 됨.
    private Long id = null;
    @Column(nullable = false, length = 20, name = "name") // name varchar(20) // 객체 name과 테이블의 name을 맵핑함. // name 부분이 같으니까 생략가능
    private String name;
    private Integer age;

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
}
