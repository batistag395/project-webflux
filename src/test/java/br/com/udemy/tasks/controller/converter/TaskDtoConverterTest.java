package br.com.udemy.tasks.controller.converter;

import br.com.udemy.tasks.controller.dto.TaskDto;
import br.com.udemy.tasks.enums.TaskState;
import br.com.udemy.tasks.model.Task;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
class TaskDtoConverterTest {
    @InjectMocks
    private TaskDtoConverter taskDtoConverter;

    @Test
    void convert() {
        Task task = mock(Task.class);
        TaskDto taskDto = taskDtoConverter.convert(task);
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getDescription(), task.getDescription());
        assertEquals(taskDto.getPriority(), task.getPriority());
        assertEquals(taskDto.getState(), task.getState());
    }

    @Test
    void testConvert() {
        TaskDto taskDto = mock(TaskDto.class);
        Task task = taskDtoConverter.convert(taskDto);
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getDescription(), taskDto.getDescription());
        assertEquals(task.getPriority(), taskDto.getPriority());
        assertEquals(task.getState(), taskDto.getState());
    }

    @Test
    void testConvert1() {
        var id_title_description = "mock_id_title_description";
        var priority = 1;
        var state = mock(TaskState.class);
        Task task = taskDtoConverter.convert(id_title_description, id_title_description, id_title_description, priority, state);

        assertEquals(id_title_description, task.getId());
        assertEquals(id_title_description, task.getTitle());
        assertEquals(id_title_description, task.getDescription());
        assertEquals(priority, task.getPriority());
        assertEquals(state, task.getState());
    }
}