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
import rocketshell.vialactea.domain.Usuario;
import rocketshell.vialactea.dto.SignIn;
import rocketshell.vialactea.dto.SignUp;
import rocketshell.vialactea.config.auth.jwt.JwtTool;
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

    @Value("${rocket-shell.auth.admin.username}")
    private String adminUsername;

    @Value("${rocket-shell.auth.admin.password}")
    private String adminPassword;

    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findUsersByUsername(username);
    }

    public Jwt signIn(SignIn signIn) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario userDetails = (Usuario) authentication.getPrincipal();

        return jwtTokenTool.generateToken(userDetails);

    }

    public Usuario signUp(SignUp signUp) {

        if (usuarioRepository.existsByEmail(signUp.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use!");
        }

        Usuario users = Usuario.builder()
                .password(passwordEncoder.encode(signUp.getPassword()))
                .email(signUp.getEmail())
                .build();

        return usuarioRepository.save(users);
    }

    @PostConstruct
    public void registerAdminUser() {

        if (!usuarioRepository.existsByUsername(this.adminUsername)) {
            Usuario admin = Usuario.builder()
                    .password(passwordEncoder.encode(this.adminPassword))
                    .build();

            admin.getRoles().add(Roles.ROLE_ADMIN);

            usuarioRepository.save(admin);
        }

    }

}
