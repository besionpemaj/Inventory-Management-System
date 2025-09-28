package com.inventory.management.system.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Category(Long id) {
        this.id = id;
    }

    @Column(name = "created_at")
    @CreationTimestamp
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    public LocalDateTime updatedAt;

    public String name;
    public String description;
}
