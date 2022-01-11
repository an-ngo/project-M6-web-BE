package com.example.loverbe.service;

import com.example.loverbe.model.entity.user.NCCDV;
import com.example.loverbe.model.entity.user.User;

import java.util.Optional;

public interface INCCDVService extends IGeneralService<NCCDV> {
    Optional<NCCDV> findByUser(User user);
}
