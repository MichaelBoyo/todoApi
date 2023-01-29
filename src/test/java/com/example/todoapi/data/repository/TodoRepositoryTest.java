package com.example.todoapi.data.repository;

import com.example.todoapi.data.models.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;


    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    public void testThatTodoCanBeSaved(){
        Todo todo = new Todo("title","body");
        Todo savedTodo = todoRepository.save(todo);
        assertNotNull(savedTodo);
        assertEquals(todo.getTitle(), savedTodo.getTitle());
        assertEquals(todo.getBody(), savedTodo.getBody());
    }

    @Test
    public void testThatTodoCanBeDeleted(){
         Todo todo = new Todo("title","body");
        Todo savedTodo = todoRepository.save(todo);
        todoRepository.deleteById(savedTodo.getId());
        assertEquals(Collections.emptyList(), todoRepository.findAll());
    }
}