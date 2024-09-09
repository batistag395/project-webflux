package br.com.udemy.tasks.service;

import br.com.udemy.tasks.model.Task;
import br.com.udemy.tasks.repository.TaskCustomRepository;
import br.com.udemy.tasks.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Log4j2
public class TaskService {
    private final TaskRepository repository;
    private final TaskCustomRepository customRepository;

    public Mono<Task> insert(Task task){
        return Mono.just(task)
                .doOnError(error -> log.error("Error during save task. Title {}", task.getTitle(), error))
                .map(task::insert).flatMap(this::save);
    }
    public Page<Task> findPaginated(Task task, Integer pageNumber, Integer pageSize){
        return customRepository.findPaginated(task, pageNumber, pageSize);
    }
    public Mono<Void> deleteById(String id){
        return Mono.fromRunnable(()-> repository.deleteById(id));
    }
    private Mono<Task> save(Task task){
        return Mono.just(task)
                .doOnNext(it -> log.info("Saving task with title {}", task.getTitle()))
                .map(repository::save);
    }
}
