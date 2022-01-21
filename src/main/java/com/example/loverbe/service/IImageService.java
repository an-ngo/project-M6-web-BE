package com.example.loverbe.service;

import com.example.loverbe.model.entity.user.nccdv.Image;

public interface IImageService extends IGeneralService<Image>{
    Iterable<Image> findAllByUserId(Long id);

}
