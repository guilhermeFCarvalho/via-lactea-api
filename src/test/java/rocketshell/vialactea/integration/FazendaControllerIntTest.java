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
import rocketshell.vialactea.domain.Fazenda;
import rocketshell.vialactea.repository.FazendaRepository;
import utils.CustomPageImpl;

public class FazendaControllerIntTest extends IntegrationTest {

	@Autowired
	private FazendaRepository fazendaRepository;
	
	@Test
	@SneakyThrows
    void shouldDeleteFazenda() {
		Fazenda fazenda = new Fazenda();
		Endereco endereco = new Endereco();
		
		endereco.setRua("avenida Brasil");
		endereco.setBairro("centro");
		endereco.setCep("87050-465");
		endereco.setCidade("Cianorte");
		endereco.setEstado("Paraná");
		endereco.setNumero("800");
		
		fazenda.setEndereco(endereco);
		
		fazenda.setNomeDaFazenda("sitio 123");
		
		
		fazenda = fazendaRepository.save(fazenda);
		
		Optional<Fazenda> repository = fazendaRepository.findById(fazenda.getId());
        assertFalse(repository.isEmpty());
        
        mockMvc.perform(delete("/api/fazendas/" + fazenda.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Fazenda> findById = fazendaRepository.findById(fazenda.getId());

        assertFalse(findById.isPresent());
	}
	
	
	 @Test
	 @SneakyThrows
	 void shouldSaveFazenda() {
		 Endereco endereco = new Endereco();
		 Fazenda fazenda = new Fazenda();
		 
			endereco.setRua("avenida Brasil");
			endereco.setBairro("centro");
			endereco.setCep("87050-465");
			endereco.setCidade("Cianorte");
			endereco.setEstado("Paraná");
			endereco.setNumero("800");
			
			fazenda.setEndereco(endereco);
			fazenda.setNomeDaFazenda("sitio 177");
			
			String jsonFazendaString = objectMapper.writeValueAsString(fazenda);
			
			MvcResult result = mockMvc.perform(post("/api/fazendas/")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonFazendaString))
	                .andExpect(status().is2xxSuccessful())
	                .andReturn();
	        
	        Fazenda fazendaResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Fazenda.class);
           
	        assertNotNull(fazendaResponse.getId());
	        assertEquals(fazendaResponse.getEndereco().getBairro(),"centro");
	        assertEquals(fazendaResponse.getEndereco().getRua(),"avenida Brasil");
	        assertEquals(fazendaResponse.getEndereco().getCep(),"87050-465");
	        assertEquals(fazendaResponse.getEndereco().getCidade(),"Cianorte");
	        assertEquals(fazendaResponse.getEndereco().getEstado(),"Paraná");
	        assertEquals(fazendaResponse.getEndereco().getNumero(),"800");
	        assertEquals(fazendaResponse.getNomeDaFazenda(),"sitio 177");

	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldThrowNotFoundErrorWhenUpdateFazendaWithNotExistsId() {
	    Fazenda fazenda = new Fazenda();
	    fazenda.setId(800L);

	    String jsonFazendaString = objectMapper.writeValueAsString(fazenda);

	    mockMvc.perform(put("/api/fazendas/" + fazenda.getId().toString())
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(jsonFazendaString))
	             .andExpect(status().isNotFound());

	  }
	 
	 
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldFindAllPageableFazenda() {
		 
		 List<String> nomesFazenda = Arrays.asList(
				 "Alegria","Alvorada","Feliz",
				 "Sao Francisco","Arapongas","Bem Vindo",
				 "Aclimacao","Sol Nascente","Km123"
				 );
		 
		 nomesFazenda.forEach(nome -> {
			 Endereco endereco = new Endereco();
			 Fazenda  fazenda = new Fazenda();
			 endereco.setBairro("123");
			 endereco.setCep("8701-229");
			 endereco.setCidade("Arapongas");
			 endereco.setEstado("Paraná");
			 endereco.setNumero("152");
			 endereco.setRua("rua");
			 fazenda.setEndereco(endereco);
			 fazenda.setNomeDaFazenda(nome);
		
			 fazendaRepository.save(fazenda);
		 });
		 
		 MvcResult result = mockMvc.perform(get("/api/fazendas?size=4&sort=nomeDaFazenda")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();

		 
		 
		 Page<Fazenda> pageFazenda = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
				 new TypeReference<CustomPageImpl<Fazenda>>() {
         });

		 
		 assertEquals(4, pageFazenda.getSize());
		 assertThat(pageFazenda.getContent()).hasSize(4)
           .extracting(Fazenda::getNomeDaFazenda)
           .containsExactlyInAnyOrder("Aclimacao", "Alegria","Alvorada","Arapongas");



		
	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldFindByIdFazenda() {
	      
		 Endereco endereco = new Endereco();
			endereco.setRua("rodovia Brasil");
			endereco.setBairro("km122");
			endereco.setCep("87050-465");
			endereco.setCidade("Cianorte");
			endereco.setEstado("Paraná");
			endereco.setNumero("800");
			
			Fazenda fazenda = new Fazenda();
			fazenda.setEndereco(endereco);
			fazenda.setNomeDaFazenda("central");
	        fazenda = fazendaRepository.save(fazenda);
		 
	        MvcResult result = mockMvc.perform(get("/api/fazendas/" + fazenda.getId().toString())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();
		  
	        
	        Fazenda fazendaResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Fazenda.class);
		  
            assertEquals("rodovia Brasil", fazendaResponse.getEndereco().getRua());
		    assertEquals("km122",fazendaResponse.getEndereco().getBairro());
		    assertEquals("87050-465",fazendaResponse.getEndereco().getCep());
		    assertEquals("Cianorte",fazendaResponse.getEndereco().getCidade());
		    assertEquals("Paraná",fazendaResponse.getEndereco().getEstado());
		    assertEquals("800",fazendaResponse.getEndereco().getNumero());
		    assertEquals("central",fazendaResponse.getNomeDaFazenda());
	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldFindAllFazenda() {
		 
		 List<String> nomesFazenda = Arrays.asList(
				 "Alegria","Alvorada","Feliz",
				 "Sao Francisco","Arapongas","Bem Vindo",
				 "Aclimacao","Sol Nascente","Km123"
				 );
		 
		 nomesFazenda.forEach(nome -> {
			 Endereco endereco = new Endereco();
			 Fazenda  fazenda = new Fazenda();
			 endereco.setBairro("123");
			 endereco.setCep("8701-229");
			 endereco.setCidade("Arapongas");
			 endereco.setEstado("Paraná");
			 endereco.setNumero("152");
			 endereco.setRua("rua");
			 fazenda.setEndereco(endereco);
			 fazenda.setNomeDaFazenda(nome);
		
			 fazendaRepository.save(fazenda);
		 });
		 
		 MvcResult result = mockMvc.perform(get("/api/fazendas/list")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();

		
		 
		 List<Fazenda> fazendas = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
				 
				 new TypeReference<List<Fazenda>>() {
         }
		 
		);
		 
		 
		 assertEquals(9, fazendas.size());
		 assertEquals("Feliz",fazendas.get(2).getNomeDaFazenda());
		 assertEquals("rua",fazendas.get(2).getEndereco().getRua());
		 assertEquals("Alegria",fazendas.get(0).getNomeDaFazenda());
		 assertEquals("rua",fazendas.get(0).getEndereco().getRua());
		

		
	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldUpdateEndereco() {


		 Endereco endereco = new Endereco();
		 
		 endereco.setRua("rodovia Brasil alterada");
		 endereco.setBairro("km122");
		 endereco.setCep("87050-465");
		 endereco.setCidade("Cianorte");
		 endereco.setEstado("Paraná");
		 endereco.setNumero("800");
		 
		 Fazenda fazenda = new Fazenda();
		 fazenda.setEndereco(endereco);
		 fazenda.setNomeDaFazenda("fazenda do boi");

		 fazenda = fazendaRepository.save(fazenda);
		 
		 
		 String jsonFazendaString = objectMapper.writeValueAsString(fazenda);
		 
		 MvcResult result = mockMvc.perform(put("/api/fazendas/" + fazenda.getId())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonFazendaString))
	                .andExpect(status().is2xxSuccessful())
	                .andReturn();
		 
		 String res = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		 
		 Fazenda fazendaResponse = objectMapper.readValue(res, Fazenda.class);
		 
		 assertEquals(fazenda.getId().toString(), fazendaResponse.getId().toString());
	     assertEquals("rodovia Brasil alterada", fazendaResponse.getEndereco().getRua());
	     assertNotEquals("rodovia Brasil", fazendaResponse.getEndereco().getRua());
	     assertEquals("km122", fazendaResponse.getEndereco().getBairro());
	     assertEquals("Cianorte", fazendaResponse.getEndereco().getCidade());
	     assertEquals("Paraná", fazendaResponse.getEndereco().getEstado());
	     assertEquals("800", fazendaResponse.getEndereco().getNumero());
	     assertEquals("87050-465", fazendaResponse.getEndereco().getCep());
	     assertEquals("fazenda do boi", fazendaResponse.getNomeDaFazenda());
	     

		 
	 }
	 
	 
	
}



