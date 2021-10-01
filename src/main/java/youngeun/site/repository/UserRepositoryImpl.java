package youngeun.site.repository;

import youngeun.site.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager em;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByIdAndPassword(String id, String password) {
        List<User> result = em.createQuery("SELECT user FROM User user WHERE user.id = :id AND user.password = :password", User.class)
                .setParameter("id", id).setParameter("password", password)
                .getResultList();
        return result.stream().findAny();
    }
}
