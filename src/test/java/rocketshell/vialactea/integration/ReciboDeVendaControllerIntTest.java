package rocketshell.vialactea.integration;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import lombok.SneakyThrows;
import rocketshell.vialactea.domain.PessoaJuridica;
import rocketshell.vialactea.domain.Propriedade;
import rocketshell.vialactea.domain.ReciboDeVenda;
import rocketshell.vialactea.repository.ReciboDeVendaRepository;

public class ReciboDeVendaControllerIntTest extends IntegrationTest {

	@Autowired
    public ReciboDeVendaRepository reciboDeVendaRepository;
	
	@Test
	@SneakyThrows
    void shouldDeleteCurso() {
		BigDecimal bigdecimal = new BigDecimal(200);
		LocalDate data = LocalDate.now();
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		Propriedade propriedade = new Propriedade();
		
		propriedade.setCar("1111");
		propriedade.setTelefone("22222");
		
		pessoaJuridica.setCNPJ("22222");
		pessoaJuridica.setInscricaoEstadual("222");
		pessoaJuridica.setTelefone("222222");
	
        ReciboDeVenda reciboDeVenda = new ReciboDeVenda();
        reciboDeVenda.setObservacoes("observação do registro de venda");
        reciboDeVenda.setPago(true);
        reciboDeVenda.setQuantidadeLeiteVendida(bigdecimal);
        reciboDeVenda.setDataDaVenda(data);
        reciboDeVenda.setPessoaJuridica(pessoaJuridica);
        reciboDeVenda.setPropriedade(propriedade);
        
      
        reciboDeVenda = reciboDeVendaRepository.save(reciboDeVenda);
        
       
        Optional<ReciboDeVenda> repository = reciboDeVendaRepository.findById(reciboDeVenda.getId());
        assertFalse(repository.isEmpty());
        
        mockMvc.perform(delete("/api/recibo-de-venda/" + reciboDeVenda.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<ReciboDeVenda> findById = reciboDeVendaRepository.findById(reciboDeVenda.getId());

        assertFalse(findById.isPresent());

    }


}
