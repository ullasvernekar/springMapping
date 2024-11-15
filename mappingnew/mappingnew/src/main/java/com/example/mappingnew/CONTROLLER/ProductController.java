package com.example.mappingnew.CONTROLLER;

import com.example.mappingnew.DTO.Product;
import com.example.mappingnew.DTO.ResponseStructure;
import com.example.mappingnew.SERVICES.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Product")
public class ProductController {
    @Autowired
    public ProductService productService;

    @PostMapping(value = "/save")
    public  ResponseEntity<ResponseStructure<Product>> save(@RequestBody Product product){
        return productService.save(product);
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseStructure<Product>> delete(@RequestParam long id){
        return productService.delete(id);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable long id){
        return productService.findById(id);
    }

    @GetMapping(value = "/findAll")
    public  ResponseEntity<ResponseStructure<List<Product>>> findAll(){
        return productService.findAll();
    }
}
