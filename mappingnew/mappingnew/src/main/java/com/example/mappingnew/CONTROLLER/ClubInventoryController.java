package com.example.mappingnew.CONTROLLER;


import com.example.mappingnew.DTO.ClubInventory;
import com.example.mappingnew.DTO.ResponseStructure;
import com.example.mappingnew.SERVICES.ClubInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/ClubInventory")
@RestController
public class ClubInventoryController {

    @Autowired
    public ClubInventoryService clubInventoryService;

    @PostMapping(value = "/save/{id}")
    public ResponseEntity<ResponseStructure<ClubInventory>> save(@RequestBody ClubInventory clubInventory, @PathVariable long id) {
        return clubInventoryService.saveClubInventory(clubInventory,id);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseStructure<ClubInventory>> delete(@RequestParam long id) {
        return clubInventoryService.delete(id);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<ResponseStructure<ClubInventory>> findById(@PathVariable long id) {
        return clubInventoryService.findById(id);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseStructure<List<ClubInventory>>> findAll() {
        return clubInventoryService.findAll();
    }
}
