package com.example.autoservice.dao;

import com.example.autoservice.model.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long orderId);
}
