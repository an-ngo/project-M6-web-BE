package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.room.Message;
import com.example.loverbe.model.entity.room.Room;
import com.example.loverbe.repository.IMessageRepository;
import com.example.loverbe.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MessageService implements IMessageService {
    @Autowired
    IMessageRepository messageRepository;

    @Override
    public Iterable<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void remove(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Iterable<Message> findAllByRoom(Room room) {
        return messageRepository.findAllByRoom(room);
    }
}
