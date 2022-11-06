package rocketshell.vialactea.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rocketshell.vialactea.config.auth.Roles;
import rocketshell.vialactea.config.auth.jwt.Jwt;
import rocketshell.vialactea.config.auth.jwt.JwtTool;
import rocketshell.vialactea.domain.PessoaFisica;
import rocketshell.vialactea.domain.Usuario;
import rocketshell.vialactea.dto.sign.SignIn;
import rocketshell.vialactea.dto.sign.SignUp;
import rocketshell.vialactea.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

  @Lazy
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTool jwtTokenTool;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PessoaFisicaService pessoaFisicaService;

  @Value("${rocket-shell.auth.admin.email}")
  private String adminUsername;

  @Value("${rocket-shell.auth.admin.password}")
  private String adminPassword;

  @Override
  public Usuario loadUserByUsername(String email) throws UsernameNotFoundException {
    return usuarioRepository.findUsuarioByEmail(email);
  }

  public Jwt signIn(SignIn signIn) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    Usuario userDetails = (Usuario) authentication.getPrincipal();

    return jwtTokenTool.generateToken(userDetails);

  }

  public Usuario signUp(SignUp signUp) {

    if (usuarioRepository.existsByEmail(signUp.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use!");
    }

    Usuario usuario = Usuario.builder()
        .email(signUp.getEmail())
        .password(passwordEncoder.encode(signUp.getPassword()))
        .build();

    Usuario novoUsuario = usuarioRepository.save(usuario);

    PessoaFisica pessoaFisicaVinculada = signUp.getPessoaFisica();
    pessoaFisicaVinculada.setUsuario(novoUsuario);

    pessoaFisicaService.create(pessoaFisicaVinculada);

    return novoUsuario;

  }

  @PostConstruct
  public void registerAdminUser() {

    if (!usuarioRepository.existsByEmail(this.adminUsername)) {
      Usuario admin = Usuario.builder()
              .email(this.adminUsername)
              .password(passwordEncoder.encode(this.adminPassword))
              .build();

      admin.getRoles().add(Roles.ROLE_ADMIN);

      usuarioRepository.save(admin);
    }

  }

}
