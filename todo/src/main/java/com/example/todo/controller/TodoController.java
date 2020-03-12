package com.example.todo.controller;


import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.model.TodoItem;
import com.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @GetMapping("/projects")
    public List<TodoItem> getAllTodoItems() {
        List<TodoItem> projects = new ArrayList<>();
        todoRepository.findAll().forEach(projects::add);
        return projects;
    }

    @PostMapping("/projects")
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
        TodoItem todoItem1 = todoRepository.save(new TodoItem(todoItem.getName(),todoItem.getDescription(),todoItem.getDeadline()));
        return todoItem1;
    }


    @PutMapping("/projects/{id}")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable("id") Long todoId, @RequestBody TodoItem todoItem) {

        Optional<TodoItem> todoItemData = todoRepository.findById(todoId);
        if (todoItemData.isPresent()){
            TodoItem todoItem1 = todoItemData.get();
            todoItem1.setName(todoItem.getName());
            todoItem1.setDescription(todoItem.getDescription());
            todoItem1.setDeadline(todoItem.getDeadline());
            todoItem1.setStatus(todoItem.getStatus());
            return new ResponseEntity<>(todoRepository.save(todoItem1), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<String> deleteTodoItem(@PathVariable(value = "id") Long todoId) {

        todoRepository.deleteById(todoId);

        return new ResponseEntity<>("Proje Silindi!", HttpStatus.OK);
    }

}
