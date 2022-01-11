package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.user.nccdv.Hobby;
import com.example.loverbe.repository.IHobbyRepository;
import com.example.loverbe.service.IHobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HobbyService implements IHobbyService {
    @Autowired
    private IHobbyRepository hobbyRepository;
    @Override
    public Iterable<Hobby> findAll() {
        return hobbyRepository.findAll();
    }

    @Override
    public Optional<Hobby> findById(Long id) {
        return hobbyRepository.findById(id);
    }

    @Override
    public Hobby save(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    @Override
    public void remove(Long id) {
        hobbyRepository.deleteById(id);
    }
}
