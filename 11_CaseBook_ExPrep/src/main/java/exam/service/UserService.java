package exam.service;

import exam.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

//    void userRegister (UserServiceModel userServiceModel);
//
//    UserServiceModel userLogin(UserServiceModel userServiceModel);

    boolean registerUser(UserServiceModel userServiceModel);

    UserServiceModel loginUser(UserServiceModel userServiceModel);

    UserServiceModel getUserById (String id);

    List<UserServiceModel> getAllUsers();

    boolean addFriend(UserServiceModel userServiceModel);
}
