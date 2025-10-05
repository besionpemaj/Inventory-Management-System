package com.inventory.management.system.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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
    @UpdateTimestamp
    public LocalDateTime updatedAt;

    public String name;
    public String description;

}
