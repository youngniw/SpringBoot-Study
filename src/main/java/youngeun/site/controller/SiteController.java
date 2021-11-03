package youngeun.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import youngeun.site.domain.Post;
import youngeun.site.domain.User;
import youngeun.site.service.GuestBookService;
import youngeun.site.service.UserService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class SiteController {
    private final GuestBookService guestBookService;
    private final UserService userService;

    @Autowired
    public SiteController(GuestBookService guestBookService, UserService userService) {
        this.guestBookService = guestBookService;
        this.userService = userService;
    }

    @GetMapping("/intro")
    public String intro(Model model) {
        Date currentDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        String today = date.format(currentDate);

        model.addAttribute("date", today);
        return "intro";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        if (model.asMap().containsKey("isWrong"))
            model.addAttribute("isWrong", true);
        else if (model.asMap().containsKey("isRegister"))
            model.addAttribute("isRegister", true);

        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(LoginForm loginForm, RedirectAttributes rttr) {
        Optional<User> loginUser = userService.login(loginForm.getId(), loginForm.getPassword());
        System.out.println("loginUser = " + loginUser);
        if (!loginUser.isPresent()) {
            rttr.addFlashAttribute("isWrong", true);
            return "redirect:/login";
        }
        else {
            rttr.addFlashAttribute("user", loginUser.get());
            return "redirect:/guestbook";
        }
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        if (model.asMap().containsKey("isFailed"))
            model.addAttribute("isFailed", true);
        return "register";
    }

    @PostMapping("/register")
    public String register(RegisterForm registerForm, RedirectAttributes rttr) {
        User newUser = new User();
        newUser.setId(registerForm.getId());
        newUser.setPassword(registerForm.getPassword());
        newUser.setName(registerForm.getName());
        newUser.setNickname(registerForm.getNickname());

        boolean success = userService.register(newUser);
        if (!success) {
            rttr.addFlashAttribute("isFailed", true);
            return "redirect:/register";
        }
        else {
            rttr.addFlashAttribute("isRegister", true);
            return "redirect:/login";
        }
    }

    @GetMapping("/guestbook")
    public String guestBook(Model model) {
        if (model.asMap().containsKey("user")) {
            Map<String, Object> map = model.asMap();

            User user = (User) map.get("user");
            model.addAttribute("userIdx", user.getUserIdx());
            model.addAttribute("userNick", user.getNickname());
        }

        Date currentDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        String today = date.format(currentDate);

        List<Post> posts = guestBookService.findAllPosts();

        model.addAttribute("date", today);
        model.addAttribute("size", posts.size());
        model.addAttribute("posts", posts);     //방명록 목록을 전달해야 함!
        return "guest_book";
    }

    @GetMapping("/guestbook/search")
    public String guestBookSearchList(@RequestParam("content") String content, Model model) {
        Date currentDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        String today = date.format(currentDate);

        List<Post> posts = guestBookService.findContentPosts(content);
        model.addAttribute("searchContent", content);

        model.addAttribute("date", today);
        model.addAttribute("size", posts.size());
        model.addAttribute("posts", posts);     //방명록 목록을 전달해야 함!

        return "guest_book";
    }

    @PostMapping("/guestbook/add")
    public String addPost(PostForm postForm) {
        User writer = new User();
        writer.setUserIdx(postForm.getUserIdx());

        Post post = new Post();
        post.setWriter(writer);
        post.setContent(postForm.getContent());
        post.setCreatedDatetime(LocalDateTime.now());

        guestBookService.leave(post);

        return "redirect:/guestbook";
    }
}
