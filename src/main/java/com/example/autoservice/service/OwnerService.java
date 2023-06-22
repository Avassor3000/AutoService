package com.example.autoservice.service;

import com.example.autoservice.model.Owner;

public interface OwnerService {
    Owner save(Owner owner);

    Owner getById(Long ownerId);

    Owner getByOwnerName(String ownerName);
}
