package br.com.udemy.tasks.controller.converter;

import br.com.udemy.tasks.controller.dto.TaskDto;
import br.com.udemy.tasks.enums.TaskState;
import br.com.udemy.tasks.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskDtoConverter {
    public TaskDto convert(Task task){
        return Optional.ofNullable(task)
                .map(source -> {
                    var dto = new TaskDto();
                    dto.setId(source.getId());
                    dto.setTitle(source.getTitle());
                    dto.setDescription(source.getDescription());
                    dto.setPriority(source.getPriority());
                    dto.setState(source.getState());
                    return dto;
                }).orElse(null);
    }
    public Task convert(TaskDto taskDto){
        return Optional.ofNullable(taskDto)
                .map(source -> Task.builder()
                        .withId(source.getId())
                        .withTitle(source.getTitle())
                        .withDescription(source.getDescription())
                        .withPriority(source.getPriority())
                        .withState(source.getState())
                        .build()
                ).orElse(null);
    }
    public Task convert(String id, String title, String description, int priority, TaskState taskState){
        return Task.builder()
                .withId(id)
                .withTitle(title)
                .withDescription(description)
                .withPriority(priority)
                .withState(taskState)
                .build();
    }
    public List<TaskDto> convertList(List<Task> taskList){
        return Optional.ofNullable(taskList)
                .map(array -> array.stream().map(this::convert).collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }
}
