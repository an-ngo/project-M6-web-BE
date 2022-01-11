package com.example.loverbe.model.entity.user.nccdv;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ServiceByNCCDV {
    @Id
    private Long id;

    private String name;

    private Boolean status;

    private Double price;


}
