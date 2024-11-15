package com.example.mappingnew.DAO;

import com.example.mappingnew.DTO.ClubInventory;
import com.example.mappingnew.REPOSITORY.ClubInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClubInventoryDao {
    @Autowired
    public ClubInventoryRepository clubInventoryRepository;

    public ClubInventory saveInventory(ClubInventory clubInventory){
        return clubInventoryRepository.save(clubInventory);
    }

    public void deleteInventory(ClubInventory clubInventory){
        clubInventoryRepository.delete(clubInventory);
    }

    public ClubInventory findById(long id){
        Optional<ClubInventory> optional = clubInventoryRepository.findById(id);
        return optional.orElse(null);
    }

    public List<ClubInventory> findAllInventory(){
        return clubInventoryRepository.findAll();
    }
}
