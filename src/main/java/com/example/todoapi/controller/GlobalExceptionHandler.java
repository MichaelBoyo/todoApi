package com.example.todoapi.controller;

import com.example.todoapi.data.dtos.ApiResponse;
import com.example.todoapi.utils.TodoApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TodoApiException.class)
    public ResponseEntity<ApiResponse> handleTodoApiException(TodoApiException e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .message(e.getLocalizedMessage())
                .success(false)
                .build());
    }
}
