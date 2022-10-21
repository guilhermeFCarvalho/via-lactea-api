package rocketshell.vialactea.controller;

import org.springframework.stereotype.Controller;
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
     
}
