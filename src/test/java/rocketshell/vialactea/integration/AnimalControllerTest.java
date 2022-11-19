package rocketshell.vialactea.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import rocketshell.vialactea.domain.Animal;
import rocketshell.vialactea.domain.Fazenda;
import rocketshell.vialactea.domain.Sexo;
import rocketshell.vialactea.repository.AnimalRepository;
import rocketshell.vialactea.repository.FazendaRepository;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AnimalControllerTest extends IntegrationTest {

    @Autowired
    public AnimalRepository animalRepository;

    @Autowired
    public FazendaRepository fazendaRepository;

    @Test
    @SneakyThrows
    void shouldDeleteAnimal() {
        Fazenda fazenda = new Fazenda();
        fazenda.setNomeDaFazenda("Fazenda Teste");

        fazendaRepository.save(fazenda);

        Animal animal = new Animal();
        animal.setId(1L);
        animal.setDataDeNascimento(LocalDate.of(2022, 1,1));
        animal.setFazenda(fazenda);
        animal.setEspecie("Bovino");
        animal.setRaca("Holandesa");
        animal.setPeso(BigDecimal.valueOf(15));
        animal.setSexo(Sexo.FEMEA);
        animal.setQuantidadeDeCrias(0);

        Animal animalSalvo = animalRepository.save(animal);

        Optional<Animal> repository = animalRepository.findById(animalSalvo.getId());
        assertFalse(repository.isEmpty());

        mockMvc.perform(delete("/api/animais/" + animalSalvo.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Animal> findById = animalRepository.findById(animalSalvo.getId());

        assertFalse(findById.isPresent());
    }

    @Test
    @SneakyThrows
    void shouldSaveAnimal() {
        Fazenda fazenda = new Fazenda();
        fazenda.setNomeDaFazenda("Fazenda Teste");

        fazendaRepository.save(fazenda);

        Animal animal = new Animal();
        animal.setId(2L);
        animal.setDataDeNascimento(LocalDate.of(2022, 1,1));
        animal.setFazenda(fazenda);
        animal.setEspecie("Bovino");
        animal.setRaca("Holandesa");
        animal.setPeso(BigDecimal.valueOf(15));
        animal.setSexo(Sexo.FEMEA);
        animal.setQuantidadeDeCrias(0);

        Animal animalSalvo = animalRepository.save(animal);

        String content = objectMapper.writeValueAsString(animalSalvo);

        MvcResult result = mockMvc.perform(post("/api/animais/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Animal animaisResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Animal.class);

        assertNotNull(animaisResponse.getId());
        assertEquals(animaisResponse.getDataDeNascimento(),LocalDate.of(2022, 1,1));
        assertEquals(animaisResponse.getFazenda(),fazenda);
        assertEquals(animaisResponse.getEspecie(),"Bovino");
        assertEquals(animaisResponse.getRaca(),"Holandesa");
        assertEquals(animaisResponse.getPeso(), BigDecimal.valueOf(15));
        assertEquals(animaisResponse.getQuantidadeDeCrias(),0);

    }

    @Test
    @SneakyThrows
    void shouldThrowNotFoundErrorWhenUpdateAnimalWithNotExistsId() {
        Animal animal = new Animal();
        animal.setId(3L);

        String jsonEnderecoString = objectMapper.writeValueAsString(animal);

        mockMvc.perform(put("/api/enderecos/" + animal.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEnderecoString))
                .andExpect(status().isNotFound());

    }

    @Test
    @SneakyThrows
    void shouldFindAnimalById() {

        Fazenda fazenda = new Fazenda();
        fazenda.setNomeDaFazenda("Fazenda Teste");

        fazendaRepository.save(fazenda);

        Animal animal = new Animal();
        animal.setId(4L);
        animal.setDataDeNascimento(LocalDate.of(2022, 1,1));
        animal.setFazenda(fazenda);
        animal.setEspecie("Bovino");
        animal.setRaca("Holandesa");
        animal.setPeso(BigDecimal.valueOf(15));
        animal.setSexo(Sexo.FEMEA);
        animal.setQuantidadeDeCrias(0);

        Animal animalSalvo = animalRepository.save(animal);

        String content = objectMapper.writeValueAsString(animalSalvo);

        MvcResult result = mockMvc.perform(get("/api/animais/" + animalSalvo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Animal animaisResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Animal.class);

        assertNotNull(animaisResponse.getId());
        assertEquals(animaisResponse.getDataDeNascimento(),LocalDate.of(2022, 1,1));
        assertEquals(animaisResponse.getFazenda(),fazenda);
        assertEquals(animaisResponse.getEspecie(),"Bovino");
        assertEquals(animaisResponse.getRaca(),"Holandesa");
        assertEquals(animaisResponse.getPeso(), BigDecimal.valueOf(15));
        assertEquals(animaisResponse.getQuantidadeDeCrias(),0);
    }

    @Test
    @SneakyThrows
    void shouldUpdateAnimal() {
        Fazenda fazenda = new Fazenda();
        fazenda.setNomeDaFazenda("Fazenda Teste");

        fazendaRepository.save(fazenda);

        Animal animal = new Animal();
        animal.setId(5L);
        animal.setDataDeNascimento(LocalDate.of(2022, 1,1));
        animal.setFazenda(fazenda);
        animal.setEspecie("Bovino");
        animal.setRaca("Holandesa");
        animal.setPeso(BigDecimal.valueOf(15));
        animal.setSexo(Sexo.FEMEA);
        animal.setQuantidadeDeCrias(0);

        Animal animalSalvo = animalRepository.save(animal);

        animalSalvo.setPeso(BigDecimal.valueOf(14));

        String content = objectMapper.writeValueAsString(animalSalvo);

        MvcResult result = mockMvc.perform(put("/api/animais/" + animalSalvo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Animal animaisResponse = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Animal.class);

        assertNotNull(animaisResponse.getId());
        assertEquals(animaisResponse.getDataDeNascimento(),LocalDate.of(2022, 1,1));
        assertEquals(animaisResponse.getFazenda(),fazenda);
        assertEquals(animaisResponse.getEspecie(),"Bovino");
        assertEquals(animaisResponse.getRaca(),"Holandesa");
        assertEquals(animaisResponse.getPeso(), BigDecimal.valueOf(14));
        assertEquals(animaisResponse.getQuantidadeDeCrias(),0);
    }


}
