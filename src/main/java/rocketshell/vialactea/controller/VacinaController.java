package rocketshell.vialactea.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.Vacina;
import rocketshell.vialactea.repository.VacinaRepository;
import rocketshell.vialactea.service.VacinaService;

@RestController
@RequestMapping("/api/vacinas")
public class VacinaController extends BaseController<Vacina,VacinaRepository,VacinaService>{
    
}
