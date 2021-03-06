package exam.web.beans;

import exam.domain.models.service.UserServiceModel;
import exam.domain.models.view.UserHomeViewModel;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {

    private List<UserHomeViewModel> models;
    private String loggedUserId;

    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.loggedUserId = (String) ((HttpSession) FacesContext.
                getCurrentInstance().getExternalContext().
                getSession(false)).getAttribute("userId");
        this.initModels();
    }

    public List<UserHomeViewModel> getModels() {
        return this.models;
    }

    public void setModels(List<UserHomeViewModel> models) {
        this.models = models;
    }

    private void initModels() {
        String username = (String) ((HttpSession) FacesContext.
                getCurrentInstance().getExternalContext().
                getSession(false)).getAttribute("username");

//        String loggedUserId = (String) ((HttpSession) FacesContext.
//                getCurrentInstance().getExternalContext().
//                getSession(false)).getAttribute("userId");

        UserServiceModel loggedInUser = this.userService.getUserById(loggedUserId);

        this.models = this.userService
                .getAllUsers()
                .stream()
                .filter(u -> !u.getUsername().equals(username) &&
                        !loggedInUser.getFriends()
                                .stream()
                                .map(f -> f.getUsername())
                                .collect(Collectors.toList()).contains(u.getUsername()))
                .map(u -> this.modelMapper.map(u, UserHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public void addFriend(String id) throws IOException {
//        String loggedUserId = (String) ((HttpSession) FacesContext.
//                getCurrentInstance().getExternalContext().
//                getSession(false)).getAttribute("userId");

        UserServiceModel loggedInUser = this.userService.getUserById(loggedUserId);
        UserServiceModel userServiceModel = this.userService.getUserById(id);
        loggedInUser.getFriends().add(userServiceModel);
        userServiceModel.getFriends().add(loggedInUser);

        if(!this.userService.addFriend(loggedInUser)) {
            throw new IllegalArgumentException("wrong!!!");
        }

        if(!this.userService.addFriend(userServiceModel)) {
            throw new IllegalArgumentException("wrong!!!");
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
