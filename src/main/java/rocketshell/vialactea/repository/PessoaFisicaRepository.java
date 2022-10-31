package rocketshell.vialactea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketshell.vialactea.domain.PessoaFisica;

import java.util.UUID;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

    PessoaFisica findPessoaFisicaByUsuarioId(UUID id);

}
