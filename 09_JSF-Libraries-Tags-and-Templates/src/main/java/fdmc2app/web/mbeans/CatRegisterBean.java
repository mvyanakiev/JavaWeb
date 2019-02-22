package fdmc2app.web.mbeans;

import fdmc2app.domain.models.binding.CatCreateBindingModel;
import fdmc2app.domain.models.service.CatServiceModel;
import fdmc2app.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CatRegisterBean {

    private CatCreateBindingModel catCreateBindingModel;
    private CatService catService;
    private ModelMapper modelMapper;

    public CatRegisterBean() {
    }

    @Inject
    public CatRegisterBean(CatService catService, ModelMapper modelMapper) {
        this.catCreateBindingModel = new CatCreateBindingModel();
        this.catService = catService;
        this.modelMapper = modelMapper;
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return this.catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }

    public void createCat() throws IOException {
        this.catService.saveCat(this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class));

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}