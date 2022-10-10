package rocketshell.vialactea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.Fazenda;
import rocketshell.vialactea.repository.FazendaRepository;
import rocketshell.vialactea.service.FazendaService;

@Controller
@RestController
@RequestMapping("/fazendas")
public class FazendaController extends BaseController<Fazenda, FazendaRepository, FazendaService> {

}
