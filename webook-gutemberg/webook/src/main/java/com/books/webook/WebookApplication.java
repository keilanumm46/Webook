package com.books.webook;

import com.books.webook.business.BookService;
import com.books.webook.ui.BookSearchConsole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class WebookApplication implements CommandLineRunner {
	private final BookService bookService;

	public WebookApplication(BookService bookService) {
		this.bookService = bookService;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebookApplication.class, args);
	}

	@Override
	public void run(String... args) {
		BookSearchConsole console = new BookSearchConsole(bookService);
		console.start();
	}
}