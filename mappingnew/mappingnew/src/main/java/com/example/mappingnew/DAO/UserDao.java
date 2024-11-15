package com.example.mappingnew.DAO;
import org.springframework.data.domain.*;


import com.example.mappingnew.DTO.User;
import com.example.mappingnew.REPOSITORY.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    @Autowired
    public UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(User user){
         userRepository.delete(user);
    }

    public User findById(long id){
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    public Page<User> findAllUsers(int offset,int pageSize,String field){
        return userRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field).descending()));
    }
}
