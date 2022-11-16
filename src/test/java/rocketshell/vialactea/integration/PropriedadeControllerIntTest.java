package rocketshell.vialactea.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import lombok.SneakyThrows;
import rocketshell.vialactea.domain.Endereco;
import rocketshell.vialactea.domain.Fazenda;
import rocketshell.vialactea.domain.Propriedade;
import rocketshell.vialactea.repository.PropriedadeRepository;

public class PropriedadeControllerIntTest extends IntegrationTest{
	
	@Autowired
	private PropriedadeRepository propriedadeRepository;
	
	@Test
	@SneakyThrows
    void shouldDeletePropriedade() {
		Fazenda fazenda = new Fazenda();
		Endereco endereco = new Endereco();
		Propriedade propriedade = new Propriedade();
		
		endereco.setRua("estrada Brasil");
		endereco.setBairro("vila rural");
		endereco.setCep("87050-465");
		endereco.setCidade("Marialva");
		endereco.setEstado("Paraná");
		endereco.setNumero("800");
		
		fazenda.setEndereco(endereco);
		fazenda.setNomeDaFazenda("sitio 123");
		
		propriedade.setCar("35005500200507");
		propriedade.setTelefone("77719898");
		propriedade.setFazenda(fazenda);

		
		propriedade = propriedadeRepository.save(propriedade);
		
		Optional<Propriedade> repository = propriedadeRepository.findById(propriedade.getId());
        assertFalse(repository.isEmpty());
        
        mockMvc.perform(delete("/api/propriedades/" + propriedade.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Propriedade> findById = propriedadeRepository.findById(propriedade.getId());

        assertFalse(findById.isPresent());
	}
	
	
	 @Test
	 @SneakyThrows
	 void shouldSavePropriedade() {
		    Fazenda fazenda = new Fazenda();
			Endereco endereco = new Endereco();
			Propriedade propriedade = new Propriedade();
			
			endereco.setRua("estrada Brasil");
			endereco.setBairro("vila rural");
			endereco.setCep("87050-465");
			endereco.setCidade("Marialva");
			endereco.setEstado("Paraná");
			endereco.setNumero("800");
			
			fazenda.setEndereco(endereco);
			fazenda.setNomeDaFazenda("sitio 123");
			
			propriedade.setCar("35005500200507");
			propriedade.setTelefone("77719898");
			propriedade.setFazenda(fazenda);
			
			String jsonPropriedadeString = objectMapper.writeValueAsString(propriedade);
			
			MvcResult result = mockMvc.perform(post("/api/propriedades")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonPropriedadeString))
	                .andExpect(status().is2xxSuccessful())
	                .andReturn();
	        
	        Propriedade propriedadeResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Propriedade.class);
          
	        assertNotNull(propriedadeResponse.getId());
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getBairro(),"vila rural");
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getRua(),"estrada Brasil");
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getCep(),"87050-465");
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getCidade(),"Marialva");
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getEstado(),"Paraná");
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getNumero(),"800");
	        assertEquals(propriedadeResponse.getFazenda().getNomeDaFazenda(),"sitio 123");
	        assertEquals(propriedadeResponse.getCar(),"35005500200507");
	        assertEquals(propriedadeResponse.getTelefone(),"77719898");

	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldThrowNotFoundErrorWhenUpdatePropriedadeWithNotExistsId() {
	    Propriedade propriedade = new Propriedade();
	    propriedade.setId(600L);

	    String jsonPropriedadeString = objectMapper.writeValueAsString(propriedade);

	    mockMvc.perform(put("/api/propriedades/" + propriedade.getId().toString())
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(jsonPropriedadeString))
	             .andExpect(status().isNotFound());

	  }

	 


}
