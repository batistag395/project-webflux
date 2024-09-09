package br.com.udemy.tasks.controller;

import br.com.udemy.tasks.controller.converter.TaskDtoConverter;
import br.com.udemy.tasks.controller.dto.TaskDto;
import br.com.udemy.tasks.enums.TaskState;
import br.com.udemy.tasks.model.Task;
import br.com.udemy.tasks.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService service;
    private final TaskDtoConverter converter;

    @PostMapping
    public Mono<TaskDto> createTask(@RequestBody TaskDto task){
        return service.insert(converter.convert(task))
                .doOnNext(it -> log.info("Saved task with id {}", it.getId()))
                .map(converter::convert);
    }
    @GetMapping
    public Page<TaskDto> getTasks(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, defaultValue = "0") int priority,
            @RequestParam(required = false)TaskState taskState,
            @RequestParam(value = "pageNumber", defaultValue = "0")Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize
    ){
        return service.findPaginated(
                converter.convert(
                        id, title, description, priority, taskState
                ), pageNumber, pageSize).map(converter::convert);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id){
        return Mono.just(id)
                .doOnNext(it -> log.info("Deleting task with id {{}", id))
                .flatMap(service::deleteById);
    }
}
