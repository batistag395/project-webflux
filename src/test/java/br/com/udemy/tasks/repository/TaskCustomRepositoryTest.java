package br.com.udemy.tasks.repository;

import br.com.udemy.tasks.enums.TaskState;
import br.com.udemy.tasks.model.Task;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@SpringBootTest
class TaskCustomRepositoryTest {
    @InjectMocks private TaskCustomRepository customRepository;
    @Mock private Task task;
    @Mock private MongoOperations mongoOperations;

    @Test
    void testFindPaginatedCustemRepository() {
        when(mongoOperations.find(any(), any())).thenReturn(List.of(task));
        when(mongoOperations.count(any(), eq(Task.class))).thenReturn(1l);
        when(task.getPriority()).thenReturn(1);
        when(task.getState()).thenReturn(TaskState.INSERT);
        Page<Task> result = customRepository.findPaginated(task, 0, 10);

        assertNotNull(result);
        assertEquals(1, result.getNumberOfElements());
    }
    @Test
    void testFindPaginatedCustemRepositoryWithoutPriority() {
        when(mongoOperations.find(any(), any())).thenReturn(List.of(task));
        when(task.getState()).thenReturn(TaskState.INSERT);
        Page<Task> result = customRepository.findPaginated(task, 0, 10);

        assertNotNull(result);
        assertEquals(1, result.getNumberOfElements());
    }
    @Test
    void testFindPaginatedCustemRepositoryWithoutTaskState() {
        when(mongoOperations.find(any(), any())).thenReturn(List.of(task));
        when(task.getPriority()).thenReturn(1);
        Page<Task> result = customRepository.findPaginated(task, 0, 10);

        assertNotNull(result);
        assertEquals(1, result.getNumberOfElements());
    }
}