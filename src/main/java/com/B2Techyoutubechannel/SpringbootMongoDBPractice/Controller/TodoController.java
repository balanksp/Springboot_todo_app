package com.B2Techyoutubechannel.SpringbootMongoDBPractice.Controller;

import java.util.*;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.B2Techyoutubechannel.SpringbootMongoDBPractice.Entity.TodoDTO;
import com.B2Techyoutubechannel.SpringbootMongoDBPractice.Repository.TodoRepository;
import com.B2Techyoutubechannel.SpringbootMongoDBPractice.Service.TodoService;
import com.B2Techyoutubechannel.SpringbootMongoDBPractice.exception.TodoCollectionException;

@RestController // this annotation used for handle the HTTP request and generate the HTTP
                // response
public class TodoController {

    @Autowired
    private TodoRepository repo;

    @Autowired
    private TodoService service;

    /**
     * read all the records in DB
     * 
     * @return
     */
    @GetMapping("/getAllTodos")
    public ResponseEntity<?> getAllTodos() {

        /*
         * ResponseEntity<?> => the code indicates that the HTTP response can have a
         * variable type of response body,
         * which can be any type of object. The response body will be automatically
         * converted to JSON format
         */

        List<TodoDTO> todos = repo.findAll(); // findAll method retrieve all the records from collection
        if (todos.size() > 0) {
            return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK); // status code 200 successful http response
            // return new ResponseEntity<>(todos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("there is no records", HttpStatus.NOT_FOUND); // status code 404 there is no
                                                                                      // records in collection.

        }

    }

    /**
     * create a record in DB
     * 
     * @param todo
     * @return
     */
    @PostMapping("/createTodo")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
        try {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            repo.save(todo);
            return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // status code 500
        }
    }

    /**
     * this api for read the particular record by using id
     * 
     * @param id
     * @return
     */

    @GetMapping("/viewSingleTodoDetails/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
        Optional<TodoDTO> todoOptional = repo.findById(id);
        if (todoOptional.isPresent()) {
            return new ResponseEntity<TodoDTO>(todoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("required data not here" + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * modify a existing record BY ID
     * 
     * @param id
     * @param todo
     * @return
     */
    @PutMapping("/modifyTodoDetails/{id}")
    public ResponseEntity<?> updateTodoDetails(@PathVariable("id") String id, @RequestBody TodoDTO todo) {
        Optional<TodoDTO> todooptional = repo.findById(id);
        if (todooptional.isPresent()) {
            TodoDTO saveTodo = todooptional.get();
            saveTodo.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : todo.getCompleted());
            saveTodo.setDescription(todo.getDescription() != null ? todo.getDescription() : todo.getDescription());
            saveTodo.setTodo(todo.getTodo() != null ? todo.getTodo() : todo.getTodo());
            saveTodo.setCreatedAt(new Date(System.currentTimeMillis()));
            saveTodo.setUpdateAt(new Date(System.currentTimeMillis()));
            repo.save(saveTodo);
            return new ResponseEntity<TodoDTO>(saveTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no modify records" + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * this api for delete a record by id
     * 
     * @param <T>
     */

    @DeleteMapping("/deleteTodo/{id}")
    public <T> ResponseEntity<?> deleteByid(@PathVariable("id") String id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>("successfully deleted the record     " + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    /**
     * when give the value as a null , then validation message should response
     */

    @PostMapping("/createTodoValidation")
    public ResponseEntity<?> createTodoValidation(@RequestBody TodoDTO todo) {
        try {
            service.createTodo(todo);
            return new ResponseEntity<TodoDTO>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

/**
 * view all records (using service and service implementation layer)
 */

    @GetMapping("/viewAllRecords")
    public ResponseEntity<?> viewAllTodos(){
        List<TodoDTO>  listOfTodos =  service.getAllTodos();
        return new ResponseEntity<>(listOfTodos,listOfTodos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND );
    }

    @GetMapping("/viewSingleTodo/{id}")
    public ResponseEntity<?>viewSingleTodo(@PathVariable ("id")String id){
                  
                  try {
                    return new ResponseEntity<>(service.getSingleTodo(id),HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
                }
    }


    @PutMapping("/modifyTodos/{id}")
    public ResponseEntity<?> modifyTodos(String id ,TodoDTO todo){
      return new ResponseEntity<>(todo, HttpStatus.OK);
    }
     
}
