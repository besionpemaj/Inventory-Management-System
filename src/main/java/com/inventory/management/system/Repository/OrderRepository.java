package com.inventory.management.system.Repository;

import com.inventory.management.system.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findById(int id);

    List<Order> findAll();
}

