package rocketshell.vialactea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.Endereco;
import rocketshell.vialactea.repository.EnderecoRepository;
import rocketshell.vialactea.service.EnderecoService;


@Controller
@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController extends BaseController<Endereco, EnderecoRepository, EnderecoService> {

}
