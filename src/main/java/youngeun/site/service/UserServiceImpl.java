package youngeun.site.service;

import org.springframework.transaction.annotation.Transactional;
import youngeun.site.domain.User;
import youngeun.site.repository.UserRepository;

import java.util.Optional;

@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signup(User user) {
        User signUpUser = userRepository.save(user);
        if (signUpUser != null)
            return true;
        else
            return false;
    }

    @Override
    public Optional<User> login(String id, String password) {
        return userRepository.findByIdAndPassword(id, password);
    }
}
