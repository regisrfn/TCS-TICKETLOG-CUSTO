package com.ticketlog.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ticketlog.server.model.PCusto;

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

@SpringBootTest
@AutoConfigureMockMvc
public class PostRequestTests {

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
    void itShouldSaveParametroCusto() throws Exception {
        JSONObject my_obj = new JSONObject();

        my_obj.put("id", "95d1421a-4071-4a9b-b3d3-603865c89097");
        my_obj.put("custoPorPessoa", 123.45);
        my_obj.put("desconto", 12.3);
        my_obj.put("valorCorte", 50000);


        MvcResult result = mockMvc
                .perform(post("/api/v1/custo/save").contentType(MediaType.APPLICATION_JSON)
                        .content(my_obj.toString())).andExpect(status().isOk())
                .andReturn();

        PCusto response = objectMapper.readValue(result.getResponse().getContentAsString(), PCusto.class);
        assertThat(response.getCustoPorPessoa()).isEqualTo(123.45);
        assertThat(response.getDesconto()).isEqualTo(12.3);
        assertThat(response.getValorCorte()).isEqualTo(50000);
    }
}
