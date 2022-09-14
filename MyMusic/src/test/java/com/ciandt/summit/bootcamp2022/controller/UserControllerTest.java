package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.config.Interceptor;
import com.ciandt.summit.bootcamp2022.dto.Data;
import com.ciandt.summit.bootcamp2022.dto.UserDTO;
import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.entity.*;
import com.ciandt.summit.bootcamp2022.services.UserService;
import com.ciandt.summit.bootcamp2022.services.exceptions.ResourceNotFoundException;
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



import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private Interceptor interceptor;

    private UsernameDto usernameDto;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDTO userDTO;

    private UserType userType;

    private User user;
    private Playlist playlist;
    private Artist artist;
    private String userExistingId;
    private String userNotExistsId;
    private List<Music> musicList = new ArrayList<>();
    private List<User> usersList = new ArrayList<>();
    private List<Playlist> playlistMusic = new ArrayList<>();
    private List<Music> musicList2 = new ArrayList<>();


    @BeforeEach
    void setUp() throws Exception {
        playlist = new Playlist("fe5c979a-469b-4c4b-ab5e-64f72f653ea6", musicList, user);
        user = new User("fe5c979a-469b-4c4b-ab5e-64f72f653ea5", "joao", playlist, userType );
        userType = new UserType("1a2c3461-27f8-4976-afa6-8b5e51c024e4", "Comum", usersList);
        userDTO = new UserDTO("fe5c979a-469b-4c4b-ab5e-64f72f653ea5", "joao", userType );
        usernameDto = new UsernameDto(new Data("joao"));
        Mockito.doCallRealMethod().when(interceptor).preHandle(any(), any(), any());

    }

    @Test
    public void shouldReturnUserNotFound() throws Exception {
        userNotExistsId = "fe5c979a-469b-4c4b-ab5e-64f72f653ea6";
        Mockito.when(userService.getUserById(anyString())).thenThrow(ResourceNotFoundException.class);
        ResultActions result = mockMvc.perform(get("/users/userId",userNotExistsId )
                .header("name", "joao").header("token", "ZIIKXbvDLcs30v/7nzGxxwRHW6AHBEp94vEtSCFGZqK8ojfKYv39J92PI5Tw9EIHZLhtGJUaY2KZHwysFlfWvA==")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());

    }

    @Test
    public void shouldReturnUserDTO() throws Exception {
        userExistingId = "fe5c979a-469b-4c4b-ab5e-64f72f653ea5";
        Mockito.when(userService.getUserById(anyString())).thenReturn(userDTO);
        ResultActions result = mockMvc.perform(get("/users/userId",userExistingId )
                .header("name", "joao").header("token", "ZIIKXbvDLcs30v/7nzGxxwRHW6AHBEp94vEtSCFGZqK8ojfKYv39J92PI5Tw9EIHZLhtGJUaY2KZHwysFlfWvA==")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());

    }

}
