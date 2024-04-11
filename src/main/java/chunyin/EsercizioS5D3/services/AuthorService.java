package chunyin.EsercizioS5D3.services;

import chunyin.EsercizioS5D3.entities.Author;
import chunyin.EsercizioS5D3.exceptions.BadRequestException;
import chunyin.EsercizioS5D3.exceptions.NotFoundException;
import chunyin.EsercizioS5D3.payloads.NewAuthorDTO;
import chunyin.EsercizioS5D3.repositories.AuthorDAO;
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
public class AuthorService {
    @Autowired
    private AuthorDAO authorDAO;
    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Author> getAuthors(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorDAO.findAll(pageable);
    }

    public Author saveAuthor(NewAuthorDTO body){
        this.authorDAO.findByEmail(body.email()).ifPresent(
                author -> {throw new BadRequestException("L'email " + author.getEmail() + " è già in uso");
                }
        );
        Author newAuthor = new Author(body.name(), body.surname(), body.email(), body.birthday(),
                "https://ui-avatars.com/api/?name="+ body.name() + "+" + body.surname());
        return authorDAO.save(newAuthor);
    }

    public Author findById(UUID authorId){
        return this.authorDAO.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author findByIdAndUpdate(UUID userId, Author modifiedAuthor){
        Author foundAuthor = this.findById(userId);
        foundAuthor.setName(modifiedAuthor.getName());
        foundAuthor.setSurname(modifiedAuthor.getSurname());
        foundAuthor.setEmail(modifiedAuthor.getEmail());
        foundAuthor.setAvatarURL(modifiedAuthor.getAvatarURL());
        return this.authorDAO.save(foundAuthor);
    }

    public void findByIdAndDelete(UUID userId){
        Author found = this.findById(userId);
        this.authorDAO.delete(found);
    }

    public String uploadImage(MultipartFile image) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;
    }
}
