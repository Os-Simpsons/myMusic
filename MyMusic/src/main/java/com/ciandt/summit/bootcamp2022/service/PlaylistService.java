package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicDto;

public interface PlaylistService {

    void saveMusicToPlaylist(String id, MusicDto musicDto);
}
