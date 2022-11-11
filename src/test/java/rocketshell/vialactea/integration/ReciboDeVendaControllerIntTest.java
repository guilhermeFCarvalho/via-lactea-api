package rocketshell.vialactea.integration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
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
import rocketshell.vialactea.domain.PessoaJuridica;
import rocketshell.vialactea.domain.Propriedade;
import rocketshell.vialactea.domain.ReciboDeVenda;
import rocketshell.vialactea.repository.ReciboDeVendaRepository;

public class ReciboDeVendaControllerIntTest extends IntegrationTest {

	@Autowired
    public ReciboDeVendaRepository reciboDeVendaRepository;
	
	@Test
	@SneakyThrows
    void shouldDeleteReciboDeVenda() {
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
	
	
	 @Test
	 @SneakyThrows
	 void shouldSaveReciboDeVenda() {
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
	        
	        
	        String jsonReciboDeVendaString = objectMapper.writeValueAsString(reciboDeVenda);
	        
	        MvcResult result = mockMvc.perform(post("/api/recibo-de-venda/")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonReciboDeVendaString))
	                .andExpect(status().is2xxSuccessful())
	                .andReturn();
	        
	        ReciboDeVenda reciboDeVendaResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), ReciboDeVenda.class);
            
	        assertNotNull(reciboDeVendaResponse.getId());
	        
	      
	        assertTrue(reciboDeVendaResponse.getPago()== true);
	        assertTrue(reciboDeVendaResponse.getPessoaJuridica().getCNPJ().equals("22222"));
	        assertTrue(reciboDeVendaResponse.getObservacoes().equals("observação do registro de venda"));

	 }
	 
	 @Test
	 @SneakyThrows
	 void shouldUpdateReciboDeVenda() {


		 ReciboDeVenda reciboDeVenda = new ReciboDeVenda();

		 reciboDeVenda.setObservacoes("observação alterada");
		 reciboDeVenda.setPago(true);
	     reciboDeVenda.setQuantidadeLeiteVendida(BigDecimal.valueOf(200));
	     reciboDeVenda.setDataDaVenda(LocalDate.now());
	     
		 reciboDeVenda = reciboDeVendaRepository.save(reciboDeVenda);
		 
		 String jsonReciboDeVendaString = objectMapper.writeValueAsString(reciboDeVenda);
		 
		 MvcResult result = mockMvc.perform(put("/api/recibo-de-venda/" + reciboDeVenda.getId())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonReciboDeVendaString))
	                .andExpect(status().is2xxSuccessful())
	                .andReturn();
		 
		 String res = result.getResponse().getContentAsString();
		 
		 ReciboDeVenda reciboDeVendaResponse = objectMapper.readValue(res, ReciboDeVenda.class);
		 
		 assertEquals(reciboDeVenda.getId().toString(), reciboDeVendaResponse.getId().toString());
	     assertEquals("observação alterada", reciboDeVendaResponse.getObservacoes());
	     

		 
	 }
	 
	 
	 @Test
	 @SneakyThrows
	 void shouldThrowNotFoundErrorWhenUpdateReciboDeVendaWithNotExistsId() {
	    ReciboDeVenda reciboDeVenda = new ReciboDeVenda();
	    reciboDeVenda.setId(400L);

	    String jsonReciboDeVendaString = objectMapper.writeValueAsString(reciboDeVenda);

	    mockMvc.perform(put("/api/recibo-de-venda/" + reciboDeVenda.getId().toString())
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(jsonReciboDeVendaString))
	             .andExpect(status().isNotFound());

	    }


	 @Test
	 @SneakyThrows
	 void shouldFindAllPageableReciboDeVenda() {
		 
		 List<String> observacoes = Arrays.asList(
				 "observacao 1","observacao 2","observacao 3",
				 "observacao 4","observacao 5","observacao 6",
				 "observacao 7","observacao 8","observacao 9",
				 "observacao 10","observacao 11","observacao 12"
				 );
		 
		 observacoes.forEach(observacao -> {
			 ReciboDeVenda reciboDeVenda = new ReciboDeVenda();
			 reciboDeVenda.setObservacoes(observacao);
			 reciboDeVendaRepository.save(reciboDeVenda);
		 });
		 
		 MvcResult result = mockMvc.perform(get("/api/recibo-de-venda?size=2")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();

		 
		 
		 Page<ReciboDeVenda> pageReciboDeVenda = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
	                new TypeReference<Page<ReciboDeVenda>>() {
	    });
		 
		 assertEquals(2, pageReciboDeVenda.getSize());


		
	 }
	 
	 @Test
	 @SneakyThrows
	 void shouldFindByIdReciboDeVenda() {
	      
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
		 
	        MvcResult result = mockMvc.perform(get("/api/recibo-de-venda/" + reciboDeVenda.getId().toString())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();
		  
	        
	        ReciboDeVenda reciboDeVendaResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), ReciboDeVenda.class);
		  
            assertEquals("observação do registro de venda", reciboDeVendaResponse.getObservacoes());
		    assertEquals(true,reciboDeVendaResponse.getPago());
		    assertEquals("1111",reciboDeVendaResponse.getPropriedade().getCar());
		    assertEquals("22222",reciboDeVendaResponse.getPessoaJuridica().getCNPJ());
	 }


}
