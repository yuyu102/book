package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest request) {
        User u = userRepository.save(new User(request.getName(), request.getAge()));
        // u.getid(); // 여기있는 값이 1,2,3 이렇게 들어있음
    }

    public List<UserResponse> getUsers() {
//        List<User> users = userRepository.findAll(); //findAll을 쓰면 자동으로 sql을 날려서 해당 테이블에 있는 모든 데이터 가져옴.
//        return users.stream() // 이 두 문장을 한문장으로 만듦
        return userRepository.findAll().stream()
//          .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge())) // 더 깔끔하게 정리.
            .map(UserResponse::new)
            .collect(Collectors.toList());
    }

    public void updateUser(UserUpdateRequest request) {
        // request.getId => SELECT * FROM user WHERE id = ?;
        // 결과 => Optional<User>
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
        userRepository.save(user);
    }
}
