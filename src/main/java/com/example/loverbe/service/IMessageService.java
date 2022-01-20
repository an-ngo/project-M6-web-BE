package com.example.loverbe.service;

import com.example.loverbe.model.entity.room.Message;
import com.example.loverbe.model.entity.room.Room;

public interface IMessageService extends IGeneralService<Message>{
    Iterable<Message> findAllByRoom(Room room);
}
