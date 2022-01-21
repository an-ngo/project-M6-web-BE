package com.example.loverbe.model.entity.room;

import com.example.loverbe.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    private User user;
    @ManyToOne(targetEntity = Room.class)
    private Room room;

    private LocalDate timeSend;

    private String message;
}
