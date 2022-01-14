package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.repository.IUserRepository;
import com.example.loverbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllServiceProvider(Pageable pageable) {
        return userRepository.findAllServiceProvider(pageable);
    }

    @Override
    public Iterable<User> findTop6HotService() {
        return userRepository.findTop6HotService();
    }

    @Override
    public Iterable<User> top6ProviderHot() {
        return userRepository.top6ProviderHot();
    }

    @Override
    public Iterable<User> find4MenUserTopCountTime() {
        return userRepository.find4MenUserTopCountTime();
    }

    @Override
    public Iterable<User> find8MaleUserTopCountTime() {
        return userRepository.find8MaleUserTopCountTime();
    }

    @Override
    public Iterable<User> find12TopJoinDate() {
        return userRepository.find12TopJoinDate();
    }

    @Override
    public Iterable<User> find6TopViewPage() {
        return userRepository.find6TopViewPage();
    }

    @Override
    public Page<User> searchUserProvider(String gender, Long beforeYear, Long afterYear, String country, String city, Pageable pageable) {
        return userRepository.searchUserProvider(gender, beforeYear, afterYear, country, city, pageable);
    }
}
