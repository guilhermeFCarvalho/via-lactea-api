package rocketshell.vialactea.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.Produtor;
import rocketshell.vialactea.repository.ProdutorRepository;
import rocketshell.vialactea.service.ProdutorService;

@RestController
@RequestMapping("/produtores")
public class ProdutorController extends BaseController<Produtor, ProdutorRepository, ProdutorService> {
    
}
