package rocketshell.vialactea.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.config.auth.jwt.Jwt;
import rocketshell.vialactea.domain.Usuario;
import rocketshell.vialactea.dto.SignIn;
import rocketshell.vialactea.dto.SignUp;
import rocketshell.vialactea.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/signin", consumes = {"application/json"})
    public ResponseEntity<Jwt> signIn(@Valid @RequestBody SignIn signIn) {
        return ResponseEntity.ok(usuarioService.signIn(signIn));
    }

    @PostMapping("/signup")
    public ResponseEntity<Usuario> signIn(@Valid @RequestBody SignUp signUp) {
        return ResponseEntity.ok(usuarioService.signUp(signUp));
    }

}
