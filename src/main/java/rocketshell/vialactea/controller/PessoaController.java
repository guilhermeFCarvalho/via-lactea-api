package rocketshell.vialactea.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.Pessoa;
import rocketshell.vialactea.repository.PessoaRepository;
import rocketshell.vialactea.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController extends BaseController<Pessoa, PessoaRepository, PessoaService> {
    
}
