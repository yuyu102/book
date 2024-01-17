package com.group.libraryapp.repositoty.book;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class BookMysqlRepository implements BookRepository{

    @Override
    public void saveBook() {

    }
}

// Primary는 호출할 때 우선시 된다. 우선권을 조절하는 어노테이션
