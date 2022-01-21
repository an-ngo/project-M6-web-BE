package com.example.loverbe.repository;

import com.example.loverbe.model.entity.user.nccdv.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "select i.id, i.link_image from image i join nccdv n on n.id = i.nccdv_id join users u on n.user_id = u.id where user_id = ?;", nativeQuery = true)
    Iterable<Image> findAllByUserId(Long id);
}
