package com.example.mappingnew.CONTROLLER;

import com.example.mappingnew.DTO.Customer;
import com.example.mappingnew.DTO.ResponseStructure;
import com.example.mappingnew.SERVICES.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Customer")
public class CustomerController {
    @Autowired
    public CustomerServices customerServices;

    @PostMapping(value = "/save")
     public   ResponseEntity<ResponseStructure<Customer>> save(@RequestBody Customer customer,long id){
        return customerServices.save(customer,id);
    }
    @DeleteMapping(value = "/delete")
    public  ResponseEntity<ResponseStructure<Customer>> delete(@RequestParam long id){
        return customerServices.delete(id);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<ResponseStructure<Customer>> findById(@PathVariable long id){
        return customerServices.findById(id);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseStructure<List<Customer>>> findAll(){
        return customerServices.findAll();
    }
}
