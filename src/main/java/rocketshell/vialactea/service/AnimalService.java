package rocketshell.vialactea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocketshell.vialactea.base.BaseService;
import rocketshell.vialactea.domain.Animal;
import rocketshell.vialactea.repository.AnimalRepository;

@Service
public class AnimalService extends BaseService<Animal, AnimalRepository>{

    @Autowired
    private AnimalRepository repository;

    public List<Animal> getAnimalByFazendaId(Long fazendaId){
        return repository.getAnimalByFazendaId(fazendaId);
    }

}
