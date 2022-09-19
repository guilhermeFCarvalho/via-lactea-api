package rocketshell.vialactea.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.PessoaJuridica;
import rocketshell.vialactea.repository.PessoaJuridicaRepository;
import rocketshell.vialactea.service.PessoaJuridicaService;

@RestController
@RequestMapping("/pessoas-juridicas")
public class PessoaJuridicaController extends BaseController<PessoaJuridica, PessoaJuridicaRepository, PessoaJuridicaService> {

    
}
