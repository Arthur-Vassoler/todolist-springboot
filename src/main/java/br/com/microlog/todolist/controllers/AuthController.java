package br.com.microlog.todolist.controllers;

import br.com.microlog.todolist.communs.JwtUtils;
import br.com.microlog.todolist.dto.user.UserCreateDto;
import br.com.microlog.todolist.dto.user.UserLoginDto;
import br.com.microlog.todolist.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final UserService userService;

  public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService) {
    super();
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseEntity<?> create(@RequestBody UserCreateDto userDto) {
    try {
      return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/signin")
  public ResponseEntity<?> signin(@RequestBody UserLoginDto login) {
    Authentication authentication =
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String tokenJwt = jwtUtils.generateTokenFromUsername(login.getUsername());

    return ResponseEntity.ok().body(tokenJwt);
  }
}