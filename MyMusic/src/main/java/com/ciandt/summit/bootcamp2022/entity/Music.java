package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Musicas")
public class Music {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="Id")
    private String id;

    @Column(name="Nome")
    private String name;

    @ManyToOne
    @JoinColumn(name="ArtistaId")
    private Artist artistId;

}
