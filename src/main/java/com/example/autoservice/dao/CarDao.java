package com.example.autoservice.dao;

import com.example.autoservice.model.Car;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
}
