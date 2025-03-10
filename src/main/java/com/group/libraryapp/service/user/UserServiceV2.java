package com.group.libraryapp.service.user;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;
    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. 어노테이션을 붙이면 트랜잭션이 시작된다. ( start transaction; )
    // 2. 문제 없이 작업을 끝내면 데이터베이스를 확정한다. ( commit; )
    // 3. 하나라도 문제가 생기면 작업 중이던 모든 것을 되돌린다. ( rollBack; )

    @Transactional
    public void saveUser(UserCreateRequest request){
       userRepository.save(new User(request.getName(), request.getAge()));
    }


    @Transactional(readOnly = true)
    public List<UserResponse> getUser(){
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge())).collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request){
        User us =  userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException:: new);
        // findById 를 사용하면 id 와 비교해 해당 항목을 찾아올수 있고, 없으면 체인 메소드를 통해 에러를 던질수 있다.
        us.updateName(request.getName());
        // 유저 변경 -> 영속성 컨텍스트가 감지

       // userRepository.save(us);
        // 변경을 감지하면 업데이트 안해도 된다.
    }

    @Transactional
    public void deleteUser(@RequestParam String name){
      User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
      if(user == null){
          throw new IllegalArgumentException();
      }
      userRepository.delete(user);
    }
}
