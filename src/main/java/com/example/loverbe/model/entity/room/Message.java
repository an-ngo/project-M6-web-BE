package com.example.loverbe.model.entity.room;

import com.example.loverbe.model.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    private String timeSend;

    private String message;
}
