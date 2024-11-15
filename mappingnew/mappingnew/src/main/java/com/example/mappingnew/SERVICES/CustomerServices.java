package com.example.mappingnew.SERVICES;

import com.example.mappingnew.DAO.CustomerDao;
import com.example.mappingnew.DTO.Customer;
import com.example.mappingnew.DTO.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServices {
    @Autowired
    public CustomerDao customerDao;

    public ResponseEntity<ResponseStructure<Customer>> save(Customer customer, long id){
        ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
        Customer customer1 = customerDao.findById(customer.getId());
        if(Objects.isNull(customer1)) {
            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("Customer Saved Successfully");
            responseStructure.setData(customerDao.saveCustomer(customer));
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        }
        else {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Customer Could Not Be Saved,Already Exists");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<ResponseStructure<Customer>> delete(long id){
        ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
        Customer customer = customerDao.findById(id);
        if(Objects.isNull(customer)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Customer Doesn't Exists To Be Deleted");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }
        else {
            customerDao.deleteCustomer(customer);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Customer Deleted Successfully");
            responseStructure.setData(customer);
            return new ResponseEntity<>(responseStructure,HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Customer>> findById(long id){
        ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
        Customer customer = customerDao.findById(id);
        if(Objects.isNull(customer)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Customer Not Found By Id ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }
        else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Customer Found By Id" +id);
            responseStructure.setData(customer);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<List<Customer>>> findAll(){
        ResponseStructure<List<Customer>> responseStructure = new ResponseStructure<>();
        List<Customer> customer = customerDao.findAllCustomers();
        if(Objects.isNull(customer)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("All Customers Not Found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }
        else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("All Customer found");
            responseStructure.setData(customer);
            return new ResponseEntity<>(responseStructure,HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Customer>> findByPhone(long phone){
        ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
        Customer customer = customerDao.findByPhone(phone);
        if(Objects.isNull(customer)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Customer Does Not Exists So Cannot Be Found By Id ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }
        else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Customer Found By Id");
            responseStructure.setData(customer);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }
}
