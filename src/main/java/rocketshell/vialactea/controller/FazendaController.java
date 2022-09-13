package rocketshell.vialactea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.Fazenda;
import rocketshell.vialactea.repository.FazendaRepository;
import rocketshell.vialactea.service.FazendaService;

@Controller
@RequestMapping("/Fazendas")
public class FazendaController extends BaseController<Fazenda,FazendaRepository,FazendaService> {
  
}
