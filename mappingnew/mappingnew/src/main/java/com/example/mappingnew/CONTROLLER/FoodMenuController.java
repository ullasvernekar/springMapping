package com.example.mappingnew.CONTROLLER;

import com.example.mappingnew.DTO.FoodMenu;
import com.example.mappingnew.DTO.ResponseStructure;
import com.example.mappingnew.SERVICES.FoodMenuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Foodmenu")
public class FoodMenuController {
    @Autowired
    public FoodMenuServices foodMenuServices;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseStructure<FoodMenu>> save(@RequestBody FoodMenu foodMenu){
        return foodMenuServices.save(foodMenu);
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseStructure<FoodMenu>> delete(@RequestParam long id){
        return foodMenuServices.delete(id);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<ResponseStructure<FoodMenu>> findById(@RequestParam long id){
        return foodMenuServices.findById(id);
    }
    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseStructure<List<FoodMenu>>> findAll(){
        return foodMenuServices.findAll();
    }
}
