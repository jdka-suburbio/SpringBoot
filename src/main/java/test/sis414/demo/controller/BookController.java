package test.sis414.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.sis414.demo.model.Author;
import test.sis414.demo.model.Book;
import test.sis414.demo.repository.AuthorRepository;
import test.sis414.demo.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private List<Book> bookList = new ArrayList<>();

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    /*
    public BookController()
    {
        Author author = new Author(1L, "Cervantes");
        Book book = new Book(1L, "Don Quijote", author);
        bookList.add(book);
    }
    */
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository)
    {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Book> getBooks()
    {
        return this.bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id)
    {
        logger.info("Id: " + id);
        Book item = this.bookRepository.findById(id).get();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(item);
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book)
    {
        Author author = book.getAuthor();
        if(author.getId() == null)
        {
            author = authorRepository.save(author);
            book.setAuthor(author);
        }
        return this.bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id)
    {
        long aux = id;
        logger.info("Removed: " + aux);
        boolean result = bookList.removeIf(b->b.getId() == aux);
        if(result)
        {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("The item was removed successful");
            //return new ResponseEntity<>("The item was removed successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error removing item", HttpStatus.NOT_FOUND);
    }
    /*
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book)
    {
        long aux = id;
        logger.info("Update: " + aux);
        System.out.println();
        for(Book item : bookList)
        {
            if(item.getId() == aux)
            {
                item.setTitle(book.getTitle());
                item.setAuthor(book.getAuthor());

                return item;
            }
        }
        return null;
    }
    */
}
