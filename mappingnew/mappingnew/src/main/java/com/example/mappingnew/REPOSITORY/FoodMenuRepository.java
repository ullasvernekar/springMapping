package com.example.mappingnew.REPOSITORY;

import com.example.mappingnew.DTO.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodMenuRepository extends JpaRepository<FoodMenu, Long> {

    public Optional<FoodMenu> findById(long id);
}
