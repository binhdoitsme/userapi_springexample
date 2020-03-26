package com.example.userapi.domain.usecase;

import java.util.List;

import com.example.userapi.base.RequestHandler;
import com.example.userapi.domain.dto.UserDto;
import com.example.userapi.domain.dto.UserToken;
import com.example.userapi.domain.mapper.UserMapper;
import com.example.userapi.domain.model.User;
import com.example.userapi.domain.repository.UserRepository;
import com.example.userapi.helper.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase implements RequestHandler<UserDto, UserToken> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserToken handle(UserDto userDto) {
        List<User> retrievedUser = userRepository.findByUsername(userDto.getUsername());
        if (retrievedUser.size() == 0) {
            System.out.println(UserMapper.unauthorizedUser());
            return UserMapper.unauthorizedUser();
        }
        User u = retrievedUser.get(0);
        boolean validated = UserValidation.validatePassword(userDto.getPassword(),
                                            u.getPassword(), u.getGeneratedSalt());
        if (validated) {
            return UserMapper.toUserToken(u);
        } else {
            return UserMapper.unauthorizedUser();
        }
    }
}
