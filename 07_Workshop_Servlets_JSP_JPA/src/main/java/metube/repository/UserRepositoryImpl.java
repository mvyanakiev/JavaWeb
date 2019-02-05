package metube.repository;

import metube.domain.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("metube")
                .createEntityManager();
    }

    @Override
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<User> findAll() {
        this.entityManager.getTransaction().begin();
        List<User> allUsers =  this.entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return allUsers;
    }

    @Override
    public User findById(String s) {
        User user = this.entityManager.createQuery("SELECT u from User u WHERE u.id = :id", User.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public long size() {
        this.entityManager.getTransaction().begin();
        long size = this.entityManager
                .createQuery("SELECT count(u) FROM User u", long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }
}
