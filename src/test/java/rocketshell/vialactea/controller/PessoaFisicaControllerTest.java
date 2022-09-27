package rocketshell.vialactea.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaFisicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRequestSemAutenticacao() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/pessoas-fisicas");

        mockMvc.perform(request).andExpect(status().is4xxClientError());

    }

}
