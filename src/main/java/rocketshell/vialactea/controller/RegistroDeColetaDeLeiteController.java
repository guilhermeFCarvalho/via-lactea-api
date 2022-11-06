package rocketshell.vialactea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.RegistroDeColetaDeLeite;
import rocketshell.vialactea.repository.RegistroDeColetaDeLeiteRepository;
import rocketshell.vialactea.service.RegistroDeColetaDeLeiteService;

@Controller
@RestController
@RequestMapping("/api/registros-coletas-de-leite")
public class RegistroDeColetaDeLeiteController extends BaseController<RegistroDeColetaDeLeite,RegistroDeColetaDeLeiteRepository,RegistroDeColetaDeLeiteService>{

}
