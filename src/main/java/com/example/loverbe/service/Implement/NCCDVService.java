package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.user.NCCDV;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.repository.INCCDVRepository;
import com.example.loverbe.service.INCCDVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class NCCDVService implements INCCDVService {
    @Autowired
    private INCCDVRepository nccdvRepository;
    @Override
    public Iterable<NCCDV> findAll() {
        return nccdvRepository.findAll();
    }

    @Override
    public Optional<NCCDV> findById(Long id) {
        return nccdvRepository.findById(id);
    }

    @Override
    public NCCDV save(NCCDV NCCDV) {
        return nccdvRepository.save(NCCDV);
    }

    @Override
    public void remove(Long id) {
        nccdvRepository.deleteById(id);
    }

    @Override
    public Optional<NCCDV> findByUser(User user) {
        return nccdvRepository.findByUser(user);
    }

    @Override
    public Page<NCCDV> findAllPage(Pageable pageable) {
        return nccdvRepository.findAll(pageable);
    }
}
