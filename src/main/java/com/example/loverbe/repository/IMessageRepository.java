package com.example.loverbe.repository;

import com.example.loverbe.model.entity.room.Message;
import com.example.loverbe.model.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {
    Iterable<Message> findAllByRoom(Room room);
}

