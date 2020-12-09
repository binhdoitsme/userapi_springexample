package com.example.userapi.domain.usecase;

import java.util.List;

import com.example.userapi.base.RequestHandler;
import com.example.userapi.domain.dto.UserDto;
import com.example.userapi.domain.mapper.UserMapper;
import com.example.userapi.domain.model.User;
import com.example.userapi.domain.repository.UserRepository;
import com.example.userapi.exception.DuplicateUserException;
import com.example.userapi.exception.InvalidFieldInputException;
import com.example.userapi.helper.UserValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase implements RequestHandler<UserDto, Object> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Object handle(UserDto userDto) {
        if (!UserValidation.validateUserInput(userDto)) {
            List<String> invalidFields = UserValidation.getInvalidFields(userDto);
            return new InvalidFieldInputException(invalidFields);
        }
        User createdUser = UserMapper.toNewUser(userDto);
        try {
            userRepository.saveAndFlush(createdUser);
            return new Boolean(true);
        } catch (Exception e) {
            return getSuitableOutputException(e);
        }
    }

    private Throwable getSuitableOutputException(Exception e) {
        System.out.println(e.getClass());
        if (e instanceof DataIntegrityViolationException) {
            return new DuplicateUserException();
        }
        return e;
    }
}
