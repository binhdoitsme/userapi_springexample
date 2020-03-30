package com.example.userapi.domain.usecase;

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
        User retrievedUser = userRepository.findByUsername(userDto.getUsername());
        if (retrievedUser == null) {
            System.out.println(UserMapper.unauthorizedUser());
            return UserMapper.unauthorizedUser();
        }
        
        boolean validated = UserValidation.validatePassword(userDto.getPassword(),
                                retrievedUser.getPassword(), retrievedUser.getGeneratedSalt());
        if (validated) {
            return UserMapper.toUserToken(retrievedUser);
        } else {
            return UserMapper.unauthorizedUser();
        }
    }
}
