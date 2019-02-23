package exam.web.beans;

import exam.domain.models.service.UserServiceModel;
import exam.domain.models.view.UserFriendsViewModel;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserFriendsBean {

    private List<UserFriendsViewModel> models;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserFriendsBean() {
    }

    @Inject
    public UserFriendsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModels();
    }


    private void initModels() {

        String loggedUserId = (String) ((HttpSession) FacesContext.
                getCurrentInstance().getExternalContext().
                getSession(false)).getAttribute("id");

        UserServiceModel user =  this.modelMapper.map(this.userService.getUserById(loggedUserId), UserServiceModel.class);

        this.models = user.getFriends()
                .stream()
                .map(u -> this.modelMapper.map(u, UserFriendsViewModel.class))
                .collect(Collectors.toList());
    }

    public void removeFriend(String id){


    }


    public List<UserFriendsViewModel> getModels() {
        return this.models;
    }

    public void setModels(List<UserFriendsViewModel> models) {
        this.models = models;
    }
}
