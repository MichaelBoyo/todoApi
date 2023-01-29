package com.example.todoapi.service;

import com.example.todoapi.data.dtos.*;
import com.example.todoapi.data.models.Todo;
import com.example.todoapi.data.repository.TodoRepository;
import com.example.todoapi.utils.TodoApiException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public TodoApiResponse createTodo(TodoRequest todoRequest) {
        Todo todo = modelMapper.map(todoRequest, Todo.class);
        todo.setCreatedAt(
                LocalDateTime.now()
                        .format(DateTimeFormatter
                                .ofPattern("yyyy-MM-dd HH:mm:ss")));
        todoRepository.saveAndFlush(todo);
        return TodoApiResponse.builder()
                .success(true)
                .todo(todo)
                .build();
    }

    @Override
    public TodoApiResponse updateTodo(UpdateTodoRequest request) throws TodoApiException {
        Todo todo = todoRepository.findById(request.getId()).orElseThrow(
                () -> new TodoApiException("Todo not found")
        );
        if (!Objects.equals(request.getBody(), "") && request.getBody() != null) todo.setBody(request.getBody());
        if (!Objects.equals(request.getTitle(), "") && request.getTitle() != null) todo.setTitle(request.getTitle());
        if (request.isCompleted()) todo.setCompleted(true);
        todoRepository.saveAndFlush(todo);
        return TodoApiResponse.builder()
                .success(true)
                .todo(todo)
                .build();
    }

    @Override
    public ApiResponse deleteTodo(Long id) {
        todoRepository.deleteById(id);
        return ApiResponse.builder()
                .success(true)
                .message("Todo deleted successfully")
                .build();
    }

    @Override
    public GetAllTodosResponse getTodos(Sort.Direction direction) {
        return GetAllTodosResponse.builder()
                .data(todoRepository.findAll(Sort.by(direction, "createdAt")))
                .success(true)
                .build();
    }

    @Override
    public void createTodos(List<TodoRequest> todoRequests) throws InterruptedException {

        for (TodoRequest request : todoRequests) {
            createTodo(request);
            Thread.sleep(1001);
        }
    }

    @Override
    public TodoApiResponse getTodoById(Long id) throws TodoApiException {
        return TodoApiResponse.builder()
                .todo(
                        todoRepository.findById(id).orElseThrow(
                                () -> new TodoApiException("todo not found")
                        ))
                .build();
    }


}
