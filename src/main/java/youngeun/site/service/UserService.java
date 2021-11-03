package youngeun.site.service;

import youngeun.site.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> login(String id, String password);
    boolean register(User user);
}
