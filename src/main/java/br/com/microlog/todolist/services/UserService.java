package br.com.microlog.todolist.services;

import br.com.microlog.todolist.dto.user.UserCreateDto;
import br.com.microlog.todolist.models.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
  UserModel create(UserCreateDto user);

  UserModel findByUsername(String username) throws Exception;

  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
