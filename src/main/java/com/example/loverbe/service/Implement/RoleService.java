package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.Role;
import com.example.loverbe.model.entity.enums.EnumRoleName;
import com.example.loverbe.repository.IRoleRepository;
import com.example.loverbe.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(EnumRoleName name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

}
