package rocketshell.vialactea.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rocketshell.vialactea.base.BaseService;
import rocketshell.vialactea.domain.ReciboDeVenda;
import rocketshell.vialactea.repository.ReciboDeVendaRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReciboDeVendaService extends BaseService<ReciboDeVenda, ReciboDeVendaRepository> {

    private final ReciboDeVendaRepository repository;

    public Page<ReciboDeVenda> listarRecibosDeUmaPropriedade(Long id , Pageable pageble) {
        return repository.findReciboDeVendaByPropriedadeId(id,pageble);
    }

}
