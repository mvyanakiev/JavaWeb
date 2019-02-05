package metube.service;

import metube.domain.model.service.UserServiceModel;

public interface UserService {

    boolean registerUser (UserServiceModel userServiceModel);

    boolean loginUser(UserServiceModel userServiceModel);

}
