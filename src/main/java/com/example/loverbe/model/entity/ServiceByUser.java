package com.example.loverbe.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ServiceByUser {
    @Id
    private Long id;

    private String name;

    private Boolean status;

    private Double price;


}
