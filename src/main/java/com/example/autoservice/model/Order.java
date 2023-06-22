package com.example.autoservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Car car;
    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;
    private String description;
    private LocalDate orderTakeDate;
    @OneToMany(mappedBy = "order")
    private List<Operation> operations;
    @OneToMany
    private List<CarStuff> listOfStuff;
    private OrderStatus orderStatus;
    private double finalPrice;
    private LocalDate orderDoneDate;
}
