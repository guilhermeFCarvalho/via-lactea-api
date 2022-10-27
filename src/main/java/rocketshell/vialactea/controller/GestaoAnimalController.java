package rocketshell.vialactea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.GestaoAnimal;
import rocketshell.vialactea.repository.GestaoAnimalRepository;
import rocketshell.vialactea.service.GestaoAnimalService;

@Controller
@RestController
@RequestMapping("/api/gestao-animais")
public class GestaoAnimalController extends BaseController<GestaoAnimal,GestaoAnimalRepository,GestaoAnimalService>{

}
