package com.example.mappingnew.CONTROLLER;

import com.example.mappingnew.DTO.ResponseStructure;
import com.example.mappingnew.DTO.User;
import com.example.mappingnew.SERVICES.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/User")
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseStructure<User>> save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseStructure<User>> delete(@RequestBody long id) {
        return userService.deleteUser(id);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<ResponseStructure<User>> findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseStructure<Page<User>>> getAll(@RequestParam(defaultValue = "0") int offset,
                                                                @RequestParam(defaultValue = "5") int pageSize, String field) {
        return userService.findAll(offset, pageSize, field);
    }


}