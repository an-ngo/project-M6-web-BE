package com.example.loverbe.service;

import com.example.loverbe.model.entity.room.Room;

import java.util.Optional;

public interface IRoomService extends IGeneralService<Room>{

    Optional<Room> findById(Long id);

    Iterable<Room> findAll();

    Room save(Room room);

    void remove(Long id);

}
