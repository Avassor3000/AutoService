package com.example.autoservice.service.impl;

import com.example.autoservice.dao.OwnerDao;
import com.example.autoservice.model.Owner;
import com.example.autoservice.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao;

    public OwnerServiceImpl(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Override
    public Owner save(Owner owner) {
        return ownerDao.save(owner);
    }

    @Override
    public Owner getById(Long ownerId) {
        return ownerDao.findById(ownerId).orElse(null);
    }

    @Override
    public Owner getByOwnerName(String ownerName) {
        return ownerDao.findByOwnerName(ownerName).orElse(null);
    }
}
