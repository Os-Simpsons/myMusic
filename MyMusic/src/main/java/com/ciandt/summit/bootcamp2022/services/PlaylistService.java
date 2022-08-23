package com.ciandt.summit.bootcamp2022.services;

import com.ciandt.summit.bootcamp2022.dto.MusicDto;

public interface PlaylistService {

    void saveMusicToPlaylist(String id, MusicDto musicDto);
}
