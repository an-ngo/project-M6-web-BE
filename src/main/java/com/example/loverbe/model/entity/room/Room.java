package com.example.loverbe.model.entity.room;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Message.class,mappedBy = "room", cascade = CascadeType.REMOVE)
    @JsonBackReference
    @ToString.Exclude
    private List<Message> messageList;

//    public Room(String name) {
//        this.name = name;
//    }
}
