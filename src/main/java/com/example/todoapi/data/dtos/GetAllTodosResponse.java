package com.example.todoapi.data.dtos;

import com.example.todoapi.data.models.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllTodosResponse {
    private List<Todo> data;
    private boolean success;


}
