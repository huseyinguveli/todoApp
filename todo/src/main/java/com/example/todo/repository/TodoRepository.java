package com.example.todo.repository;

import com.example.todo.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Long> {

}
