package com.example.mappingnew.SERVICES;

import com.example.mappingnew.DAO.ClubInventoryDao;
import com.example.mappingnew.DAO.ProductDao;
import com.example.mappingnew.DTO.ClubInventory;
import com.example.mappingnew.DTO.Product;
import com.example.mappingnew.DTO.ProductClub;
import com.example.mappingnew.DTO.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    public ProductDao productDao;

    @Autowired
    public ClubInventoryDao clubInventoryDao;

    public ResponseEntity<ResponseStructure<Product>> save(Product product){
        ResponseStructure<Product> responseStructure = new ResponseStructure<>();
            product.setTotalPrice(product.getPrice() * product.getConversion());
            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("Product Saved Successfully");
            responseStructure.setData(productDao.saveProduct(product));
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        }

    public ResponseEntity<ResponseStructure<Product>> delete(long id){
        ResponseStructure<Product> responseStructure = new ResponseStructure<>();
        Product product = productDao.findById(id);
        if(Objects.isNull(product)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Product Already Exists To Be Deleted");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        else {
            productDao.deleteProduct(product);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Product Deleted Successfully");
            responseStructure.setData(product);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    
   public ResponseEntity<ResponseStructure<Product>> findById(long id){
        ResponseStructure<Product> responseStructure = new ResponseStructure<>();
        ProductClub productClub =  new ProductClub();
        Product product = productDao.findById(id);
        if(Objects.isNull(product)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Product Could Not Be Found By ID ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        else {
            responseStructure.setStatus(HttpStatus.FOUND.value());
            responseStructure.setMessage("Product Found By ID Successfully" + id);
            responseStructure.setData(product);
            return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<List<Product>>> findAll(){
        ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();
       List<Product> product1 = productDao.findAllProducts();
        if(Objects.isNull(product1)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("All Product Could Not Be Found By ID");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        else {
            responseStructure.setStatus(HttpStatus.FOUND.value());
            responseStructure.setMessage("All Products Found By ID");
            responseStructure.setData(product1);
            return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
        }
    }


}


