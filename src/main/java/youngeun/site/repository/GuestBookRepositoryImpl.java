package youngeun.site.repository;

import youngeun.site.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class GuestBookRepositoryImpl implements GuestBookRepository {
    private final EntityManager em;

    public GuestBookRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Post save(Post post) {
        System.out.println("RepositoryImpl");
        em.persist(post);
        return post;
    }

    @Override
    public Optional<Post> findByGuestbookIdx(Long guestbookIdx) {
        Post post = em.find(Post.class, guestbookIdx);
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findByWriterIdx(Long writerIdx) {
        List<Post> resultList = em.createQuery("SELECT p FROM Post p WHERE p.writerIdx = :writerIdx", Post.class)
                .setParameter("writerIdx", writerIdx)
                .getResultList();
        return resultList;
    }

    @Override
    public List<Post> findByContent(String content) {
        List<Post> resultList = em.createQuery("SELECT p FROM Post p WHERE p.content LIKE :content", Post.class)
                .setParameter("content", "%"+content+"%")
                .getResultList();
        return resultList;
    }

    @Override
    public List<Post> findByWriterIdxAndContent(Long writerIdx, String content) {
        List<Post> resultList = em.createQuery("SELECT p FROM Post p WHERE p.writerIdx = :writerIdx AND p.content like :content", Post.class)
                .setParameter("writerIdx", writerIdx)
                .setParameter("content", "%"+content+"%")
                .getResultList();
        return resultList;
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("SELECT p FROM Post p", Post.class).getResultList();
    }

    @Override
    public int size() {
        int result = em.createQuery("SELECT COUNT(p) FROM Post p", Long.class).getSingleResult().intValue();
        return result;
    }
}
