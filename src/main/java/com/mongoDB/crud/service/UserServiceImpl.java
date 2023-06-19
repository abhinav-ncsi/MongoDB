package com.mongoDB.crud.service;

import com.mongoDB.crud.exception.UserAlreadyExistsException;
import com.mongoDB.crud.exception.UserNotFoundException;
import com.mongoDB.crud.model.User;
import com.mongoDB.crud.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepository;
//    private final IUserRepository iUserRepository;
//
//    public UserServiceImpl(IUserRepository iUserRepository) {
//        this.iUserRepository = iUserRepository;
//    }

    @Override
    public User saveUser(User uobj) throws UserAlreadyExistsException {
        Optional<User> optional = this.userRepository.findById(uobj.getUserId());
        User adduobj = null;

        if(optional.isPresent())
        {
            System.out.println("User Details already exists ...");
            throw new UserAlreadyExistsException();
        }
        else
        {
            adduobj = this.userRepository.save(uobj);
        }
        return adduobj;
    }

    @Override
    public User updateUser(User uobj, int uid) throws UserNotFoundException {

        //get the user details which to be updated by passing the user id
        Optional<User> userOptional = this.userRepository.findById(uid);

        User uObj = null;
        User updatedData = null;

//    	Checking whether user id exists or not
        if(userOptional.isPresent())
        {
            System.out.println("Record Exists and ready for Update !!!");

//        	Extracting the user details as user object from optional
            uObj = userOptional.get();

//        	setting the updated value to setter method by taking from user through getter
            uObj.setEmail(uobj.getEmail());
            uObj.setFirstName(uobj.getFirstName());
            uObj.setLastName(uobj.getLastName());
            uObj.setPassword(uobj.getPassword());
            uObj.setNumber(uObj.getNumber());

//        	saving the final updated value to db
            updatedData = this.userRepository.save(uObj);
        }
        else
        {
            throw new UserNotFoundException();
        }
//        returning the updated value to user
        return updatedData;

    }

    @Override
    public User getUserById(int uid) throws UserNotFoundException {
        //get the user details which to be updated by passing the user id
        Optional<User> userOptional = this.userRepository.findById(uid);

        User uObj = null;

//    	Checking whether user id exists or not
        if(userOptional.isPresent())
        {

//        	Extracting the user details as user object from optional
            uObj = userOptional.get();
        }
        else
        {
            System.out.println("User does not exists");
            throw new UserNotFoundException();
        }

        return uObj;
    }

//    @Override
//    public List<User> getAllUsers() {
//        return this.userRepository.findAll();
//    }

    //adding pagination to get the user data in chunks
    @Override
    public Page<User> getAllUsersWithPagination(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public boolean delUser(int uid) throws UserNotFoundException {
        Optional<User> userOptional = this.userRepository.findById(uid);

        boolean status=false;

//    	Checking whether user id exists or not
        if(userOptional.isPresent())
        {
            System.out.println("Record Exists and ready for Deletion !!!");

//        	Extracting the user details as user object from optional
            this.userRepository.delete(userOptional.get());
            status=true;
        }
        else
        {
            System.out.println("User details does not exits for deletion ..");
            throw new UserNotFoundException();

        }
        return status;
    }
}
