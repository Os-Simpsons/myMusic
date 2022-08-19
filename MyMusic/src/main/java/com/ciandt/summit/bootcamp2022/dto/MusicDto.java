package com.ciandt.summit.bootcamp2022.dto;

import com.ciandt.summit.bootcamp2022.entity.Artist;

public class MusicDto {

    private String id;
    private String name;
    private Artist artist;

    public MusicDto() {

    }

    public MusicDto(String id, String name, Artist artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artistId) {
        this.artist = artistId;
    }
}
