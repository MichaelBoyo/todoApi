package com.example.todoapi.data.repository;

import com.example.todoapi.data.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
