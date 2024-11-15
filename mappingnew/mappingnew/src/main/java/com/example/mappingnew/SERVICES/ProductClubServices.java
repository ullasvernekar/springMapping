package com.example.mappingnew.SERVICES;

import com.example.mappingnew.DAO.ClubInventoryDao;
import com.example.mappingnew.DAO.ProductDao;
import com.example.mappingnew.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductClubServices {

    @Autowired
    private ClubInventoryDao clubInventoryDao;

    @Autowired
    private ProductDao productDao;

    public ResponseEntity<ResponseStructure<ProductClub>> save (ProductClub productClub,long id) {
        ResponseStructure<ProductClub> responseStructure = new ResponseStructure<>();
        Product product = productDao.findById(id);
        productClub.setTotalPrice(productClub.getQuantity() * product.getPrice());

        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("ProductClub totalPrice Saved Successfully");
        return new ResponseEntity<>(responseStructure,HttpStatus.OK);
    }
}
