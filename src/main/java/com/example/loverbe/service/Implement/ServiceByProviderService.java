package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import com.example.loverbe.repository.IServiceByProviderRepository;
import com.example.loverbe.service.IServiceByProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ServiceByProviderService implements IServiceByProviderService {
    @Autowired
    private IServiceByProviderRepository serviceByProviderRepository;
    @Override
    public Iterable<ServiceByProvider> findAll() {
        return serviceByProviderRepository.findAll();
    }

    @Override
    public Optional<ServiceByProvider> findById(Long id) {
        return serviceByProviderRepository.findById(id);
    }

    @Override
    public ServiceByProvider save(ServiceByProvider serviceByProvider) {
        return serviceByProviderRepository.save(serviceByProvider);
    }

    @Override
    public void remove(Long id) {
serviceByProviderRepository.deleteById(id);
    }
}
