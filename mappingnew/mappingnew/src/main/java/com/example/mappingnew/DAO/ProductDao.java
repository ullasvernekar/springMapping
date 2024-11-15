package com.example.mappingnew.DAO;

import com.example.mappingnew.DTO.Product;
import com.example.mappingnew.REPOSITORY.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {
    
    @Autowired
    public ProductRepository productRepository;
    
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    
    public void deleteProduct(Product product){
        productRepository.delete(product);
    }
    
    public Product findById(long id){
        Optional<Product> optional = productRepository.findById(id);
        return optional.orElse(null);
    }
    
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
}
