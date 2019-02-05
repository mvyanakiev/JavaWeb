package metube.repository;

import metube.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {

    private final EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("metube")
                .createEntityManager();
    }

    @Override
    public Tube save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Tube> findAll() {
        this.entityManager.getTransaction().begin();
        List<Tube> allTubes =  this.entityManager.createQuery("SELECT t FROM Tube t", Tube.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return allTubes;
    }

    @Override
    public Tube findById(String s) {
        Tube tube = this.entityManager.createQuery("SELECT t from Tube t WHERE t.id = :id", Tube.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    public long size() {
        this.entityManager.getTransaction().begin();
        long size = this.entityManager
                .createQuery("SELECT count(t) FROM Tube t", long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }
}
