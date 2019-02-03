package metube.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E, K> {

    E save(E entity);

    List<E> findAll();

//    E findById(K id);

    Optional<E> findById(K id);

}
