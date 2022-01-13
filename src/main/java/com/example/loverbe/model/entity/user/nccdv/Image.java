package com.example.loverbe.model.entity.user.nccdv;

import com.example.loverbe.model.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String link_image;

    @JsonBackReference
    @ManyToOne(targetEntity = User.class)
    private User user;


    public Image(String link_image, User user) {
        this.link_image = link_image;
        this.user = user;
    }
}
