package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.user.nccdv.Image;
import com.example.loverbe.repository.IImageRepository;
import com.example.loverbe.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepository iImageRepository;
    @Override
    public Iterable<Image> findAll() {
        return iImageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return iImageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public void remove(Long id) {
        iImageRepository.deleteById(id);
    }
}
