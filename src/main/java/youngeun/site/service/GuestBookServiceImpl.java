package youngeun.site.service;

import youngeun.site.domain.Post;
import youngeun.site.repository.GuestBookRepository;

import java.util.List;

public class GuestBookServiceImpl implements GuestBookService {
    private final GuestBookRepository guestBookRepository;

    public GuestBookServiceImpl(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    @Override
    public Long leave(Post post) {
        guestBookRepository.save(post);
        return post.getId();
    }

    @Override
    public List<Post> findAllPosts() {
        return guestBookRepository.findAll();
    }

    @Override
    public List<Post> findWriterPosts(String writerName) {
        return guestBookRepository.findByWriterName(writerName);
    }

    @Override
    public List<Post> findContentPosts(String content) {
        return guestBookRepository.findByContent(content);
    }

    @Override
    public List<Post> findByWriterAndContent(String writerName, String content) {
        return guestBookRepository.findByWriterAndContent(writerName, content);
    }

    @Override
    public int postSize() {
        return guestBookRepository.size();
    }
}
