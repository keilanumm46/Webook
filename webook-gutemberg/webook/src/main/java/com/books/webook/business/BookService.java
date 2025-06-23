package com.books.webook.business;

import com.books.webook.model.Author;
import com.books.webook.model.Book;
import com.books.webook.entity.AuthorEntity;
import com.books.webook.entity.BookEntity;
import com.books.webook.repository.AuthorRepository;
import com.books.webook.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private static final String GUTENDEX_API = "https://gutendex.com/books/";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookEntity searchBookByTitle(String query) {
        try {
            // Verifica se já existe no banco
//            List<BookEntity> existingBooks = bookRepository.findByTitleContainingIgnoreCase(query);
//            if (!existingBooks.isEmpty()) {
//                System.out.println("📚 Livro encontrado no banco de dados: " + existingBooks.get(0).getTitle());
//                return existingBooks.get(0);
//            }

            System.out.println("🌐 Buscando livro na API Gutendex...");

            String url = GUTENDEX_API + "?search=" + query.replace(" ", "%20");
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);
            JsonNode results = root.path("results");

            if (!results.isEmpty()) {
                Book book = objectMapper.treeToValue(results.get(0), Book.class);

                AuthorEntity author = null;
                Author bookAuthor = book.getAuthor();
                if (bookAuthor != null) {
                    author = new AuthorEntity();
                    author.setName(bookAuthor.getName());
                    author.setBirthYear(bookAuthor.getBirthYear());
                    author.setDeathYear(bookAuthor.getDeathYear());
                }

                BookEntity entity = new BookEntity();
                entity.setTitle(book.getTitle());
                entity.setLanguage(book.getLanguage());
                entity.setDownloadCount(book.getDownloadCount());
                entity.setAuthor(author); // Cascade persistirá o autor

                BookEntity saved = bookRepository.save(entity);
                System.out.println("✅ Livro salvo no banco: " + saved.getTitle());

                return saved;
            }

            logger.warn("❌ Nenhum livro encontrado para a busca: {}", query);
            return null;
        } catch (Exception e) {
            logger.error("Erro ao buscar livro: {}", e.getMessage());
            return null;
        }
    }

    public List<BookEntity> listAllBooks() {
        System.out.println("📚 Listando todos os livros do banco.");
        return bookRepository.findAll();
    }

    public List<BookEntity> listBooksByLanguage(String language) {
        System.out.println("🌐 Listando livros do idioma '" + language + "'");
        return bookRepository.findByLanguageIgnoreCase(language);
    }

    public List<AuthorEntity> listAllAuthors() {
        System.out.println("👥 Listando todos os autores.");
        return authorRepository.findAll();
    }

    public List<AuthorEntity> listAuthorsAliveInYear(int year) {
        System.out.println("📅 Listando autores vivos no ano " + year);
        return authorRepository.findAll().stream()
                .filter(a -> a.getBirthYear() != null && a.getBirthYear() <= year &&
                        (a.getDeathYear() == null || a.getDeathYear() >= year))
                .toList();
    }
}