package br.com.microlog.todolist.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateDto {
  private UUID idUser;
  private String description;
  private String title;
  private String priority;
  private LocalDateTime startAt;
  private LocalDateTime endAt;
}
