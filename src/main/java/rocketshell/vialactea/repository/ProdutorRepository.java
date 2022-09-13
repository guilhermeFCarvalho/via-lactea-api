package rocketshell.vialactea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketshell.vialactea.domain.Produtor;

public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
    
}
