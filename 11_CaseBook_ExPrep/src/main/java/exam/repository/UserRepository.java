package exam.repository;

import exam.domain.entities.User;
import exam.domain.models.service.UserServiceModel;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername (String username);

}
