package com.example.loverbe.repository;

import com.example.loverbe.model.entity.Role;
import com.example.loverbe.model.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}
