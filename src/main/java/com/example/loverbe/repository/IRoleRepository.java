package com.example.loverbe.repository;

import com.example.loverbe.model.entity.user.Role;
import com.example.loverbe.enums.EnumRoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRoleName name);
}
