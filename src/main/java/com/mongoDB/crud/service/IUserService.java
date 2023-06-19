package com.mongoDB.crud.service;

import com.mongoDB.crud.exception.UserAlreadyExistsException;
import com.mongoDB.crud.exception.UserNotFoundException;
import com.mongoDB.crud.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {

    public User saveUser(User uobj) throws UserAlreadyExistsException;

    public User updateUser(User uobj,int uid) throws UserNotFoundException;

    public User getUserById(int uid) throws UserNotFoundException;

//    public List<User> getAllUsers();
    public Page<User> getAllUsersWithPagination(int pageNumber, int pageSize);

    public boolean delUser(int uid)throws UserNotFoundException;

}
