package rocketshell.vialactea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketshell.vialactea.domain.Animal;


public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
