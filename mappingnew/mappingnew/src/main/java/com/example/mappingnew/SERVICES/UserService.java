package com.example.mappingnew.SERVICES;


import com.example.mappingnew.DAO.UserDao;
import com.example.mappingnew.DTO.ResponseStructure;
import com.example.mappingnew.DTO.User;
import com.example.mappingnew.REPOSITORY.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    public UserDao userdao;

    @Autowired
    public UserRepository userRepository;

    public ResponseEntity<ResponseStructure<User>> saveUser(User user){
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        User test = userdao.findById(user.getId());
        if(Objects.isNull(test)){
            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("User Saved Successfully");
            responseStructure.setData(userdao.saveUser(user));
            return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
        }
        else {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("User Already Exists So Cannot Be Saved");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<ResponseStructure<User>> deleteUser(long id){
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        User user = userdao.findById(id);
        if(Objects.isNull(user)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("User Doesn't Exists To Be Deleted");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }
            else {
                userdao.deleteUser(user);
                responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
                responseStructure.setMessage("User Deleted Successfully");
                responseStructure.setData(user);
                return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
            }
        }

        public ResponseEntity<ResponseStructure<User>> findById(long id){
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        User user = userdao.findById(id);
        if(Objects.isNull(user)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("User Does Not Exists To Be Found By ID ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }
        else {
            responseStructure.setStatus(HttpStatus.FOUND.value());
            responseStructure.setMessage("User Found By ID = " +id);
            responseStructure.setData(user);
            return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
        }
        }

        public ResponseEntity<ResponseStructure<Page<User>>> findAll(int offset,int pageSize,String field){
        ResponseStructure<Page<User>> responseStructure = new ResponseStructure<>();
        Page<User> usersPage = userdao.findAllUsers(offset,pageSize,field);
            if (!usersPage.isEmpty()) {
                responseStructure.setStatus(HttpStatus.OK.value());
                responseStructure.setMessage("All Users found");
                responseStructure.setData(usersPage);
                return new ResponseEntity<>(responseStructure,HttpStatus.OK);
            } else {
                responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
                responseStructure.setMessage("No Users Found");
                responseStructure.setData(null);
                return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
            }
        }
}


