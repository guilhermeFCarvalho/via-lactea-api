package rocketshell.vialactea.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.SneakyThrows;
import rocketshell.vialactea.domain.Endereco;
import rocketshell.vialactea.repository.EnderecoRepository;
import utils.CustomPageImpl;


public class EnderecoControllerIntTest extends IntegrationTest {
   
	@Autowired
    public EnderecoRepository enderecoRepository;
	
	@Test
	@SneakyThrows
    void shouldDeleteEndereco() {
		Endereco endereco = new Endereco();
		
		endereco.setRua("avenida Brasil");
		endereco.setBairro("centro");
		endereco.setCep("87050-465");
		endereco.setCidade("Cianorte");
		endereco.setEstado("Paraná");
		endereco.setNumero("800");
		
		endereco = enderecoRepository.save(endereco);
		
		Optional<Endereco> repository = enderecoRepository.findById(endereco.getId());
        assertFalse(repository.isEmpty());
        
        mockMvc.perform(delete("/api/enderecos/" + endereco.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Endereco> findById = enderecoRepository.findById(endereco.getId());

        assertFalse(findById.isPresent());
	}
	

	 @Test
	 @SneakyThrows
	 void shouldSaveEndereco() {
		 Endereco endereco = new Endereco();
			endereco.setRua("avenida Brasil");
			endereco.setBairro("centro");
			endereco.setCep("87050-465");
			endereco.setCidade("Cianorte");
			endereco.setEstado("Paraná");
			endereco.setNumero("800");
			
			String jsonEnderecoString = objectMapper.writeValueAsString(endereco);
			
			MvcResult result = mockMvc.perform(post("/api/enderecos/")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonEnderecoString))
	                .andExpect(status().is2xxSuccessful())
	                .andReturn();
	        
	        Endereco enderecoResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Endereco.class);
            
	        assertNotNull(enderecoResponse.getId());
	        assertEquals(enderecoResponse.getBairro(),"centro");
	        assertEquals(enderecoResponse.getRua(),"avenida Brasil");
	        assertEquals(enderecoResponse.getCep(),"87050-465");
	        assertEquals(enderecoResponse.getCidade(),"Cianorte");
	        assertEquals(enderecoResponse.getEstado(),"Paraná");
	        assertEquals(enderecoResponse.getNumero(),"800");

	 }
	 
	 @Test
	 @SneakyThrows
	 void shouldThrowNotFoundErrorWhenUpdateEnderecoWithNotExistsId() {
	    Endereco endereco = new Endereco();
	    endereco.setId(400L);

	    String jsonEnderecoString = objectMapper.writeValueAsString(endereco);

	    mockMvc.perform(put("/api/endereco/" + endereco.getId().toString())
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(jsonEnderecoString))
	             .andExpect(status().isNotFound());

	    }
	 
	 @Test
	 @SneakyThrows
	 void shouldFindAllPageableEndereco() {
		 
		 List<String> cidades = Arrays.asList(
				 "Maringá","Londrina","Cianorte",
				 "Paranavai","Arapongas","Marialva"
				 );
		 
		 cidades.forEach(cidade -> {
			 Endereco endereco = new Endereco();
			 endereco.setBairro("centro");
			 endereco.setCep("8701-229");
			 endereco.setCidade(cidade);
			 endereco.setEstado("Paraná");
			 endereco.setNumero("222");
			 endereco.setRua("rua"+cidade);
		
			 enderecoRepository.save(endereco);
		 });
		 
		 MvcResult result = mockMvc.perform(get("/api/enderecos?size=3&sort=cidade")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();

		 
		 
		 Page<Endereco> pageEndereco = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
				 new TypeReference<CustomPageImpl<Endereco>>() {
         });

		 
		 assertEquals(3, pageEndereco.getSize());
		 assertThat(pageEndereco.getContent()).hasSize(3)
           .extracting(Endereco::getCidade)
           .containsExactlyInAnyOrder("Arapongas", "Cianorte","Londrina");



		
	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldFindByIdEndereco() {
	      
		 Endereco endereco = new Endereco();
			endereco.setRua("avenida Brasil");
			endereco.setBairro("centro");
			endereco.setCep("87050-465");
			endereco.setCidade("Cianorte");
			endereco.setEstado("Paraná");
			endereco.setNumero("800");
	        endereco = enderecoRepository.save(endereco);
		 
	        MvcResult result = mockMvc.perform(get("/api/enderecos/" + endereco.getId().toString())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();
		  
	        
	        Endereco enderecoResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Endereco.class);
		  
            assertEquals("avenida Brasil", enderecoResponse.getRua());
		    assertEquals("centro",enderecoResponse.getBairro());
		    assertEquals("87050-465",enderecoResponse.getCep());
		    assertEquals("Cianorte",enderecoResponse.getCidade());
		    assertEquals("Paraná",enderecoResponse.getEstado());
		    assertEquals("800",enderecoResponse.getNumero());
	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldFindAllEndereco() {
		 
		 List<String> cidades = Arrays.asList(
				 "Maringá","Londrina","Cianorte",
				 "Paranavai","Arapongas","Marialva"
				 );
		 
		 cidades.forEach(cidade -> {
			 Endereco endereco = new Endereco();
			 endereco.setBairro("centro");
			 endereco.setCep("8701-229");
			 endereco.setCidade(cidade);
			 endereco.setEstado("Paraná");
			 endereco.setNumero("222");
			 endereco.setRua("rua"+cidade);
		
			 enderecoRepository.save(endereco);
		 });
		 
		 MvcResult result = mockMvc.perform(get("/api/enderecos/list")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();

		
		 
		 List<Endereco> enderecos = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
				 
				 new TypeReference<List<Endereco>>() {
         }
		 
		);
		 
		 
		 assertEquals(6, enderecos.size());
		 assertEquals("Maringá",enderecos.get(0).getCidade());
		 assertEquals("ruaMaringá",enderecos.get(0).getRua());
		

		
	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldUpdateEndereco() {


		 Endereco endereco = new Endereco();
		 
		 endereco.setRua("avenida Brasil");
		 endereco.setBairro("centro");
		 endereco.setCep("87050-465");
		 endereco.setCidade("Cianorte");
		 endereco.setEstado("Paraná");
		 endereco.setNumero("800");

		 endereco = enderecoRepository.save(endereco);
		 
		 endereco.setRua("avenida Brasil Alterada");
		 
		 
		 String jsonEnderecoString = objectMapper.writeValueAsString(endereco);
		 
		 MvcResult result = mockMvc.perform(put("/api/enderecos/" + endereco.getId())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonEnderecoString))
	                .andExpect(status().is2xxSuccessful())
	                .andReturn();
		 
		 String res = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		 
		 Endereco enderecoResponse = objectMapper.readValue(res, Endereco.class);
		 
		 assertEquals(endereco.getId().toString(), enderecoResponse.getId().toString());
	     assertEquals("avenida Brasil Alterada", enderecoResponse.getRua());
	     assertNotEquals("avenida Brasil", enderecoResponse.getRua());
	     assertEquals("centro", enderecoResponse.getBairro());
	     assertEquals("Cianorte", enderecoResponse.getCidade());
	     assertEquals("Paraná", enderecoResponse.getEstado());
	     assertEquals("800", enderecoResponse.getNumero());
	     assertEquals("87050-465", enderecoResponse.getCep());
	     

		 
	 }
	 


	 
	   
	
	
}
