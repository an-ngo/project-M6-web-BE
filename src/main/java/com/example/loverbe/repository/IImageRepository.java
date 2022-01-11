package com.example.loverbe.repository;

import com.example.loverbe.model.entity.user.nccdv.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {

}