package youngeun.site.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import youngeun.site.domain.Post;
import youngeun.site.domain.User;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GuestBookServiceIntegrationTest {
    @Autowired GuestBookService guestBookService;

    @Test
    void 글추가() throws Exception {
        User writer = new User();
        writer.setUserIdx(1L);

        Post post = new Post();
        post.setWriter(writer);
        post.setContent("첫 번째 방명록입니다.");
        post.setCreatedDatetime(LocalDateTime.now());

        Post leavePost = guestBookService.leave(post);
        System.out.println("leavePost = " + leavePost.toString());
        Assertions.assertThat(leavePost.getWriter().getUserIdx()).isEqualTo(1L);
    }
}