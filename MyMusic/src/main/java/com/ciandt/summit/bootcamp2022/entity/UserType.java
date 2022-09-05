package com.ciandt.summit.bootcamp2022.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TipoUsuario")
public class UserType {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="Id")
    private String id;

    @Column(name="Descricao", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "userType")
    private List<User> users;

}
