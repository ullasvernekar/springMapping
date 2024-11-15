package com.example.mappingnew.DAO;

import com.example.mappingnew.DTO.Customer;
import com.example.mappingnew.REPOSITORY.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDao {
    @Autowired
    public CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }

    public Customer findById(long id){
        Optional<Customer> optional = customerRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer findByPhone (long phone){
        return customerRepository.findByPhone(phone);
    }
}
