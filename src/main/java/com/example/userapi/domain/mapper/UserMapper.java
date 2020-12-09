package com.example.userapi.domain.mapper;

import com.example.userapi.domain.dto.UserToken;
import com.example.userapi.domain.model.User;
import com.example.userapi.helper.UserTokenGenerator;
import com.example.userapi.helper.UserValidation;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.userapi.domain.dto.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User u) {
        return new UserDto(u.getUsername(), u.getPassword(), u.getDisplayName(), u.getEmail());
    }

    public static User toNewUser(UserDto userDto) {
        String salt = BCrypt.gensalt();
        String hashedPassword = UserValidation.encodePassword(userDto.getPassword(), salt);
        return new User(userDto.getUsername(), hashedPassword,
                        userDto.getDisplayName(), userDto.getEmail(), salt);
    }

    public static UserToken toUserToken(User u) {
        String token = UserTokenGenerator.generateJWT(Integer.toString(u.getId()), u.getUsername());
        return new UserToken(u, token);
    }

    public static UserToken unauthorizedUser() {
        UserToken tk = new UserToken();
        tk.setAuthToken("Unauthorized!");
        return tk;
    }
}
