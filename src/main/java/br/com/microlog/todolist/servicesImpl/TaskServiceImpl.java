package br.com.microlog.todolist.servicesImpl;

import br.com.microlog.todolist.dto.task.TaskCreateDto;
import br.com.microlog.todolist.models.TaskModel;
import br.com.microlog.todolist.repositories.ITaskRepository;
import br.com.microlog.todolist.repositories.IUserRepository;
import br.com.microlog.todolist.services.TaskService;
import br.com.microlog.todolist.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
  final ITaskRepository taskRepository;

  final IUserRepository userRepository;

  private final ModelMapper modelMapper;

  public TaskServiceImpl(ITaskRepository taskRepository, IUserRepository userRepository) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;

    this.modelMapper = new ModelMapper();
  }

  @Override
  public TaskModel create(TaskCreateDto taskDto) throws Exception {
    var currentDate = LocalDateTime.now();

    if (currentDate.isAfter(taskDto.getStartAt()) || currentDate.isAfter(taskDto.getEndAt()))
      throw new Exception("A data de início / data de término deve ser maior do que a data atual");

    if (taskDto.getStartAt().isAfter(taskDto.getEndAt()))
      throw new Exception("A data de início deve ser menor do que a data de término");

    TaskModel task = modelMapper.map(taskDto, TaskModel.class);

    return this.taskRepository.save(task);
  }

  @Override
  public List<TaskModel> listByIdUser(UUID idUser) throws Exception {
    userExist(idUser);

    return this.taskRepository.findByIdUser(idUser);
  }

  @Override
  public TaskModel update(TaskCreateDto taskDto, UUID id) throws Exception {
    userExist(taskDto.getIdUser());
    var task = taskExist(id);

    Utils.copyNonNullProperties(taskDto, task);

    return this.taskRepository.save(task);
  }

  private TaskModel taskExist(UUID idTask) throws Exception {
    return this.taskRepository.findById(idTask).orElseThrow(() -> new Exception("Task não cadastrada"));
  }

  private void userExist(UUID idUser) throws Exception {
    this.userRepository.findById(idUser).orElseThrow(() -> new Exception("Usuário não cadastrado"));
  }
}
