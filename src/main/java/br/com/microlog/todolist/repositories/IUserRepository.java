package br.com.microlog.todolist.repositories;

import br.com.microlog.todolist.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
  Optional<UserModel> findByUsername(String username);
}
