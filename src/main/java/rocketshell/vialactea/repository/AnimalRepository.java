package rocketshell.vialactea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketshell.vialactea.domain.Animal;


public interface AnimalRepository extends JpaRepository<Animal, Long> {

   public List<Animal> getAnimalByFazendaId(Long fazendaId);

}
