package fdmc2app.web.mbeans;

import fdmc2app.domain.models.view.CatListViewModel;
import fdmc2app.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ListAllCatsBean {

    private List<CatListViewModel> cats;

    private CatService catService;
    private ModelMapper modelMapper;


    public ListAllCatsBean() {
    }

    @Inject
    public ListAllCatsBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.cats = this.catService.findAllCats().stream()
                .map(c -> this.modelMapper.map(c, CatListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<CatListViewModel> getCats() {
        return this.cats;
    }

    public void setCats(List<CatListViewModel> cats) {
        this.cats = cats;
    }
}
