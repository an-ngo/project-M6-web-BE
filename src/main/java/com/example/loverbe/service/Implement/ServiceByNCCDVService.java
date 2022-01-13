package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import com.example.loverbe.repository.IServiceByNCCDVRepository;
import com.example.loverbe.service.IServiceByNCCDVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ServiceByNCCDVService implements IServiceByNCCDVService {
    @Autowired
    private IServiceByNCCDVRepository serviceByNCCDVRepository;
    @Override
    public Iterable<ServiceByProvider> findAll() {
        return serviceByNCCDVRepository.findAll();
    }

    @Override
    public Optional<ServiceByProvider> findById(Long id) {
        return serviceByNCCDVRepository.findById(id);
    }

    @Override
    public ServiceByProvider save(ServiceByProvider serviceByProvider) {
        return serviceByNCCDVRepository.save(serviceByProvider);
    }

    @Override
    public void remove(Long id) {
serviceByNCCDVRepository.deleteById(id);
    }
}
