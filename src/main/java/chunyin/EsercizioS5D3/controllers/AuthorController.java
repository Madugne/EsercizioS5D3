package chunyin.EsercizioS5D3.controllers;

import chunyin.EsercizioS5D3.entities.Author;
import chunyin.EsercizioS5D3.exceptions.BadRequestException;
import chunyin.EsercizioS5D3.payloads.NewAuthorDTO;
import chunyin.EsercizioS5D3.payloads.NewAuthorRespDTO;
import chunyin.EsercizioS5D3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public NewAuthorRespDTO saveAuthor(@RequestBody @Validated NewAuthorDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewAuthorRespDTO(this.authorService.saveAuthor(body).getId());
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

    @PostMapping("/upload")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile image) throws IOException {
        return this.authorService.uploadImage(image);

    }
}
