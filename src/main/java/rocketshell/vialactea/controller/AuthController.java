package rocketshell.vialactea.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.config.auth.jwt.Jwt;
import rocketshell.vialactea.domain.Users;
import rocketshell.vialactea.dto.sign.SignIn;
import rocketshell.vialactea.dto.sign.SignUp;
import rocketshell.vialactea.service.UsersService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private UsersService usersService;

  @PostMapping("/signin")
  public ResponseEntity<Jwt> signIn(@Valid @RequestBody SignIn signIn) {
    return ResponseEntity.ok(usersService.signIn(signIn));
  }

  @PostMapping("/signup")
  public ResponseEntity<Users> signIn(@Valid @RequestBody SignUp signUp) {
    return ResponseEntity.ok(usersService.signUp(signUp));
  }

}
