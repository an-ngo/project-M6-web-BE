package com.example.loverbe.service;

import com.example.loverbe.model.enums.EnumRoleName;
import com.example.loverbe.model.entity.user.Role;

import java.util.Optional;

public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByName(EnumRoleName name);

//    void remove(Long id);
//
//    Optional<Role> findById(Long id);
//
//    Role save(Role role);

}
