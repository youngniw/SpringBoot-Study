package youngeun.site.repository;

import youngeun.site.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByIdAndPassword(String id, String password);
}
