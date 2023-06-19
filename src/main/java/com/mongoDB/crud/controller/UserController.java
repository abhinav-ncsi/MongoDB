package com.mongoDB.crud.controller;

import com.mongoDB.crud.exception.UserAlreadyExistsException;
import com.mongoDB.crud.exception.UserNotFoundException;
import com.mongoDB.crud.model.User;
import com.mongoDB.crud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    private ResponseEntity<?> responseEntity;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User newUser = null;
        try {
            newUser = this.userService.saveUser(user);
        } catch (UserAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        responseEntity = new ResponseEntity<>(newUser, HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("/updateUser/{uid}")
    public ResponseEntity<?> updateNoteHandler(@RequestBody User uobj,@PathVariable int uid) throws UserNotFoundException
    {
            User newUser = this.userService.updateUser(uobj, uid);
            responseEntity = new ResponseEntity<>(newUser, HttpStatus.CREATED);
        return responseEntity;
    }

//    @GetMapping("/getAllUsers")
//    public ResponseEntity<?> getAllUsers()
//    {
//        List<User> allUsers = this.userService.getAllUsers();
//        responseEntity = new ResponseEntity<>(allUsers,HttpStatus.OK);
//        return responseEntity;
//    }
    @GetMapping("/getAllUsers")
    public Page<User> getAllUsersWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize){

        return userService.getAllUsersWithPagination(pageNumber,pageSize);

    }

    @GetMapping("/getUserbyid/{uid}")
    public ResponseEntity<?> getUserById(@PathVariable int uid) throws UserNotFoundException
    {
        User uObj = this.userService.getUserById(uid);
        responseEntity = new ResponseEntity<>(uObj,HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/delUserbyid/{uid}")
    public ResponseEntity<?> DeleteNoteByIdHandler(@PathVariable int uid) throws UserNotFoundException
    {
        boolean status = this.userService.delUser(uid);
        responseEntity = new ResponseEntity<>("User Deleted ....",HttpStatus.OK);
        return responseEntity;
    }
}
