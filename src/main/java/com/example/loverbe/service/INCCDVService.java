package com.example.loverbe.service;

import com.example.loverbe.model.entity.user.NCCDV;
import com.example.loverbe.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface INCCDVService extends IGeneralService<NCCDV> {
    Optional<NCCDV> findByUser(User user);
    Page<NCCDV> findAllPage(Pageable pageable);
}
