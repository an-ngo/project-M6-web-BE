package com.example.loverbe.repository;

import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServiceByProviderRepository extends JpaRepository<ServiceByProvider,Long> {
}
