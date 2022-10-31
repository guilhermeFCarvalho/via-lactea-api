package rocketshell.vialactea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.PessoaFisica;
import rocketshell.vialactea.repository.PessoaFisicaRepository;
import rocketshell.vialactea.service.PessoaFisicaService;

import java.util.UUID;

@RestController
@RequestMapping("/api/pessoas-fisicas")
public class PessoaFisicaController extends BaseController<PessoaFisica, PessoaFisicaRepository, PessoaFisicaService> {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @GetMapping("/usuario/{id}")
    public PessoaFisica buscarPeloUsuarioId(@PathVariable("id") UUID id){
        return pessoaFisicaService.getByUserId(id);
    }

}
