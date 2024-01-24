package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 255, name = "name") // name은 name이라서 생략 가능.
    private String name;

    protected  Book() {

    }

    public Book(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }
        this.name = name;
    } // id는 자동 생성이니까 name 생성자

    public String getName() {
        return name;
    }
}
