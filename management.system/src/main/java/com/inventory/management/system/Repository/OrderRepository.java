package com.inventory.management.system.Repository;

import com.inventory.management.system.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
