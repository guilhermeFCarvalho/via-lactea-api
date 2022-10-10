package rocketshell.vialactea.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.PessoaFisica;
import rocketshell.vialactea.repository.PessoaFisicaRepository;
import rocketshell.vialactea.service.PessoaFisicaService;

@RestController
@RequestMapping("api//pessoas-fisicas")
public class PessoaFisicaController extends BaseController<PessoaFisica, PessoaFisicaRepository, PessoaFisicaService> {

}
