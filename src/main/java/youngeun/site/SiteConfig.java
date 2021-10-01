package youngeun.site;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import youngeun.site.repository.GuestBookRepository;
import youngeun.site.repository.GuestBookRepositoryImpl;
import youngeun.site.repository.UserRepository;
import youngeun.site.repository.UserRepositoryImpl;
import youngeun.site.service.GuestBookService;
import youngeun.site.service.GuestBookServiceImpl;
import youngeun.site.service.UserService;
import youngeun.site.service.UserServiceImpl;

import javax.persistence.EntityManager;

@Configuration
public class SiteConfig {
    private final EntityManager em;

    public SiteConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(em);
    }

    @Bean
    public GuestBookService guestBookService() {
        return new GuestBookServiceImpl(guestBookRepository());
    }

    @Bean
    public GuestBookRepository guestBookRepository() {
        return new GuestBookRepositoryImpl(em);
    }
}
