package youngeun.site;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import youngeun.site.repository.GuestBookRepository;
import youngeun.site.repository.GuestBookRepositoryImpl;
import youngeun.site.service.GuestBookService;
import youngeun.site.service.GuestBookServiceImpl;

import javax.sql.DataSource;

@Configuration
public class SiteConfig {
    private final DataSource dataSource;

    public SiteConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public GuestBookService guestBookService() {
        return new GuestBookServiceImpl(guestBookRepository());
    }

    @Bean
    public GuestBookRepository guestBookRepository() {
        return new GuestBookRepositoryImpl(dataSource);
    }
}
