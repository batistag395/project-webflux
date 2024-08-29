package br.com.udemy.tasks.controller.dto;

import br.com.udemy.tasks.enums.TaskState;
import lombok.Data;

@Data
public class TaskDto {
    private String id;
    private String title;
    private String description;
    private int priority;
    private TaskState state;
}
