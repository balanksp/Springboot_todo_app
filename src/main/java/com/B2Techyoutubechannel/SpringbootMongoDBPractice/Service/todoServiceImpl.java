package com.B2Techyoutubechannel.SpringbootMongoDBPractice.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.B2Techyoutubechannel.SpringbootMongoDBPractice.Entity.TodoDTO;
import com.B2Techyoutubechannel.SpringbootMongoDBPractice.Repository.TodoRepository;
import com.B2Techyoutubechannel.SpringbootMongoDBPractice.exception.TodoCollectionException;

@Service
public class todoServiceImpl implements TodoService {
  @Autowired
  private TodoRepository repo;

  @Override
  public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException {
    Optional<TodoDTO> todoOptional = repo.findByTodo(todo.getTodo());

    if (todoOptional.isPresent()) {
      throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExist());
    }

    else {
      todo.setCreatedAt(new Date(System.currentTimeMillis()));
      repo.save(todo);
    }
  }

  /**
   * using service and service implementation layer
   */
  @Override
  public List<TodoDTO> getAllTodos() {
    List<TodoDTO> listTodo = repo.findAll();
    if (listTodo.size() > 0) {
      return listTodo;
    } else {
      return new ArrayList<TodoDTO>();
    }
  }

  @Override
  public TodoDTO getSingleTodo(String id) throws TodoCollectionException {
    Optional<TodoDTO> optionalTodos = repo.findById(id);
    if (!optionalTodos.isPresent()) {
     throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
    } 
    else
     {
      return  optionalTodos.get();
    }
  }

  @Override
  public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException {
          Optional<TodoDTO> todowithId = repo.findById(id);
          if (todowithId.isPresent()) {
            TodoDTO saveTodo = todowithId.get();
            saveTodo.setTodo(todo.getTodo());
            saveTodo.setCompleted(todo.getCompleted());
            saveTodo.setDescription(todo.getDescription());
            saveTodo.setUpdateAt(new Date(System.currentTimeMillis()));
            repo.save(saveTodo);
          } else {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
          }

  }

  




}

