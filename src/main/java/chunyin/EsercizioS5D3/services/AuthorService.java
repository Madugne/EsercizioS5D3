package chunyin.EsercizioS5D3.services;

import chunyin.EsercizioS5D3.entities.Author;
import chunyin.EsercizioS5D3.exceptions.BadRequestException;
import chunyin.EsercizioS5D3.exceptions.NotFoundException;
import chunyin.EsercizioS5D3.repositories.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorDAO authorDAO;

    public Page<Author> getAuthors(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorDAO.findAll(pageable);
    }

    public Author saveAuthor(Author newAuthor){
        this.authorDAO.findByEmail(newAuthor.getEmail()).ifPresent(
                author -> {throw new BadRequestException("L'email " + author.getEmail() + " è già in uso");
                }
        );
        newAuthor.setAvatarURL("https://ui-avatars.com/api/?name="+ newAuthor.getName() + "+" + newAuthor.getSurname());
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
        foundAuthor.setAvatarURL("https://ui-avatars.com/api/?name="+ modifiedAuthor.getName() + "+" + modifiedAuthor.getSurname());
        return this.authorDAO.save(foundAuthor);
    }

    public void findByIdAndDelete(UUID userId){
        Author found = this.findById(userId);
        this.authorDAO.delete(found);
    }
}
