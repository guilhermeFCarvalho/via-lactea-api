package rocketshell.vialactea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.CarteiraVacina;
import rocketshell.vialactea.repository.CarteiraVacinaRepository;
import rocketshell.vialactea.service.CarteiraVacinaService;

@Controller
@RestController
@RequestMapping("/api/carteiras-vacinas")
public class CarteiraVacinaController extends BaseController<CarteiraVacina,CarteiraVacinaRepository,CarteiraVacinaService>{

}
