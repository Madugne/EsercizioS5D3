package chunyin.EsercizioS5D3.services;

import chunyin.EsercizioS5D3.entities.Author;
import chunyin.EsercizioS5D3.entities.BlogPost;
import chunyin.EsercizioS5D3.exceptions.BadRequestException;
import chunyin.EsercizioS5D3.exceptions.NotFoundException;
import chunyin.EsercizioS5D3.repositories.AuthorDAO;
import chunyin.EsercizioS5D3.repositories.BlogPostDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostDAO blogPostDAO;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<BlogPost> getBlogPosts(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostDAO.findAll(pageable);
    }

    public BlogPost saveAuthor(BlogPost newBlogPost){
        newBlogPost.setCoverURL("https://ui-avatars.com/api/?name="
                + newBlogPost.getAuthor()
                + newBlogPost.getCategory()
                + newBlogPost.getTitle()
                + newBlogPost.getContent()
                + newBlogPost.getReadingTime());
        return blogPostDAO.save(newBlogPost);
    }

    public BlogPost findById(UUID blogpostId){
        return this.blogPostDAO.findById(blogpostId).orElseThrow(() -> new NotFoundException(blogpostId));
    }

    public BlogPost findByIdAndUpdate(UUID blogpostId, BlogPost modifiedBlogPost){
        BlogPost foundBlogPost = this.findById(blogpostId);
        foundBlogPost.setCategory(modifiedBlogPost.getCategory());
        foundBlogPost.setTitle(modifiedBlogPost.getTitle());
        foundBlogPost.setContent(modifiedBlogPost.getContent());
        foundBlogPost.setReadingTime(modifiedBlogPost.getReadingTime());
        foundBlogPost.setCoverURL("https://ui-avatars.com/api/?name="
                + modifiedBlogPost.getAuthor()
                + modifiedBlogPost.getCategory()
                + modifiedBlogPost.getTitle()
                + modifiedBlogPost.getContent()
                + modifiedBlogPost.getReadingTime());
        return this.blogPostDAO.save(foundBlogPost);
    }

    public void findByIdAndDelete(UUID blogpostId){
        BlogPost found = this.findById(blogpostId);
        this.blogPostDAO.delete(found);
    }

    public String uploadImage(MultipartFile image) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;
    }
}
