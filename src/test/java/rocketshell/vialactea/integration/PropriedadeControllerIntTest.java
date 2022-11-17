package rocketshell.vialactea.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import utils.CustomPageImpl;       
import com.fasterxml.jackson.core.type.TypeReference;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	 @Test
	 @SneakyThrows
	 void shouldFindAllPageablePropriedade() {
		 
		 List<String> cars = Arrays.asList(
				 "35005500200507","65005500200507","15005500200507",
				 "25005500200507","23005500200507","34005500200507"
				 );
		 
		 cars.forEach(car -> {
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
				
				propriedade.setCar(car);
				propriedade.setTelefone("77719898");
				propriedade.setFazenda(fazenda);
		
			 propriedadeRepository.save(propriedade);
		 });
		 
		 MvcResult result = mockMvc.perform(get("/api/propriedades?size=4&sort=car")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();

		 
		 
		 Page<Propriedade> pagePropriedade = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
				 new TypeReference<CustomPageImpl<Propriedade>>() {
         });

		 
		 assertEquals(4, pagePropriedade.getSize());
		 assertThat(pagePropriedade.getContent()).hasSize(4)
           .extracting(Propriedade::getCar)
           .containsExactlyInAnyOrder("15005500200507", "23005500200507","25005500200507","34005500200507");



		
	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldFindByIdPropriedade() {
	      
		    Fazenda fazenda = new Fazenda();
			Endereco endereco = new Endereco();
			Propriedade propriedade = new Propriedade();
			
			endereco.setRua("estrada Brasil");
			endereco.setBairro("vila rural");
			endereco.setCep("87050-465");
			endereco.setCidade("Marialva");
			endereco.setEstado("Paraná");
			endereco.setNumero("500");
			
			fazenda.setEndereco(endereco);
			fazenda.setNomeDaFazenda("sitio 124");
			
			propriedade.setCar("35005500200507");
			propriedade.setTelefone("77719898");
			propriedade.setFazenda(fazenda);
			
	        propriedade = propriedadeRepository.save(propriedade);
		 
	        MvcResult result = mockMvc.perform(get("/api/propriedades/" + propriedade.getId().toString())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();
		  
	        
	        Propriedade propriedadeResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Propriedade.class);
		  
	        
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getBairro(),"vila rural");
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getRua(),"estrada Brasil");
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getCep(),"87050-465");
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getCidade(),"Marialva");
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getEstado(),"Paraná");
	        assertEquals(propriedadeResponse.getFazenda().getEndereco().getNumero(),"500");
	        assertEquals(propriedadeResponse.getFazenda().getNomeDaFazenda(),"sitio 124");
	        assertEquals(propriedadeResponse.getCar(),"35005500200507");
	        assertEquals(propriedadeResponse.getTelefone(),"77719898");
	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldFindAllPropriedades() {
		 
		 List<String> cars = Arrays.asList(
				 "35005500200507","65005500200507","15005500200507",
				 "25005500200507","23005500200507","34005500200507"
		 );
		 
		 cars.forEach(car -> {
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
				
				propriedade.setCar(car);
				propriedade.setTelefone("77719898");
				propriedade.setFazenda(fazenda);
		
			    propriedadeRepository.save(propriedade);
		 });
		 
		 
		 MvcResult result = mockMvc.perform(get("/api/propriedades/list")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();

		
		 
		 List<Propriedade> propriedades = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
				 
				 new TypeReference<List<Propriedade>>() {
         }
		 
		);
		 
		 
		 assertEquals(6, propriedades.size());
		 assertEquals("35005500200507",propriedades.get(0).getCar());
		 assertEquals("77719898",propriedades.get(0).getTelefone());
		 assertEquals("25005500200507",propriedades.get(3).getCar());
		 assertEquals("Marialva",propriedades.get(3).getFazenda().getEndereco().getCidade());
		 assertEquals("34005500200507",propriedades.get(5).getCar());
		

		
	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldUpdatePropriedade() {


		 Endereco endereco = new Endereco();
		 
		 endereco.setRua("estrada do boi alterada");
		 endereco.setBairro("km324 alterada");
		 endereco.setCep("87050-465 alterada");
		 endereco.setCidade("Maringá alterada");
		 endereco.setEstado("Paraná alterada");
		 endereco.setNumero("600 alterada");
		 
		 Fazenda fazenda = new Fazenda();
		 fazenda.setEndereco(endereco);
		 fazenda.setNomeDaFazenda("fazenda das aguas alterada");
		 
		 Propriedade propriedade = new Propriedade();
		 
		 propriedade.setCar("35005500200507-alterada");
		 propriedade.setTelefone("34443636-alterada");
		 propriedade.setFazenda(fazenda);

		 propriedade = propriedadeRepository.save(propriedade);
		 
		 
		 String jsonPropriedadeString = objectMapper.writeValueAsString(propriedade);
		 
		 MvcResult result = mockMvc.perform(put("/api/propriedades/" + propriedade.getId())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonPropriedadeString))
	                .andExpect(status().is2xxSuccessful())
	                .andReturn();
		 
		 String res = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		 
		 Propriedade propriedadeResponse = objectMapper.readValue(res, Propriedade.class);
		 
		 assertEquals(propriedade.getId().toString(), propriedadeResponse.getId().toString());
	     assertEquals("estrada do boi alterada", propriedadeResponse.getFazenda().getEndereco().getRua());
	     assertEquals("km324 alterada", propriedadeResponse.getFazenda().getEndereco().getBairro());
	     assertEquals("87050-465 alterada", propriedadeResponse.getFazenda().getEndereco().getCep());
	     assertEquals("Maringá alterada", propriedadeResponse.getFazenda().getEndereco().getCidade());
	     assertEquals("Paraná alterada", propriedadeResponse.getFazenda().getEndereco().getEstado());
	     assertEquals("600 alterada", propriedadeResponse.getFazenda().getEndereco().getNumero());
	     assertEquals("fazenda das aguas alterada", propriedadeResponse.getFazenda().getNomeDaFazenda());
	     assertEquals("35005500200507-alterada", propriedadeResponse.getCar());
	     assertNotEquals("35005500200507", propriedadeResponse.getCar());
	     assertEquals("34443636-alterada",propriedadeResponse.getTelefone());
	     assertNotEquals("34443636",propriedadeResponse.getTelefone());
	     
		 
	 }
	 
	 
	
}


