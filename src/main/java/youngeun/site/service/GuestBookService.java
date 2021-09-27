package youngeun.site.service;

import youngeun.site.domain.Post;

import java.util.List;

public interface GuestBookService {
    Long leave(Post post);
    List<Post> findAllPosts();
    List<Post> findWriterPosts(String writerName);
    List<Post> findContentPosts(String content);
    List<Post> findByWriterAndContent(String writerName, String content);
    int postSize();
}
