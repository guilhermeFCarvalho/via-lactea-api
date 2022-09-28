package rocketshell.vialactea.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import rocketshell.vialactea.domain.Pessoa;
import rocketshell.vialactea.domain.PessoaFisica;
import rocketshell.vialactea.repository.PessoaFisicaRepository;

@ExtendWith(MockitoExtension.class)

public class PessoaFisicaServiceTest {
    
    @Mock 
    private PessoaFisicaRepository repository;

    private PessoaFisicaService service;

    @BeforeEach 
    private void setUp(){
        PessoaFisica pessoa = new PessoaFisica("guilherme", "carvalho", null);
        this.service = new PessoaFisicaService();

        service.create(pessoa);
    }

    @Test
    private void getAll(){
        assertEquals(service.getAll(), repository.findAll());
    }

}
