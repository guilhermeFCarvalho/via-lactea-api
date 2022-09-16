package rocketshell.vialactea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketshell.vialactea.domain.Pessoa;



public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
