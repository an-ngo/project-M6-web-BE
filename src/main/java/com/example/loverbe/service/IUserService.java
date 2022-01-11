package com.example.loverbe.service;

import com.example.loverbe.model.entity.user.User;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>{
    User save(User user);
    Optional<User> findByUsername(String name);
    User findByEmail(String email);
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua

}
