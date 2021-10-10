package youngeun.site.service;

import youngeun.site.domain.Post;

import java.util.List;

public interface GuestBookService {
    Post leave(Post post);
    List<Post> findAllPosts();
    List<Post> findWriterPosts(Long writerIdx);
    List<Post> findContentPosts(String content);
    List<Post> findByWriterAndContent(Long writerIdx, String content);
    int postSize();
}
