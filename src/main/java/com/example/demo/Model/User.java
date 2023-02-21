package com.example.demo.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private String email;

    @ManyToMany(fetch= FetchType.EAGER)
    private Collection<Roles> role=new ArrayList<>();

    public User(String name, String password, String email) {
        this.name=name;
        this.password=password;
        this.email=email;
    }
}
