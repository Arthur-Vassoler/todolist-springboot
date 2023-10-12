package br.com.microlog.todolist.servicesImpl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.microlog.todolist.dto.user.UserCreateDto;
import br.com.microlog.todolist.models.UserModel;
import br.com.microlog.todolist.repositories.IUserRepository;
import br.com.microlog.todolist.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  final IUserRepository userRepository;

  private final ModelMapper modelMapper;

  public UserServiceImpl(IUserRepository userRepository) {
    this.userRepository = userRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public UserModel create(UserCreateDto user) {
    var passwordHash = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

    user.setPassword(passwordHash);

    UserModel userModel = modelMapper.map(user, UserModel.class);

    return this.userRepository.save(userModel);
  }

  @Override
  public UserModel findByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
  }
}
