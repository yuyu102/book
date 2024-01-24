package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // User을 반환하는 함수
    Optional<User> findByName(String name);
    // 반드시 findByName으로 작성해야함.
    // By 뒤에 붙는 필드 이름은 SELECT 문의 WHERE에 해당함.
    // SELECT * FROM user WHERE name = ?;
}
