package com.example.autoservice.dao;

import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDao extends JpaRepository<Owner, Long> {
    Optional<Owner> findById(Long id);

    Optional<Owner> findByOwnerName(String ownerName);
}
