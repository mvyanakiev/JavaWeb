package exam.service;


import exam.domain.entities.User;
import exam.domain.models.service.UserServiceModel;
import exam.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

        if (this.userRepository.save(user) == null) {
            return false;
        }
        return true;
    }

    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel) {





        // hardcode log
        // fixme
        User user = this.userRepository.findByUsername("ani");
        userServiceModel.setPassword("1");

        // uncomment this to work ok
//        User user = this.userRepository.findByUsername(userServiceModel.getUsername());







        if (user == null || !DigestUtils.sha256Hex(userServiceModel.getPassword()).equals(user.getPassword())) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel getUserById(String id) {
        User user = this.userRepository.findById(id);

        if (user == null) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getAllUsers() {
        List<UserServiceModel> users = this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());

        return users;
    }

    @Override
    public boolean addFriend(UserServiceModel userServiceModel) {
        User user = this.userRepository.update(this.modelMapper.map(userServiceModel, User.class));

        if (user != null) {
            return true;
        }

        return false;
    }

    //    @Override
//    public void userRegister(UserServiceModel userServiceModel) {
//        User user = this.modelMapper.map(userServiceModel, User.class);
//        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
//
//        this.userRepository.save(user);
//    }
//
//    @Override
//    public UserServiceModel userLogin(UserServiceModel userServiceModel) {
//        User user = this.userRepository.findByUsername(userServiceModel.getUsername());
//
//        if (user == null || !DigestUtils.sha256Hex(userServiceModel.getPassword()).equals(user.getPassword())) {
//            return null;
//        }
//
//        return this.modelMapper.map(user, UserServiceModel.class);
//    }
}
