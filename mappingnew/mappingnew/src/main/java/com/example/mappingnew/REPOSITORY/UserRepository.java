package com.example.mappingnew.REPOSITORY;

import com.example.mappingnew.DTO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByPhone(long phone);

    public Optional<User> findById(long id);


}
