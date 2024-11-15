package com.example.mappingnew.REPOSITORY;

import com.example.mappingnew.DTO.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    public Customer findByPhone(long phone);
}
