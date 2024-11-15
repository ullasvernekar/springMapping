package com.example.mappingnew.REPOSITORY;

import com.example.mappingnew.DTO.ClubInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubInventoryRepository extends JpaRepository<ClubInventory, Long> {

    public ClubInventory findBybalanceType(double balanceType);

}
