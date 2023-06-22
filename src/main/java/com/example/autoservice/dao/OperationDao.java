package com.example.autoservice.dao;

import com.example.autoservice.model.Operation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationDao extends JpaRepository<Operation, Long> {
    Optional<Operation> findById(Long operationId);
}
