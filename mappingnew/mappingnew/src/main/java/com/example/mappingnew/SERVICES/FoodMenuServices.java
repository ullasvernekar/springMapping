package com.example.mappingnew.SERVICES;

import com.example.mappingnew.DAO.FoodMenuDao;
import com.example.mappingnew.DAO.ProductDao;
import com.example.mappingnew.DTO.FoodMenu;
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
public class FoodMenuServices {

    @Autowired
    private FoodMenuDao foodMenuDao;

    @Autowired
    private ProductDao productDao;

    public ResponseEntity<ResponseStructure<FoodMenu>> save(FoodMenu foodMenu) {
        ResponseStructure<FoodMenu> responseStructure = new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        List<ProductClub> productClubs = foodMenu.getProductClub();
        double totalAmount = 0;
        if (productClubs != null || !productClubs.isEmpty()) {
            for (ProductClub productClub : productClubs) {
                Product product = productDao.findById(productClub.getProduct().getId());
                if (product != null) {
                    productClub.setTotalPrice(productClub.getQuantity() * product.getPrice());
                    productClub.setFoodMenu(foodMenu);
                    totalAmount += productClub.getTotalPrice();
                } else {
                    responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
                    responseStructure.setMessage("Product");
                    responseStructure.setData(null);
                    return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
                }
            }
        } else {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("FoodMenu Doesn't Exists To Be Deleted");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        /*double totalFoodMenuPrice = productClubs.stream()
                .mapToDouble(foodProduct -> {
                    Product product = productDao.findById(foodProduct.getProduct().getId());
                    if (product == null) {
                        responseStructure.setMessage("Product ID Not Found: " + foodProduct.getProduct().getId());
                        return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND).getStatusCodeValue();
                    }
                    double pricePerUnit = product.getPrice() * foodProduct.getQuantity();
                    foodProduct.setFoodMenu(foodMenu);
                    foodProduct.setTotalPrice(pricePerUnit);
                    return pricePerUnit;
                }).sum();*/
        foodMenu.setTotalPrice(totalAmount);
        responseStructure.setMessage("FoodMenu Saved Successfully");
        responseStructure.setData(foodMenuDao.saveFood(foodMenu));
        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<FoodMenu>> delete(long id) {
        ResponseStructure<FoodMenu> responseStructure = new ResponseStructure<>();
        FoodMenu foodMenu = foodMenuDao.findById(id);
        if (Objects.isNull(foodMenu)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("FoodMenu Doesn't Exists To Be Deleted");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            foodMenuDao.deleteFood(foodMenu);
            responseStructure.setStatus(HttpStatus.FOUND.value());
            responseStructure.setMessage("FoodMenu Deleted Successfully");
            responseStructure.setData(foodMenu);
            return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
        }
    }


    public ResponseEntity<ResponseStructure<FoodMenu>> findById(long id) {
        ResponseStructure<FoodMenu> responseStructure = new ResponseStructure<>();
        FoodMenu foodMenu = foodMenuDao.findById(id);
        if (Objects.isNull(foodMenu)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("FoodMenu Does Not Exists So Cannot Be Found By Id ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.FOUND.value());
            responseStructure.setMessage("FoodMenu Found By Id" + id);
            responseStructure.setData(foodMenu);
            return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<List<FoodMenu>>> findAll() {
        ResponseStructure<List<FoodMenu>> responseStructure = new ResponseStructure<>();
        List<FoodMenu> foodMenu = foodMenuDao.findAllFood();
        if (Objects.isNull(foodMenu)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("All Users Does Not Exists To Find");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.FOUND.value());
            responseStructure.setMessage("All Users found");
            responseStructure.setData(foodMenu);
            return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
        }
    }


}
