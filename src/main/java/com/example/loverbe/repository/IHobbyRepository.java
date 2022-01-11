package com.example.loverbe.repository;

import com.example.loverbe.model.entity.user.nccdv.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHobbyRepository extends JpaRepository<Hobby, Long> {
}
