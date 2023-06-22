package com.example.autoservice.dao;

import com.example.autoservice.model.Master;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterDao extends JpaRepository<Master, Long> {
    Optional<Master> findByName(String masterName);

    Optional<Master> findById(Long masterId);
}
