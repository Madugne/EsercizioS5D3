package chunyin.EsercizioS5D3.controllers;

import chunyin.EsercizioS5D3.entities.Author;
import chunyin.EsercizioS5D3.entities.BlogPost;
import chunyin.EsercizioS5D3.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;
    @GetMapping
    public Page<BlogPost> getAllBlogposts(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return this.blogPostService.getBlogPosts(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost saveBlogpost(@RequestBody BlogPost body){
        return this.blogPostService.saveAuthor(body);
    }

    @GetMapping("/{blogpostId}")
    public BlogPost findById(@PathVariable UUID blogpostId){
        return this.blogPostService.findById(blogpostId);
    }

    @PutMapping("/{blogpostId}")
    public BlogPost findByIdAndUpdate(@PathVariable UUID blogpostId, @RequestBody BlogPost body){
        return this.blogPostService.findByIdAndUpdate(blogpostId, body);
    }

    @DeleteMapping("/{blogpostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID blogpostId){
        this.blogPostService.findByIdAndDelete(blogpostId);
    }
}
