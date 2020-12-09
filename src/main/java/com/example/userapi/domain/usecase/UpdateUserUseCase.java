package com.example.userapi.domain.usecase;

import java.util.List;

import com.example.userapi.base.Composite;
import com.example.userapi.base.RequestHandler;
import com.example.userapi.domain.dto.UserDto;
import com.example.userapi.domain.repository.UserRepository;
import com.example.userapi.exception.InvalidFieldInputException;
import com.example.userapi.exception.UnexpectedException;
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
            String oldUsername = userData.getTwo();
            if (UserValidation.validateUserInput(newUserData)) {
                updateUser(newUserData, oldUsername);
                return new Boolean(true);
            } else {
                List<String> invalidFields = UserValidation.getInvalidFields(newUserData);
                return new InvalidFieldInputException(invalidFields);
            }
        } catch (Exception e) {
            return new UnexpectedException();
        }
    }

    private void updateUser(UserDto newUserData, String oldUsername) {
        String salt = userRepository.findByUsername(oldUsername).getGeneratedSalt();
        String newPassword = UserValidation.encodePassword(newUserData.getPassword(), salt);
        userRepository.updateUser(newUserData.getUsername(), newPassword, newUserData.getDisplayName(),
                newUserData.getEmail(), oldUsername);
    }
}
