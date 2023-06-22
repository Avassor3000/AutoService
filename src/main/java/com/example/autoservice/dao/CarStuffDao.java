package com.example.autoservice.dao;

import com.example.autoservice.model.CarStuff;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarStuffDao extends JpaRepository<CarStuff, Long> {
    Optional<CarStuff> findById(Long id);
}
