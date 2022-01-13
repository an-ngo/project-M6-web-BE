package com.example.loverbe.model.entity.user.nccdv;

import com.example.loverbe.model.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ServiceByProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @JsonBackReference
    @ManyToOne(targetEntity = User.class)
    private User user;

    public ServiceByProvider(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
