package rocketshell.vialactea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.ReciboDeVenda;
import rocketshell.vialactea.repository.ReciboDeVendaRepository;
import rocketshell.vialactea.service.ReciboDeVendaService;

@Controller
@RestController
@RequestMapping("/api/recibo-de-venda")
public class ReciboDeVendaController extends BaseController<ReciboDeVenda, ReciboDeVendaRepository, ReciboDeVendaService>{
  
}
