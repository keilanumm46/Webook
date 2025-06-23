package com.books.webook.repository;


import com.books.webook.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    // Busca livros cujo título contenha (ignore case) o texto informado
    List<BookEntity> findByTitleContainingIgnoreCase(String title);

    // Busca livros pelo idioma (ignore case)
    List<BookEntity> findByLanguageIgnoreCase(String language);
}
