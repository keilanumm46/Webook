package com.books.webook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    @JsonAlias("name")
    private String name;

    @JsonAlias("birth_year")
    private Integer birthYear;

    @JsonAlias("death_year")
    private Integer deathYear;

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
