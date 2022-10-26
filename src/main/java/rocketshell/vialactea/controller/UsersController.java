package rocketshell.vialactea.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.domain.Usuario;

@RestController
@RequestMapping("/api/users")
public class UsersController {

  @GetMapping("/me")
  public ResponseEntity<Usuario> getMe(@AuthenticationPrincipal Usuario user) {
    return ResponseEntity.ok(user);
  }

}
