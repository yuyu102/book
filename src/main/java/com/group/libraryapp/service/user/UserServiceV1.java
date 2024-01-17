package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repositoty.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Repository가 아닌 Service
@Service
public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;
    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }
    //    public UserService(JdbcTemplate jdbcTemplate) {
//        userRepository = new UserRepository(jdbcTemplate);
//    } // 이 코드가 위 코드로 바뀜

    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUsers();
    }
    public void updateUser(UserUpdateRequest request) {
        if (userJdbcRepository.isUesrNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }
      userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if (userJdbcRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }
        // deleteuser는 userRepository에서 name 유저가 존재하지 않으면 예외를 던진다.

        userJdbcRepository.deleteUser(name);
    }
    // service에서는바로 호출
}
