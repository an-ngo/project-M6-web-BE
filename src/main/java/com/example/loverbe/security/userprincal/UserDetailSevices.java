package com.example.loverbe.security.userprincal;

import com.example.loverbe.model.entity.User;
import com.example.loverbe.repository.IUserRepository;
import com.example.loverbe.service.Implement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailSevices implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    UserService userService;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()){
            throw new UsernameNotFoundException(username);
        }
        return UserPrincipal.build(userOptional.get());
    }
    // ham lay ra user dang dang nhap hien tai
    public User getCurrentUser(){
        Optional<User> user;
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        }else {
            username = principal.toString();
        }
        if (userRepository.existsByUsername(username)){
            user = userService.findByUsername(username);
        }else {
            user = Optional.of(new User());
            user.get().setUsername("Anonymous");
        }
        return user.get();
    }
}
