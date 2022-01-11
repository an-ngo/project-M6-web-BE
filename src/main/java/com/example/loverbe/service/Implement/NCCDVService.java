package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.user.NCCDV;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.service.INCCDVService;

import java.util.Optional;

public class NCCDVService implements INCCDVService {
    @Override
    public Iterable<NCCDV> findAll() {
        return null;
    }

    @Override
    public Optional<NCCDV> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public NCCDV save(NCCDV nccdv) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Optional<NCCDV> findByUser(User user) {
        return Optional.empty();
    }
}
