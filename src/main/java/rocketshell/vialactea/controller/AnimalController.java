package rocketshell.vialactea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.Animal;
import rocketshell.vialactea.repository.AnimalRepository;
import rocketshell.vialactea.service.AnimalService;

@Controller
@RestController
@RequestMapping("/api/animais")
public class AnimalController extends BaseController<Animal,AnimalRepository,AnimalService>{
    
    @Autowired
    private AnimalService service;
    
    @GetMapping("/fazenda/{id}")
    public List<Animal> getByFazendaId(@PathVariable("id") Long id) {
      return service.getAnimalByFazendaId(id);
    }
}
