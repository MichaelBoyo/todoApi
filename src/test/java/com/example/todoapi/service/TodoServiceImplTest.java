package com.example.todoapi.service;

import com.example.todoapi.data.dtos.TodoApiResponse;
import com.example.todoapi.data.dtos.TodoRequest;
import com.example.todoapi.data.dtos.UpdateTodoRequest;
import com.example.todoapi.data.repository.TodoRepository;
import com.example.todoapi.utils.TodoApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServiceImplTest {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();
    }


    @Test
    void createTodo() {
        TodoRequest todoRequest = new TodoRequest("title", "body");
        TodoApiResponse response = todoService.createTodo(todoRequest);
        assertEquals("title", response.getTodo().getTitle());
        assertEquals("body", response.getTodo().getBody());
    }

    @Test
    void updateTodo() throws TodoApiException {
        TodoRequest todoRequest = new TodoRequest("title", "body");
        TodoApiResponse createResponse = todoService.createTodo(todoRequest);
        UpdateTodoRequest updateTodoRequest = new UpdateTodoRequest(
                createResponse.getTodo().getId(), "new title", "new body");
        TodoApiResponse updateResponse = todoService.updateTodo(updateTodoRequest);
        assertEquals("new title", updateResponse.getTodo().getTitle());
        assertEquals("new body", updateResponse.getTodo().getBody());


    }

    @Test
    void deleteTodo() {
        TodoRequest todoRequest = new TodoRequest("title", "body");
        TodoApiResponse createResponse = todoService.createTodo(todoRequest);
        todoService.deleteTodo(createResponse.getTodo().getId());
        assertEquals(todoService.getTodos(Sort.Direction.ASC).getData(), Collections.emptyList());
    }

    @Test
    void getTodosAscendingSort() throws InterruptedException {
        TodoRequest todoRequest1 = new TodoRequest("title1", "body1");
        TodoRequest todoRequest2 = new TodoRequest("title2", "body2");
        TodoRequest todoRequest3 = new TodoRequest("title3", "body3");
        todoService.createTodos(List.of(todoRequest1, todoRequest2, todoRequest3));


        assertEquals(todoService.getTodos(Sort.Direction.ASC).getData().get(0).getTitle(), "title1");
        assertEquals(todoService.getTodos(Sort.Direction.ASC).getData().get(2).getTitle(), "title3");

    }

    @Test
    void getTodosDescendingSort() throws InterruptedException {
        TodoRequest todoRequest1 = new TodoRequest("title1", "body1");
        TodoRequest todoRequest2 = new TodoRequest("title2", "body2");
        TodoRequest todoRequest3 = new TodoRequest("title3", "body3");
        todoService.createTodos(List.of(todoRequest1, todoRequest2, todoRequest3));

        assertEquals(todoService.getTodos(Sort.Direction.DESC).getData().get(0).getTitle(), "title3");
        assertEquals(todoService.getTodos(Sort.Direction.DESC).getData().get(2).getTitle(), "title1");

    }
}