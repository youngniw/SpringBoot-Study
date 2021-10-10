package youngeun.site.service;

import org.springframework.transaction.annotation.Transactional;
import youngeun.site.domain.Post;
import youngeun.site.repository.GuestBookRepository;

import java.util.List;

@Transactional
public class GuestBookServiceImpl implements GuestBookService {
    private final GuestBookRepository guestBookRepository;

    public GuestBookServiceImpl(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    @Override
    public Post leave(Post post) {
        System.out.println("ServiceImpl");
        guestBookRepository.save(post);
        return post;
    }

    @Override
    public List<Post> findAllPosts() {
        return guestBookRepository.findAll();
    }

    @Override
    public List<Post> findWriterPosts(Long writerIdx) {
        return guestBookRepository.findByWriterIdx(writerIdx);
    }

    @Override
    public List<Post> findContentPosts(String content) {
        return guestBookRepository.findByContent(content);
    }

    @Override
    public List<Post> findByWriterAndContent(Long writerIdx, String content) {
        return guestBookRepository.findByWriterIdxAndContent(writerIdx, content);
    }

    @Override
    public int postSize() {
        return guestBookRepository.size();
    }
}
