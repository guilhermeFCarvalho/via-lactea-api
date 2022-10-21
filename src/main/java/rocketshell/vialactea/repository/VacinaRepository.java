package rocketshell.vialactea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketshell.vialactea.domain.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina,Long> {
    
}
