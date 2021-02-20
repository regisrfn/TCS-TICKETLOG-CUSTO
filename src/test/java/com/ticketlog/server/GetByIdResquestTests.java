package com.ticketlog.server;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ticketlog.server.model.PCusto;

import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class GetByIdResquestTests {

        @Autowired
        private JdbcTemplate jdbcTemplate;
        @Autowired
        private MockMvc mockMvc;

        private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        @BeforeEach
        void clearTable() {
                jdbcTemplate.update("DELETE FROM pcusto");
        }

        @Test
        void itShouldGetPCustoById() throws Exception {
                JSONObject my_obj = new JSONObject();

                my_obj.put("id", "95d1421a-4071-4a9b-b3d3-603865c89097");
                my_obj.put("custoPorPessoa", 123.45);
                my_obj.put("desconto", 12.3);
                my_obj.put("valorCorte", 50000);

                MvcResult result = mockMvc.perform(post("/api/v1/custo/save").contentType(MediaType.APPLICATION_JSON)
                                .content(my_obj.toString())).andExpect(status().isOk()).andReturn();

                PCusto pcustoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), PCusto.class);

                mockMvc.perform(get("/api/v1/custo/get/" + pcustoResponse.getId())
                                .contentType(MediaType.APPLICATION_JSON).content(my_obj.toString()))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.custoPorPessoa", Is.is(123.45)))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.desconto", Is.is(12.3)))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.valorCorte", Is.is(50000)))
                                .andExpect(status().isOk()).andReturn();

        }

        @Test
        void itShouldNotGetPCustoById() throws Exception {
                JSONObject my_obj = new JSONObject();

                my_obj.put("id", "95d1421a-4071-4a9b-b3d3-603865c89097");
                my_obj.put("custoPorPessoa", 123.45);
                my_obj.put("desconto", 12.3);
                my_obj.put("valorCorte", 50000);

               mockMvc.perform(post("/api/v1/custo/save").contentType(MediaType.APPLICATION_JSON)
                                .content(my_obj.toString())).andExpect(status().isOk()).andReturn();

                mockMvc.perform(get("/api/v1/custo/get/1"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.apiError",
                                                Is.is("Formato de id invalido")))
                                .andExpect(status().isBadRequest()).andReturn();

                mockMvc.perform(get("/api/v1/custo/get/76e731c1-ad78-4e88-8501-f769c55b48d5"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.apiError",
                                                Is.is("Parametro não encontrado")))
                                .andExpect(status().isNotFound()).andReturn();

        }
}