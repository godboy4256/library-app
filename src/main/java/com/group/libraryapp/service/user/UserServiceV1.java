package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceV1 {
    final private UserJdbcRepository userRepository;

    public UserServiceV1(UserJdbcRepository UserJdbcRepository){
        this.userRepository = UserJdbcRepository;
    }

    public void userUpdate(UserUpdateRequest request){
        if(userRepository.userIsNotExist(request.getId())){
            throw new IllegalArgumentException();
        }
        userRepository.userUpdate(request.getName(),request.getId());
    }
}
