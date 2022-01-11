package com.example.loverbe.model.entity.user.nccdv;

import com.example.loverbe.model.entity.user.NCCDV;
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
    @ManyToOne(targetEntity = NCCDV.class)
    private NCCDV nccdv;
}
