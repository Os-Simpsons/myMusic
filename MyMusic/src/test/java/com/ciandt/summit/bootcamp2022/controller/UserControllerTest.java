package com.ciandt.summit.bootcamp2022.controller;


import com.ciandt.summit.bootcamp2022.dto.Data;
import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.utils.TokenService;
import com.ciandt.summit.bootcamp2022.utils.exceptions.InvalidLogDataException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    private UsernameDto usernameDto;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() throws Exception {
        usernameDto = new UsernameDto(new Data("joao",
                ""));

    }


    @Test
    public void shouldReturnToken() throws Exception {

        Mockito.when(tokenService.createToken(any(UsernameDto.class))).thenReturn("ZIIKXbvDLcs30v/7nzGxxwRHW6AHBEp94vEtSCFGZqK8ojfKYv39J92PI5Tw9EIHZLhtGJUaY2KZHwysFlfWvA==");
        String json = objectMapper.writeValueAsString(usernameDto);
        ResultActions result = mockMvc.perform(post("/users")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));


        result.andExpect(content().string("ZIIKXbvDLcs30v/7nzGxxwRHW6AHBEp94vEtSCFGZqK8ojfKYv39J92PI5Tw9EIHZLhtGJUaY2KZHwysFlfWvA=="));

    }


    @Test
    public void shouldReturnInvalidLogDataException() throws Exception {

        Mockito.when(tokenService.createToken(any(UsernameDto.class))).thenThrow(InvalidLogDataException.class);
        String json = objectMapper.writeValueAsString(usernameDto);
        ResultActions result = mockMvc.perform(post("/users")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));


        result.andExpect(status().isUnauthorized());

    }
}
