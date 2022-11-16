package rocketshell.vialactea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocketshell.vialactea.base.BaseController;
import rocketshell.vialactea.domain.ReciboDeVenda;
import rocketshell.vialactea.repository.ReciboDeVendaRepository;
import rocketshell.vialactea.service.ReciboDeVendaService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/recibo-de-venda")
public class ReciboDeVendaController extends BaseController<ReciboDeVenda,
                                                            ReciboDeVendaRepository,
                                                            ReciboDeVendaService>{

    @Autowired
    private ReciboDeVendaService service;

    @GetMapping("/propriedade/{id}")
    public ResponseEntity<Page<ReciboDeVenda>> buscarRecibos(@PathVariable("id") Long id, Pageable pageable) {
        return ResponseEntity.ok(service.listarRecibosDeUmaPropriedade(id,pageable));
    }

    @GetMapping("/propriedade/{id}/ultimo-registro")
    public ResponseEntity<ReciboDeVenda> ultimoRegitro(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.buscarUltimoRegistroDaPropriedade(id));
    }


}
