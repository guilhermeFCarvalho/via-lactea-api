package rocketshell.vialactea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketshell.vialactea.domain.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
    
}
