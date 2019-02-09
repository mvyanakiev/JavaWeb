package metube.service;

import metube.domain.entities.User;
import metube.domain.model.service.UserServiceModel;
import metube.repository.UserRepository;
import metube.util.ModelMapper;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;

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
        user.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));

        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public boolean loginUser(UserServiceModel userServiceModel) {

        if (this.userRepository.findByUsernameAndPassword(userServiceModel.getUsername(),
                DigestUtils.sha256Hex(userServiceModel.getPassword())) != null ) {
            return  true;
        }

        return false;
    }

    @Override
    public UserServiceModel findUserByUsername(String username) {
        User user = this.userRepository.findByUserName(username);

        if (user == null) {
            throw new IllegalArgumentException();
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}