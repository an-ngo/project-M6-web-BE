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
public class ServiceByNCCDV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean status;

    private Double price;

    @JsonBackReference
    @ManyToMany(targetEntity = User.class)
    private List<User> users;
}
