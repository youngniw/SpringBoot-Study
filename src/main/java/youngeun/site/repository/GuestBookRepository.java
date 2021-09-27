package youngeun.site.repository;

import youngeun.site.domain.Post;

import java.util.List;
import java.util.Optional;

public interface GuestBookRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findByWriterName(String writerName); //작성자 검색
    List<Post> findByContent(String content);       //내용으로 검색
    List<Post> findByWriterAndContent(String writerName, String content); //작성자+내용 검색
    List<Post> findAll();
    int size();
}
