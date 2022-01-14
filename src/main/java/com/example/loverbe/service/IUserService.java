package com.example.loverbe.service;

import com.example.loverbe.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>{
    User save(User user);
    Optional<User> findByUsername(String name);
    User findByEmail(String email);
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua
    Page<User> findAllUser(Pageable pageable);
    Page<User> findAllServiceProvider(Pageable pageable);
    Iterable<User> findTop6HotService();
    Iterable<User> top6ProviderHot();
    Iterable<User> find4MenUserTopCountTime();
    Iterable<User> find8MaleUserTopCountTime();
    Iterable<User> find12TopJoinDate();
    Iterable<User> find6TopViewPage();
    Page<User> searchUserProvider(String gender, Long beforeYear, Long afterYear, String country, String city, Pageable pageable);
}
