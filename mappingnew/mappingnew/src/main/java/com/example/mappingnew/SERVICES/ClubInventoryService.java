package com.example.mappingnew.SERVICES;

import com.example.mappingnew.DAO.ClubInventoryDao;
import com.example.mappingnew.DAO.ProductDao;
import com.example.mappingnew.DTO.ClubInventory;
import com.example.mappingnew.DTO.Product;
import com.example.mappingnew.DTO.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.FormatFlagsConversionMismatchException;
import java.util.List;
import java.util.Objects;


    @Service
    public class ClubInventoryService {

        @Autowired
        public ClubInventoryDao clubInventoryDao;

        @Autowired
        public ProductDao productDao;

    public ResponseEntity<ResponseStructure<ClubInventory>> saveClubInventory(ClubInventory clubInventory, long id) {
        ResponseStructure<ClubInventory> responseStructure = new ResponseStructure<>();
        Product product = productDao.findById(id);
        if (Objects.isNull(product)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Product Id Not Found No Club Inventory will Be Saved");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            clubInventory.setProduct(product);
            clubInventory.setBalanceType(product.getConversion() * clubInventory.getQuantity());
            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("ClubInventory Saved For The Product Of Name = " + product.getName());
            responseStructure.setData(clubInventoryDao.saveInventory(clubInventory));
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<ResponseStructure<ClubInventory>> delete(long id){
        ResponseStructure<ClubInventory> responseStructure = new ResponseStructure<>();
        ClubInventory clubInventory = clubInventoryDao.findById(id);
        if(Objects.isNull(clubInventory)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("ClubInventory Doesn't Exists To Be Deleted");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }
        else {
            clubInventoryDao.deleteInventory(clubInventory);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("ClubInventory Deleted Successfully");
            responseStructure.setData(clubInventory);
            return new ResponseEntity<>(responseStructure,HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<ClubInventory>> findById(long id){
        ResponseStructure<ClubInventory> responseStructure = new ResponseStructure<>();
        ClubInventory clubInventory = clubInventoryDao.findById(id);
        if(Objects.isNull(clubInventory)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("ClubInventory Not Found By Id ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }
        else {
            responseStructure.setStatus(HttpStatus.FOUND.value());
            responseStructure.setMessage("ClubInventory Found By Id" +id);
            responseStructure.setData(clubInventory);
            return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<List<ClubInventory>>> findAll(){
        ResponseStructure<List<ClubInventory>> responseStructure = new ResponseStructure<>();
        List<ClubInventory> clubInventory = clubInventoryDao.findAllInventory();
        if(Objects.isNull(clubInventory)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("ClubInventory Does Not Exists To Find");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }
        else {
            responseStructure.setStatus(HttpStatus.FOUND.value());
            responseStructure.setMessage("All ClubInventory Found");
            responseStructure.setData(clubInventory);
            return new ResponseEntity<>(responseStructure,HttpStatus.FOUND);
        }
    }
}
