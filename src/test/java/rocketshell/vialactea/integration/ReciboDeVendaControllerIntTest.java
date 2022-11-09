package rocketshell.vialactea.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.SneakyThrows;
import rocketshell.vialactea.domain.ReciboDeVenda;
import rocketshell.vialactea.repository.ReciboDeVendaRepository;

public class ReciboDeVendaControllerIntTest extends IntegrationTest {

	@Autowired
    public ReciboDeVendaRepository reciboDeVendaRepository;
	
	@Test
	@SneakyThrows
    void shouldDeleteCurso() {
        ReciboDeVenda reciboDeVenda = new ReciboDeVenda();
        reciboDeVenda.setObservacoes("observação do registro de venda");
        reciboDeVenda.setPago(true);
        //curso.setNome("Deletar este curso");
       // curso = cursoRepository.save(curso);

       // mockMvc.perform(delete("/api/curso/" + curso.getId().toString())
         //       .contentType(MediaType.APPLICATION_JSON))
        //        .andExpect(status().isOk());

        //Optional<Curso> findById = cursoRepository.findById(curso.getId());

        //assertFalse(findById.isPresent());

    }


}
