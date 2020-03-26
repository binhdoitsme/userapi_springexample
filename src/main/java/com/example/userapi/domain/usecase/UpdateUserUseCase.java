package com.example.userapi.domain.usecase;

import java.util.List;

import com.example.userapi.base.Composite;
import com.example.userapi.base.RequestHandler;
import com.example.userapi.domain.dto.UserDto;
import com.example.userapi.domain.repository.UserRepository;
import com.example.userapi.exception.InvalidFieldInputException;
import com.example.userapi.helper.UserValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase implements RequestHandler<Composite<UserDto, String>, Object> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Object handle(Composite<UserDto, String> userData) {
        try {
            UserDto newUserData = userData.getOne();
            if (UserValidation.validateUserInput(newUserData)) {
                String oldUsername = userData.getTwo();
                String salt = userRepository.findByUsername(oldUsername).get(0).getGeneratedSalt();
                String newPassword = UserValidation.encodePassword(newUserData.getPassword(), salt);
                userRepository.updateUser(newUserData.getUsername(), newPassword, newUserData.getDisplayName(),
                        newUserData.getEmail(), oldUsername);
                return new Boolean(true);
            } else {
                List<String> invalidFields = UserValidation.getInvalidFields(newUserData);
                return new InvalidFieldInputException(invalidFields);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
