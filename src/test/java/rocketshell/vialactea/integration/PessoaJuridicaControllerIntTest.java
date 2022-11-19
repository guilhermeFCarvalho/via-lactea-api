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
import rocketshell.vialactea.domain.PessoaJuridica;
import rocketshell.vialactea.domain.Propriedade;
import rocketshell.vialactea.repository.PessoaJuridicaRepository;
import utils.CustomPageImpl;

public class PessoaJuridicaControllerIntTest extends IntegrationTest {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Test
    @SneakyThrows
    void shouldDeletePessoaJuridica() {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setRazaoSocial("nomeempresa");
        pessoaJuridica.setCNPJ("10100100000101");
        pessoaJuridica.setInscricaoEstadual("123123123123");

        pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);

        Optional<PessoaJuridica> repository = pessoaJuridicaRepository.findById(pessoaJuridica.getId());
        assertFalse(repository.isEmpty());

        mockMvc.perform(delete("/api/pessoas-juridicas/" + pessoaJuridica.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<PessoaJuridica> findById = pessoaJuridicaRepository.findById(pessoaJuridica.getId());

        assertFalse(findById.isPresent());
    }

    @Test
    @SneakyThrows
    void shouldSavePessoaJuridica() {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setRazaoSocial("nomeempresa");
        pessoaJuridica.setCNPJ("10100100000101");
        pessoaJuridica.setInscricaoEstadual("123123123123");

        String jsonPessoaJuridicaString = objectMapper.writeValueAsString(pessoaJuridica);

        MvcResult result = mockMvc.perform(post("/api/pessoas-juridicas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPessoaJuridicaString))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        PessoaJuridica PessoaJuridicaResponse = objectMapper
                .readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), PessoaJuridica.class);

        assertNotNull(PessoaJuridicaResponse.getId());

        assertThat(PessoaJuridicaResponse.getCNPJ()).isEqualTo("10100100000101");

    }

    @Test
    @SneakyThrows
    void shouldUpdatePessoaJuridica() {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setRazaoSocial("nomeempresa");
        pessoaJuridica.setCNPJ("10100100000101");
        pessoaJuridica.setInscricaoEstadual("123123123123");

        pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);

        pessoaJuridica.setRazaoSocial("nome alterado");
        pessoaJuridica.setCNPJ("10100100000102");
        pessoaJuridica.setInscricaoEstadual("123123123122");

        pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);

        String jsonPessoaJuridicaString = objectMapper.writeValueAsString(pessoaJuridica);

        MvcResult result = mockMvc.perform(put("/api/pessoas-juridicas/" + pessoaJuridica.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPessoaJuridicaString))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        PessoaJuridica pessoaJuridicaResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
                PessoaJuridica.class);

        assertEquals(pessoaJuridica.getId().toString(), pessoaJuridicaResponse.getId().toString());
        assertEquals("nome alterado", pessoaJuridicaResponse.getRazaoSocial());
        assertEquals("10100100000102", pessoaJuridicaResponse.getCNPJ());
        assertEquals("123123123122", pessoaJuridicaResponse.getInscricaoEstadual());
    }

    @Test
    @SneakyThrows
    void shouldThrowNotFoundErrorWhenUpdatePessoaJuridicaWithNotExistsId() {
        PessoaJuridica PessoaJuridica = new PessoaJuridica();
        PessoaJuridica.setId(500L);

        String jsonPessoaJuridicaString = objectMapper.writeValueAsString(PessoaJuridica);

        mockMvc.perform(put("/api/pessoas-juridicas/" + PessoaJuridica.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPessoaJuridicaString))
                .andExpect(status().isNotFound());

    }

    @Test
    @SneakyThrows
    void shouldFindByIdPessoaJuridica() {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setRazaoSocial("nomeempresa");
        pessoaJuridica.setCNPJ("10100100000101");
        pessoaJuridica.setInscricaoEstadual("123123123123");

        pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);

        Optional<PessoaJuridica> repository = pessoaJuridicaRepository.findById(pessoaJuridica.getId());
        assertFalse(repository.isEmpty());

        MvcResult result = mockMvc.perform(get("/api/pessoas-juridicas/" + pessoaJuridica.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        PessoaJuridica pessoaJuridicaResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
                PessoaJuridica.class);

        assertEquals(pessoaJuridica.getId().toString(), pessoaJuridicaResponse.getId().toString());
        assertEquals("nomeempresa", pessoaJuridicaResponse.getRazaoSocial());
        assertEquals("10100100000101", pessoaJuridicaResponse.getCNPJ());
        assertEquals("123123123123", pessoaJuridicaResponse.getInscricaoEstadual());

    }

    @Test
    @SneakyThrows
    void shouldFindAllPageablePessoaJuridica() {
        List<String> razoesSociais = new ArrayList();
        razoesSociais.add("empresa a");
        razoesSociais.add("empresa b");
        razoesSociais.add("empresa c");
        razoesSociais.add("empresa d");
        razoesSociais.add("empresa e");
        razoesSociais.add("empresa f");

        

        razoesSociais.forEach(razaoSocial -> {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setRazaoSocial(razaoSocial);
            pessoaJuridica.setCNPJ("10100100000101");
            pessoaJuridica.setInscricaoEstadual("123123123123");
            pessoaJuridicaRepository.save(pessoaJuridica);
            
        });

        MvcResult result = mockMvc.perform(get("/api/pessoas-juridicas?size=5&sort=razaoSocial")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        Page<PessoaJuridica> pagePessoaJuridica = objectMapper.readValue(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<CustomPageImpl<PessoaJuridica>>() {
                });

        assertEquals(5, pagePessoaJuridica.getSize());
        assertThat(pagePessoaJuridica.getContent()).hasSize(5)
                .extracting(PessoaJuridica::getRazaoSocial)
                .containsExactlyInAnyOrder("empresa a", "empresa b", "empresa c", "empresa d", "empresa e");

    }

    @Test
    @SneakyThrows
    void shouldFindAllPessoaJuridica() {
        List<String> razoesSociais = new ArrayList();
        razoesSociais.add("empresa um");
        razoesSociais.add("empresa dois");
        razoesSociais.add("empresa tres");
        razoesSociais.add("empresa quatro");
        razoesSociais.add("empresa cinco");
        razoesSociais.add("empresa seis");;

        razoesSociais.forEach(razaoSocial -> {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setRazaoSocial(razaoSocial);
            
            pessoaJuridicaRepository.save(pessoaJuridica);
        });

        MvcResult result = mockMvc.perform(get("/api/pessoas-juridicas/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<PessoaJuridica> pessoasFisicas = objectMapper.readValue(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<List<PessoaJuridica>>() {

                });

        assertEquals(6, pessoasFisicas.size());
        assertEquals("empresa um", pessoasFisicas.get(0).getRazaoSocial());
        assertEquals("empresa dois", pessoasFisicas.get(1).getRazaoSocial());
        assertEquals("empresa tres", pessoasFisicas.get(2).getRazaoSocial());
        assertEquals("empresa quatro", pessoasFisicas.get(3).getRazaoSocial());
        assertEquals("empresa cinco", pessoasFisicas.get(4).getRazaoSocial());
        assertEquals("empresa seis", pessoasFisicas.get(5).getRazaoSocial());
        

    }

}
