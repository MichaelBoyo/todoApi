package com.example.todoapi.controller;

import com.example.todoapi.data.dtos.*;
import com.example.todoapi.service.TodoService;
import com.example.todoapi.utils.TodoApiException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoApiResponse> createTodo(@RequestBody TodoRequest request) {
        return ResponseEntity.ok(todoService.createTodo(request));
    }


    @PutMapping("/{id}")
    public ResponseEntity<TodoApiResponse> updateTodoById(@PathVariable Long id, @RequestBody UpdateTodoRequest request) throws TodoApiException {
        request.setId(id);
        return ResponseEntity.ok(todoService.updateTodo(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.deleteTodo(id));
    }

    @GetMapping
    public ResponseEntity<GetAllTodosResponse> getTodos(@RequestParam(defaultValue = "asc") String sort) {
        return ResponseEntity.ok(todoService
                .getTodos(!sort.equals("asc") ? Sort.Direction.DESC : Sort.Direction.ASC));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoApiResponse> getTodoById(@PathVariable Long id) throws TodoApiException {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }
}
