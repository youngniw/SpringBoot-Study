package youngeun.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import youngeun.site.domain.Post;
import youngeun.site.service.GuestBookService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SiteController {
    private final GuestBookService guestBookService;

    @Autowired
    public SiteController(GuestBookService guestBookService) {
        this.guestBookService = guestBookService;
    }

    @GetMapping("/intro")
    public String intro(Model model) {
        Date currentDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        String today = date.format(currentDate);

        model.addAttribute("date", today);
        return "intro";
    }

    @GetMapping("/guest-book")
    public String guestBook(Model model) {
        Date currentDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        String today = date.format(currentDate);

        List<Post> posts = guestBookService.findAllPosts();

        model.addAttribute("date", today);
        model.addAttribute("size", posts.size());
        model.addAttribute("posts", posts);     //방명록 목록을 전달해야 함!
        return "guest_book";
    }

    @GetMapping("/guest-book/search")
    public String guestBookSearchList(@RequestParam(value = "writername", required = false) String writerName, @RequestParam(value = "content", required = false) String content, Model model) {
        Date currentDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        String today = date.format(currentDate);

        List<Post> posts = new ArrayList<>();
        if (writerName != null && content == null) {
            posts = guestBookService.findWriterPosts(writerName);
            model.addAttribute("searchWriterName", writerName);
        }
        else if (writerName == null && content != null) {
            posts = guestBookService.findContentPosts(content);
            model.addAttribute("searchContent", content);
        }
        else if (writerName != null && content != null) {
            posts = guestBookService.findByWriterAndContent(writerName, content);
            model.addAttribute("searchWriterName", writerName);
            model.addAttribute("searchContent", content);
        }

        model.addAttribute("date", today);
        model.addAttribute("size", posts.size());
        model.addAttribute("posts", posts);     //방명록 목록을 전달해야 함!

        return "guest_book";
    }

    @PostMapping("/guest-book/add")
    public String addPost(PostForm postForm) {
        Post post = new Post();
        post.setWriterName(postForm.getName());
        post.setContent(postForm.getContent());
        post.setCreatedDatetime(LocalDateTime.now());

        guestBookService.leave(post);

        return "redirect:/guest-book";
    }
}
