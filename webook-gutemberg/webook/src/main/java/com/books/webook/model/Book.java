package com.books.webook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    @JsonAlias("title")
    private String title;

    @JsonAlias("authors")
    private List<Author> authors;

    @JsonAlias("languages")
    private List<String> languages;

    @JsonAlias("download_count")
    private int downloadCount;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Author getAuthor() {
        return authors != null && !authors.isEmpty() ? authors.get(0) : null;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getLanguage() {
        return languages != null && !languages.isEmpty() ? languages.get(0) : "Desconhecido";
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        String authorStr = getAuthor() != null ? getAuthor().getName() : "Desconhecido";
        return String.format("Título: %s, Autor: %s, Idioma: %s, Downloads: %d",
                title != null ? title : "Desconhecido",
                authorStr,
                getLanguage(),
                downloadCount);
    }
}
