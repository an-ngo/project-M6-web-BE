package com.example.loverbe.service;

import com.example.loverbe.model.entity.room.Room;

import java.util.Optional;

public interface IRoomService extends IGeneralService<Room>{
    Iterable<Room> findAllByUserId(Long id);
}
