package com.B2Techyoutubechannel.SpringbootMongoDBPractice.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.B2Techyoutubechannel.SpringbootMongoDBPractice.Entity.TodoDTO;

public interface TodoRepository extends MongoRepository<TodoDTO, String> { // string data type is primary key
    /*
     * find the todo in the DB 
     */

    @Query("{'todo': ?0}")
    Optional<TodoDTO> findByTodo(String todo);


    
}
