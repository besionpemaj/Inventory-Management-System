package com.inventory.management.system.Repository;

import com.inventory.management.system.Entity.Category;
import com.inventory.management.system.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);

    Optional<Product> findById(Long id);

    Page<Product> findByCategory(Category category, Pageable pageable);

    List<Product> findAllByDeletedFalse();
}
