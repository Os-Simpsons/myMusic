package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.Data;
import com.ciandt.summit.bootcamp2022.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.services.PlaylistService;
import com.ciandt.summit.bootcamp2022.services.PlaylistServiceImpl;
import com.ciandt.summit.bootcamp2022.services.exceptions.ResourceNotFoundException;
import com.ciandt.summit.bootcamp2022.utils.exceptions.InvalidLogDataException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PlaylistController.class)
public class PlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaylistServiceImpl playlistService;

    @Autowired
    private ObjectMapper objectMapper;
    private String playlistExistingId;
    private String playlistNotExistId;

    private String musicDTONotExistId;
    private Playlist playlist;
    private MusicDto musicDto;
    private UsernameDto usernameDto;
    private Artist artist;
    private List<Music> musicList = new ArrayList<>();
    private List<User> usersList = new ArrayList<>();
    private List<Playlist> playlistMusic = new ArrayList<>();
    private List<Music> musicList2 = new ArrayList<>();

    private Music musicReturned;


    @BeforeEach
    void setUp() throws Exception {
        playlistExistingId = "a39926f4-6acb-4497-884f-d4e5296ef652";
        playlistNotExistId = "070d9496-ae38-4587-8ca6-2ed9b36fb198";
        musicDTONotExistId = "32844fdd-bb76-4c0a-9627-e34ddc9fd892";
        playlist = new Playlist(playlistExistingId, musicList, usersList);
        artist = new Artist("32844fdd-bb76-4c0a-9627-e34ddc9fd892", "The Beatles", musicList2);
        musicDto = new MusicDto("67f5976c-eb1e-404e-8220-2c2a8a23be47", "Hippy Hippy Shake", artist);
        usernameDto = new UsernameDto(new Data("joao",
                "ZIIKXbvDLcs30v/7nzGxxwRHW6AHBEp94vEtSCFGZqK8ojfKYv39J92PI5Tw9EIHZLhtGJUaY2KZHwysFlfWvA=="));
        musicReturned = new Music("67f5976c-eb1e-404e-8220-2c2a8a23be47", "Hippy Hippy Shake", artist, playlistMusic);

        Mockito.doNothing().when(playlistService).saveMusicToPlaylist(playlistExistingId, musicDto,usernameDto);

       // Mockito.when(playlistService.saveMusicToPlaylist(playlistNotExistId, musicDto, usernameDto)).thenThrow(ResourceNotFoundException.class)
        //Mockito.doThrow(InvalidLogDataException.class).when(playlistService).saveMusicToPlaylist(playlistExistingId, musicDto, usernameDto);
    }

    @Test
    public void shuouldSaveMusicToPlaylistAndReturn201() throws Exception {

        String json = objectMapper.writeValueAsString(musicDto);
        ResultActions result = mockMvc.perform(put("/playlist/{playlistId}/musicas", playlistExistingId)
                       .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("name", "joao")
                .header("token", "ZIIKXbvDLcs30v/7nzGxxwRHW6AHBEp94vEtSCFGZqK8ojfKYv39J92PI5Tw9EIHZLhtGJUaY2KZHwysFlfWvA=="));


        result.andExpect(status().isCreated());

    }

    @Test
    public void shuouldSaveMusicToPlaylistAndReturn400() throws Exception {
        Mockito.doThrow(ResourceNotFoundException.class).when(playlistService).saveMusicToPlaylist(playlistNotExistId, musicDto, usernameDto);
        musicDto.setName(null);
        musicDto.setId(null);
        musicDto.setArtist(null);
        String json = objectMapper.writeValueAsString(musicDto);
        ResultActions result = mockMvc.perform(put("/playlist/{playlistId}/musicas", playlistNotExistId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("name", "joao")
                .header("token", "ZIIKXbvDLcs30v/7nzGxxwRHW6AHBEp94vEtSCFGZqK8ojfKYv39J92PI5Tw9EIHZLhtGJUaY2KZHwysFlfWvA=="));


        result.andExpect(status().isBadRequest());

    }
}
