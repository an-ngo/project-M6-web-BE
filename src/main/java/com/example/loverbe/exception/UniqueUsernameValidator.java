package com.example.loverbe.exception;

import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.repository.IUserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {
    public UniqueUsernameValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private IUserRepository userRepository;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        Optional<User> user = userRepository.findByUsername(username);
        return username != null && user == null;
    }
}
