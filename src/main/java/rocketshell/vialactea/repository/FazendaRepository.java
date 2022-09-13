package rocketshell.vialactea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketshell.vialactea.domain.Fazenda;

public interface FazendaRepository extends JpaRepository<Fazenda, Long> {
  
}
