package rocketshell.vialactea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.ConsultaVeterinario;
import rocketshell.vialactea.repository.ConsultaVeterinarioRepository;
import rocketshell.vialactea.service.ConsultaVeterinarioService;

@Controller
@RestController
@RequestMapping("/api/ConsultaVeterinario")
public class ConsultaVeterinarioController extends BaseController<ConsultaVeterinario,ConsultaVeterinarioRepository,ConsultaVeterinarioService>{

}
