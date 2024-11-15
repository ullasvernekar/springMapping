package com.example.mappingnew.DAO;

import com.example.mappingnew.DTO.FoodMenu;
import com.example.mappingnew.REPOSITORY.FoodMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FoodMenuDao {

    @Autowired
    public FoodMenuRepository foodMenuRepository;

    public FoodMenu saveFood(FoodMenu foodMenu){
        return foodMenuRepository.save(foodMenu);
    }

    public void deleteFood(FoodMenu foodMenu){
        foodMenuRepository.delete(foodMenu);
    }

    public FoodMenu findById(long id){
        Optional<FoodMenu> optional = foodMenuRepository.findById(id);
        return optional.orElse(null);
    }

    public List<FoodMenu> findAllFood() {
        return foodMenuRepository.findAll();
    }
}
