package br.com.microlog.todolist.dto.user;

import lombok.Data;

@Data
public class UserLoginDto {
  private String username;
  private String password;
}
