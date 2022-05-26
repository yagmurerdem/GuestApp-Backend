package com.project.yagmurquestapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
    @Table(name="kisi")
    @Data
    public class Kisi {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        Long id;
        String userName;
        String password;
    }

