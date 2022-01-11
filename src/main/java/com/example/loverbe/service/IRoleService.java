package com.example.loverbe.service;

import com.example.loverbe.model.entity.Role;
import com.example.loverbe.model.entity.RoleName;

import java.util.Optional;

public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByName(RoleName name);

//    void remove(Long id);
//
//    Optional<Role> findById(Long id);
//
//    Role save(Role role);

}
