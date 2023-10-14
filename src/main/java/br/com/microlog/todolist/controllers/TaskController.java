package br.com.microlog.todolist.controllers;

import br.com.microlog.todolist.dto.task.TaskCreateDto;
import br.com.microlog.todolist.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping("/")
  public ResponseEntity<?> create(@RequestBody TaskCreateDto userDto) {
    try {
      return new ResponseEntity<>(taskService.create(userDto), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/user/{idUser}")
  public ResponseEntity<?> listByIdUser(@PathVariable UUID idUser) {
    try {
      return new ResponseEntity<>(taskService.listByIdUser(idUser), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody TaskCreateDto task, @PathVariable UUID id) {
    try {
      return new ResponseEntity<>(taskService.update(task, id), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
