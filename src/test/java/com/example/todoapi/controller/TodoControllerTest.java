package com.example.todoapi.controller;

import com.example.todoapi.data.dtos.GetAllTodosResponse;
import com.example.todoapi.data.dtos.TodoApiResponse;
import com.example.todoapi.data.dtos.TodoRequest;
import com.example.todoapi.data.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private TodoRepository todoRepository;



    @LocalServerPort
    private int port;

    private final String baseUrl = "/api/v1/todo";
    private final String serverHost = "http://localhost:";
    private ResponseEntity<TodoApiResponse> response;
    private TodoRequest todoRequest;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();
        todoRequest = new TodoRequest("title", "body");
        response = testRestTemplate.postForEntity(
                serverHost + port + baseUrl,
                new HttpEntity<>(todoRequest, null),
                TodoApiResponse.class
        );
    }

    @Test
    void createTodo() {
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getTodo().getTitle(), todoRequest.getTitle());
        assertEquals(response.getBody().getTodo().getBody(), todoRequest.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    void updateTodoById() {
        TodoRequest updateTodoRequest = new TodoRequest("new title", "new body");

        testRestTemplate.put(serverHost + port + baseUrl + "/" + response.getBody().getTodo().getId(),
                new HttpEntity<>(updateTodoRequest, null));
        ResponseEntity<GetAllTodosResponse> getAllTodosResponse = testRestTemplate
                .getForEntity(serverHost + port + baseUrl + "?sort=desc",
                        GetAllTodosResponse.class);

        assertTrue(getAllTodosResponse.getBody().isSuccess());
        assertEquals(getAllTodosResponse.getStatusCode(), HttpStatus.OK);
        assertNotEquals(getAllTodosResponse.getBody().getData(), Collections.emptyList());
        assertEquals(getAllTodosResponse.getBody().getData().get(0).getTitle(), updateTodoRequest.getTitle());
        assertEquals(getAllTodosResponse.getBody().getData().get(0).getBody(), updateTodoRequest.getBody());


    }

    @Test
    void deleteTodoById() {
        testRestTemplate.delete(serverHost + port + baseUrl + "/" + response.getBody().getTodo().getId());

        ResponseEntity<GetAllTodosResponse> getAllTodosResponse = testRestTemplate
                .getForEntity(serverHost + port + baseUrl + "?sort=desc",
                        GetAllTodosResponse.class);

        assertEquals(getAllTodosResponse.getBody().getData(), Collections.emptyList());
    }

    @Test
    void getTodos() {
        ResponseEntity<GetAllTodosResponse> getAllTodosResponse = testRestTemplate
                .getForEntity(serverHost + port + baseUrl + "?sort=desc",
                        GetAllTodosResponse.class);
        assertTrue(getAllTodosResponse.getBody().isSuccess());
        assertEquals(getAllTodosResponse.getStatusCode(), HttpStatus.OK);
        assertNotEquals(getAllTodosResponse.getBody().getData(), Collections.emptyList());

    }
}