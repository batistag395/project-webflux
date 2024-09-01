package br.com.udemy.tasks.controller;

import br.com.udemy.tasks.controller.converter.TaskDtoConverter;
import br.com.udemy.tasks.controller.dto.TaskDto;
import br.com.udemy.tasks.model.Task;
import br.com.udemy.tasks.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
        when(converter.convert(any(Task.class))).thenReturn(new TaskDto());
        when(service.insert(any())).thenReturn(Mono.just(new Task()));

        WebTestClient client = WebTestClient.bindToController(controller).build();
        client.post()
                .uri("/task")
                .bodyValue(new Task())
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskDto.class);
    }

    @Test
    void testGetTasks() {
        Page<Task> page = mock(Page.class);
        when(service.findPaginated(any(), anyInt(), anyInt())).thenReturn(page);
        WebTestClient client = WebTestClient.bindToController(controller).build();
        client.get()
                .uri("/task")
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskDto.class);

    }

    @Test
    void testDelete() {
        var anyId = "id";
        when(service.deleteById(anyId)).thenReturn(Mono.empty());

        WebTestClient client = WebTestClient.bindToController(controller).build();
        client.delete()
                .uri("/task/" + anyId)
                .exchange()
                .expectStatus().isNoContent();
    }
}