package rocketshell.vialactea.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.Propriedade;
import rocketshell.vialactea.repository.PropriedadeRepository;
import rocketshell.vialactea.service.PropriedadeService;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController extends BaseController<Propriedade, PropriedadeRepository, PropriedadeService> {

}
