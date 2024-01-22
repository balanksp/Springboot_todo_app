package com.B2Techyoutubechannel.SpringbootMongoDBPractice.Service;

import java.util.List;

import javax.validation.ConstraintViolationException;
import com.B2Techyoutubechannel.SpringbootMongoDBPractice.Entity.TodoDTO;
import com.B2Techyoutubechannel.SpringbootMongoDBPractice.exception.TodoCollectionException;

public interface TodoService {
    
   public void  createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;  

   public List<TodoDTO> getAllTodos();
   
   public TodoDTO getSingleTodo(String id) throws TodoCollectionException;

   public void updateTodo(String id,TodoDTO todo)throws TodoCollectionException;

}
