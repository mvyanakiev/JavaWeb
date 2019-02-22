package fdmc2app.repository;

import fdmc2app.domain.entities.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CatRepositoryImpl implements CatRepository {

    private final EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cat save(Cat cat) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(cat);
        this.entityManager.getTransaction().commit();
        return cat;
    }

    @Override
    public List<Cat> findAll() {
        this.entityManager.getTransaction().begin();
        List<Cat> cats =  this.entityManager
                .createQuery("SELECT c FROM Cat c ", Cat.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return cats;
    }
}