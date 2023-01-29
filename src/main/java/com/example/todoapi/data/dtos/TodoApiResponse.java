package com.example.todoapi.data.dtos;

import com.example.todoapi.data.models.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoApiResponse {
    private Todo todo;
    private boolean success;
}
