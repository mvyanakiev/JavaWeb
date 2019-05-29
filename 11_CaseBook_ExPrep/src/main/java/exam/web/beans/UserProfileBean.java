package exam.web.beans;

import exam.domain.models.service.UserServiceModel;
import exam.domain.models.view.UserProfilViewModel;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserProfileBean {

    private UserProfilViewModel model;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        UserServiceModel userServiceModel = this.userService.getUserById(id);

        if (userServiceModel == null) {
            throw new IllegalArgumentException("Wrong!!1!");
        }
        this.model = this.modelMapper.map(userServiceModel, UserProfilViewModel.class);
    }

    public UserProfilViewModel getModel() {
        return this.model;
    }

    public void setModel(UserProfilViewModel model) {
        this.model = model;
    }
}
