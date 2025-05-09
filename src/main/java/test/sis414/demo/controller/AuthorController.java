package test.sis414.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.sis414.demo.model.Author;
import test.sis414.demo.repository.AuthorRepository;
import java.util.List;

@RestController
@RequestMapping("/authors")
@Tag(name="Author Controller", description="Operations about Author")
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
    @Operation(
            summary = "Create a new author",
            tags = {"Author Controller"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Author created successfully")
            }
    )
    public ResponseEntity<Author> addAuthor(@RequestBody Author authorRequest)
    {
        Author author = this.authorRepository.save(authorRequest);
        return new ResponseEntity<Author>(author, HttpStatus.CREATED);
    }
}
