package rocketshell.vialactea.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.SneakyThrows;
import rocketshell.vialactea.domain.Endereco;
import rocketshell.vialactea.domain.PessoaFisica;
import rocketshell.vialactea.domain.Propriedade;
import rocketshell.vialactea.repository.PessoaFisicaRepository;
import utils.CustomPageImpl;

public class PessoaFisicaControllerIntTest extends IntegrationTest {
	
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
    
    @Test
	@SneakyThrows
    void shouldDeletePessoaFisica() {
		
		PessoaFisica pessoaFisica = new PessoaFisica();
		
	    pessoaFisica.setNome("Maria");
	    pessoaFisica.setSobrenome("Lima");
		
		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
		
		Optional<PessoaFisica> repository = pessoaFisicaRepository.findById(pessoaFisica.getId());
        assertFalse(repository.isEmpty());
        
        mockMvc.perform(delete("/api/pessoas-fisicas/" + pessoaFisica.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<PessoaFisica> findById = pessoaFisicaRepository.findById(pessoaFisica.getId());

        assertFalse(findById.isPresent());
	}
    
    @Test
    @SneakyThrows
    void shouldSavePessoaFisica() {
    	
    	Propriedade propriedade1 = new Propriedade();
    	
    	propriedade1.setTelefone("33333333");
    	propriedade1.setCar("950950940");
    	
    	Propriedade propriedade2 = new Propriedade();

    	propriedade2.setTelefone("44444444");
    	propriedade2.setCar("95095096666");
    	
    	PessoaFisica pessoaFisica = new PessoaFisica();
    	pessoaFisica.setNome("Maria");
    	pessoaFisica.setSobrenome("Pereira");
    	
    	List<Propriedade> propriedades = new ArrayList();
    	
    	propriedades.add(propriedade1);
    	propriedades.add(propriedade2);
        pessoaFisica.setPropriedades(propriedades);
    	
       
        String jsonPessoaFisicaString = objectMapper.writeValueAsString(pessoaFisica);

        MvcResult result = mockMvc.perform(post("/api/pessoas-fisicas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPessoaFisicaString))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        PessoaFisica pessoaFisicaResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), PessoaFisica.class);

        assertNotNull(pessoaFisicaResponse.getId());

        assertThat(pessoaFisicaResponse.getPropriedades())
                .hasSize(2)
                .extracting(Propriedade::getCar)
                .containsExactlyInAnyOrder("950950940", "95095096666");

    }
    
    @Test
    @SneakyThrows
    void shouldUpdatePessoaFisica() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Nome alterado");
        pessoaFisica.setSobrenome("Sobrenome alterado");
        
        pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

        String jsonPessoaFisicaString = objectMapper.writeValueAsString(pessoaFisica);

        MvcResult result = mockMvc.perform(put("/api/pessoas-fisicas/" + pessoaFisica.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPessoaFisicaString))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        PessoaFisica pessoaFisicaResponse = objectMapper.readValue(result.getResponse().getContentAsString(), PessoaFisica.class);

        assertEquals(pessoaFisica.getId().toString(), pessoaFisicaResponse.getId().toString());
        assertEquals("Nome alterado", pessoaFisicaResponse.getNome());
        assertEquals("Sobrenome alterado", pessoaFisicaResponse.getSobrenome());
    }
    
    @Test
    @SneakyThrows
    void shouldThrowNotFoundErrorWhenUpdatePessoaFisicaWithNotExistsId() {
    	PessoaFisica pessoaFisica = new PessoaFisica();
    	pessoaFisica.setId(500L);

        String jsonPessoaFisicaString = objectMapper.writeValueAsString(pessoaFisica);

        mockMvc.perform(put("/api/pessoas-fisicas/" + pessoaFisica.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPessoaFisicaString))
                .andExpect(status().isNotFound());

    }

    
    @Test
	@SneakyThrows
    void shouldFindByIdPessoaFisica() {
		
		PessoaFisica pessoaFisica = new PessoaFisica();
		
	    pessoaFisica.setNome("Maria");
	    pessoaFisica.setSobrenome("Lima");
		
		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
		
		Optional<PessoaFisica> repository = pessoaFisicaRepository.findById(pessoaFisica.getId());
        assertFalse(repository.isEmpty());
        
        MvcResult result = mockMvc.perform(get("/api/pessoas-fisicas/" + pessoaFisica.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        
        PessoaFisica pessoaFisicaResponse = objectMapper.readValue(result.getResponse().getContentAsString(), PessoaFisica.class);

        assertEquals(pessoaFisica.getId().toString(), pessoaFisicaResponse.getId().toString());
        assertEquals("Maria", pessoaFisicaResponse.getNome());
        assertEquals("Lima", pessoaFisicaResponse.getSobrenome());
        
    }
    
    
    @Test
    @SneakyThrows
    void shouldFindAllPageablePessoaFisica() {
    	List<String> nomes = new ArrayList();
    	nomes.add("Amanda");
    	nomes.add("Lourdes");
    	nomes.add("Paulo");
    	nomes.add("William");
    	nomes.add("Rodrigo");
    	nomes.add("Guilherme");
    	nomes.add("Julio");
    	nomes.add("Aparecido");

        nomes.forEach(nome -> {
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setNome(nome);
            pessoaFisica.setSobrenome(nome+" Sobrenome");
            pessoaFisicaRepository.save(pessoaFisica);
        });

        MvcResult result = mockMvc.perform(get("/api/pessoas-fisicas?size=5&sort=nome")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        Page<PessoaFisica> pagePessoaFisica = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<CustomPageImpl<PessoaFisica>>() {
                });

        assertEquals(5, pagePessoaFisica.getSize());
        assertThat(pagePessoaFisica.getContent()).hasSize(5)
                .extracting(PessoaFisica::getNome)
                .containsExactlyInAnyOrder("Amanda", "Aparecido", "Guilherme", "Julio", "Lourdes");

    }
    
    
    @Test
    @SneakyThrows
    void shouldFindAllPessoaFisica() {
    	List<String> nomes = new ArrayList();
    	nomes.add("Amanda");
    	nomes.add("Lourdes");
    	nomes.add("Paulo");
    	nomes.add("William");
    	nomes.add("Rodrigo");
    	nomes.add("Guilherme");
    	nomes.add("Julio");
    	nomes.add("Aparecido");
    	nomes.add("Eduardo");
    	nomes.add("Igor");
    	nomes.add("Allan");

        nomes.forEach(nome -> {
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setNome(nome);
            pessoaFisica.setSobrenome(nome+" Sobrenome");
            pessoaFisicaRepository.save(pessoaFisica);
        });

        MvcResult result = mockMvc.perform(get("/api/pessoas-fisicas/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<PessoaFisica> pessoasFisicas = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
        		 new TypeReference<List<PessoaFisica>>() {
        	
        });

        assertEquals(11, pessoasFisicas.size());
        assertEquals("Amanda",pessoasFisicas.get(0).getNome());
        assertEquals("Amanda Sobrenome",pessoasFisicas.get(0).getSobrenome());
        assertEquals("Rodrigo",pessoasFisicas.get(4).getNome());
        assertEquals("Rodrigo Sobrenome",pessoasFisicas.get(4).getSobrenome());
        assertEquals("Allan",pessoasFisicas.get(10).getNome());
        assertEquals("Allan Sobrenome",pessoasFisicas.get(10).getSobrenome());
        assertEquals("Julio",pessoasFisicas.get(6).getNome());
        assertEquals("Julio Sobrenome",pessoasFisicas.get(6).getSobrenome());

    }



}
