package br.com.udemy.tasks.service;

import br.com.udemy.tasks.model.Task;
import br.com.udemy.tasks.repository.TaskCustomRepository;
import br.com.udemy.tasks.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final TaskCustomRepository customRepository;

    public Mono<Task> insert(Task task){
        return Mono.just(task).map(task::insert).flatMap(this::save);
    }
    public Page<Task> findPaginated(Task task, Integer pageNumber, Integer pageSize){
        return customRepository.findPaginated(task, pageNumber, pageSize);
    }
    public Mono<Void> deleteById(String id){
        return Mono.fromRunnable(()-> repository.deleteById(id));
    }
    private Mono<Task> save(Task task){
        return Mono.just(task).map(repository::save);
    }
}
