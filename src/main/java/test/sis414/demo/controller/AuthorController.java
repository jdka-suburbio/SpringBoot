package test.sis414.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import test.sis414.demo.model.Author;
import test.sis414.demo.repository.AuthorRepository;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository)
    {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> getAuthors()
    {
        return this.authorRepository.findAll();
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author)
    {
        return this.authorRepository.save(author);
    }
}
