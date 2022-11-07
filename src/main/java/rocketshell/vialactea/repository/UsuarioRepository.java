package rocketshell.vialactea.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketshell.vialactea.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {


  boolean existsByEmail(String email);

  Usuario findUsuarioByEmail(String email);

}
