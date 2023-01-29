package com.example.todoapi.service;

import com.example.todoapi.data.dtos.*;
import com.example.todoapi.utils.TodoApiException;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TodoService {
    TodoApiResponse createTodo(TodoRequest todoRequest);
    TodoApiResponse updateTodo(UpdateTodoRequest request) throws TodoApiException;
    ApiResponse deleteTodo(Long id);

    GetAllTodosResponse getTodos(Sort.Direction direction);

    void createTodos(List<TodoRequest> todoRequest1) throws InterruptedException;
}
