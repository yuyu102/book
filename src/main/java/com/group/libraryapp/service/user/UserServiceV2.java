package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 아래에 있는 함수가 시작될 때 start transaction;을 해준다 (트랜젝션을 시작!)
    // 함수가 예외 없이 잘 끝났다면 commit
    // 혹시라도 문제가 있으면 rollback
    @Transactional  // org.springframework를 선택해야함 // 이렇게하면 트랜젝션 간단 사용.
    public void saveUser(UserCreateRequest request) {
        User u = userRepository.save(new User(request.getName(), request.getAge()));
        // u.getid(); // 여기있는 값이 1,2,3 이렇게 들어있음
        throw new IllegalArgumentException(); // 오류나면 예외로 ROLLBACK; 일어남
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
//        List<User> users = userRepository.findAll(); //findAll을 쓰면 자동으로 sql을 날려서 해당 테이블에 있는 모든 데이터 가져옴.
//        return users.stream() // 이 두 문장을 한문장으로 만듦
        return userRepository.findAll().stream()
//          .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge())) // 더 깔끔하게 정리.
            .map(UserResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        // request.getId => SELECT * FROM user WHERE id = ?;
        // 결과 => Optional<User>
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
//        userRepository.save(user); // Transactional을 사용하여 영속성 컨텍스트라 SAVE 하지 않아도 변경 감지해서 자동으로 저장됨.
    }

    @Transactional
    public void deleteUser(String name) {
        // SELECT * FROM user WHERE name = ?
        User user = userRepository.findByName(name)
                   .orElseThrow(IllegalArgumentException::new);

//        if (user == null) {
//            throw new IllegalArgumentException();
         // user이 존재하면 user 객체를, 존재하지 않는다면 null을.

        userRepository.delete(user);
    }

}
