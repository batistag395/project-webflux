package br.com.udemy.tasks.service;

import br.com.udemy.tasks.model.Task;
import br.com.udemy.tasks.repository.TaskCustomRepository;
import br.com.udemy.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskServiceTest {
    @InjectMocks
    private TaskService service;
    @Mock
    private TaskRepository repository;
    @Mock
    private TaskCustomRepository customRepository;

    @Test
    void testInsert() {
        Task task = mock(Task.class);
        when(repository.save(any())).thenReturn(task);

        StepVerifier.create(service.insert(task))
                .then(() -> verify(repository, times(1)).save(any()))
                .expectNext(task)
                .expectComplete();

    }

    @Test
    void findPaginated() {
    }

    @Test
    void deleteById() {
        var id = "1";
        StepVerifier.create(service.deleteById(id))
                .then(()-> verify(repository, times(1)).deleteById(id))
                .expectComplete();
    }
}