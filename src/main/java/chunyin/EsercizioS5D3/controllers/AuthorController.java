package chunyin.EsercizioS5D3.controllers;

import chunyin.EsercizioS5D3.entities.Author;
import chunyin.EsercizioS5D3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        return this.authorService.getAuthors(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author body){
        return this.authorService.saveAuthor(body);
    }

    @GetMapping("/{authorId}")
    public Author findById(@PathVariable UUID authorId){
        return this.authorService.findById(authorId);
    }

    @PutMapping("/{authorId}")
    public Author findByIdAndUpdate(@PathVariable UUID authorId, @RequestBody Author body){
        return this.authorService.findByIdAndUpdate(authorId, body);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID authorId){
        this.authorService.findByIdAndDelete(authorId);
    }
}
