package com.books.webook.ui;

import com.books.webook.business.BookService;

import java.util.List;
import java.util.Scanner;

import com.books.webook.entity.AuthorEntity;
import com.books.webook.entity.BookEntity;

public class BookSearchConsole {
    private final BookService bookService;
    private final Scanner scanner;

    public BookSearchConsole(BookService bookService) {
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1" -> searchBookByTitle();
                case "2" -> listAllBooks();
                case "3" -> listBooksByLanguage();
                case "4" -> listAllAuthors();
                case "5" -> listAuthorsAliveInYear();
                case "6" -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== Catálogo de Livros Gutendex ===");
        System.out.println("1. Buscar livro por título");
        System.out.println("2. Listar todos os livros");
        System.out.println("3. Listar livros por idioma");
        System.out.println("4. Listar todos os autores");
        System.out.println("5. Listar autores vivos em um ano");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void searchBookByTitle() {
        System.out.print("Digite o termo de busca: ");
        String query = scanner.nextLine();
        if (query.trim().isEmpty()) {
            System.out.println("Erro: O termo de busca não pode estar vazio.");
            return;
        }
        BookEntity book = bookService.searchBookByTitle(query);
        if (book == null) {
            System.out.println("Nenhum livro encontrado ou erro na busca.");
        } else {
            System.out.println("\nLivro encontrado:");
            System.out.println(book.getTitle() + " - " + (book.getAuthor() != null ? book.getAuthor().getName() : "Autor desconhecido"));
        }
    }

    private void listAllBooks() {
        List<BookEntity> books = bookService.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("Nenhum livro no catálogo.");
        } else {
            books.forEach(b -> System.out.println(b.getTitle() + " - " + b.getLanguage()));
        }
    }

    private void listBooksByLanguage() {
        System.out.print("Digite o idioma (ex.: en, pt): ");
        String language = scanner.nextLine();
        List<BookEntity> books = bookService.listBooksByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            books.forEach(b -> System.out.println(b.getTitle()));
        }
    }

    private void listAllAuthors() {
        List<AuthorEntity> authors = bookService.listAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("Nenhum autor.");
        } else {
            authors.forEach(a -> System.out.println(a.getName()));
        }
    }

    private void listAuthorsAliveInYear() {
        System.out.print("Digite o ano: ");
        try {
            int year = Integer.parseInt(scanner.nextLine());
            List<AuthorEntity> authors = bookService.listAuthorsAliveInYear(year);
            if (authors.isEmpty()) {
                System.out.println("Nenhum autor encontrado.");
            } else {
                authors.forEach(a -> System.out.println(a.getName()));
            }
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido.");
        }
    }
}

