package com.example.todoapi;

import com.example.todoapi.data.dtos.TodoRequest;
import com.example.todoapi.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DummyDataLoader implements ApplicationRunner {
    private final TodoService todoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        todoService.createTodos(List.of(
                TodoRequest.builder()
                        .title("Step 1")
                        .body("Clone app from Github")
                        .completed(true)
                        .build(),
                TodoRequest.builder()
                        .title("Step 2")
                        .body("Run 'mvn package' ")
                        .completed(true)
                        .build(),
                TodoRequest.builder()
                        .title("Step 3")
                        .body("Run 'java -jar target/todoApi-0.0.1-SNAPSHOT.jar'")
                        .completed(true)
                        .build(),
                TodoRequest.builder()
                        .title("Step 4")
                        .body("Test Endpoints")
                        .build()));




    }
}
