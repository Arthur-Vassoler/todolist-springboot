package br.com.microlog.todolist.services;

import br.com.microlog.todolist.dto.task.TaskCreateDto;
import br.com.microlog.todolist.models.TaskModel;

import java.util.List;
import java.util.UUID;

public interface TaskService {
  TaskModel create(TaskCreateDto userDto) throws Exception;

  List<TaskModel> listByIdUser(UUID idUser) throws Exception;

  TaskModel update(TaskCreateDto task, UUID id) throws Exception;
}
