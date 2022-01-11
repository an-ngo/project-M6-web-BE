package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.room.Room;
import com.example.loverbe.repository.IRoomRepository;
import com.example.loverbe.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService implements IRoomService {
    @Autowired private IRoomRepository roomRepository;
    private Long id;

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void remove(Long id) {
        roomRepository.deleteById(id);
    }
}
