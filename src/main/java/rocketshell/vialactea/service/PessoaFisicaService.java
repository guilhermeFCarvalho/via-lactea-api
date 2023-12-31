package rocketshell.vialactea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocketshell.vialactea.base.BaseService;
import rocketshell.vialactea.domain.PessoaFisica;
import rocketshell.vialactea.repository.PessoaFisicaRepository;

import java.util.UUID;

@Service
public class PessoaFisicaService extends BaseService<PessoaFisica, PessoaFisicaRepository> {

    @Autowired
    private PessoaFisicaRepository repository ;

    public PessoaFisica getByUserId(UUID id) {
        return repository.findPessoaFisicaByUsuarioId(id);
    }

}
