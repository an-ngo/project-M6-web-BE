package com.example.loverbe.model.entity.room;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SseEmitterbyID {

    @Id
    private Long id;



}
