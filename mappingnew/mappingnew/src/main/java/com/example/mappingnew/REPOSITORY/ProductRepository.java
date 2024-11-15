package com.example.mappingnew.REPOSITORY;

import com.example.mappingnew.DTO.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public Optional<Product> findById(long id);
}
