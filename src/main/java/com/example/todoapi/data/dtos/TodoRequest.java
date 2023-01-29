package com.example.todoapi.data.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@RequiredArgsConstructor
public class TodoRequest {
    @NonNull
    private String title;
    @NonNull
    private String body;
    private boolean completed;
}
