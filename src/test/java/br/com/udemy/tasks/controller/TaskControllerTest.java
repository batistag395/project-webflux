package br.com.udemy.tasks.controller;

import br.com.udemy.tasks.controller.converter.TaskDtoConverter;
import br.com.udemy.tasks.controller.dto.TaskDto;
import br.com.udemy.tasks.model.Task;
import br.com.udemy.tasks.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaskControllerTest {
    @InjectMocks
    private TaskController controller;

    @Mock
    private TaskService service;
    @Mock
    private TaskDtoConverter converter;

    @Test
    void testCreateTask() {
        Mockito.when(converter.convert(Mockito.any(Task.class))).thenReturn(new TaskDto());
        Mockito.when(service.insert(Mockito.any())).thenReturn(Mono.just(new Task()));

        WebTestClient client = WebTestClient.bindToController(controller).build();
        client.post()
                .uri("/task")
                .bodyValue(new Task())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Task.class);
    }

    @Test
    void testGetTasks() {
    }

    @Test
    void testDelete() {
    }
}