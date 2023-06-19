package com.mongoDB.crud.repository;

import com.mongoDB.crud.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends MongoRepository<User,Integer> {
    List<User> findAllByUserId(int userId, Pageable pageable);
}
//@Repository
//public interface IUserRepository extends PagingAndSortingRepository<User,Integer> {
//
//    List<User> findAllByUserId(int userId, Pageable pageable);
//}
