package com.books.webook.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public AuthorEntity() {
    }

    public AuthorEntity(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        String birth = birthYear != null ? birthYear.toString() : "Desconhecido";
        String death = deathYear != null ? deathYear.toString() : "Desconhecido";
        return String.format("Nome: %s, Nascimento: %s, Falecimento: %s",
                name != null ? name : "Desconhecido", birth, death);
    }
}
