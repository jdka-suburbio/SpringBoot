package test.sis414.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.sis414.demo.model.Author;
import test.sis414.demo.repository.AuthorRepository;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/authors")
@Tag(name="Author", description="This endpoint permits create, read, update and delete operations")
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

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an author",
            tags = {"Author"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Author was delete successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id)
    {
        this.authorRepository.deleteById(id);
        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @Operation(
            summary = "Create a new author",
            tags = {"Author"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Author created successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<Author> addAuthor(@RequestBody Author authorRequest)
    {
        authorRequest.setId(null);
        Author author = this.authorRepository.save(authorRequest);
        return new ResponseEntity<Author>(author, HttpStatus.CREATED);
    }
}



