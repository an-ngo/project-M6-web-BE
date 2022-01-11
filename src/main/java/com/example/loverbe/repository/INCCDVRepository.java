package com.example.loverbe.repository;

import com.example.loverbe.model.entity.user.NCCDV;
import com.example.loverbe.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INCCDVRepository extends JpaRepository<NCCDV, Long> {
    Optional<NCCDV> findByUser(User user);
}
