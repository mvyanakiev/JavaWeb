package fdmc2app.service;

import fdmc2app.domain.entities.Cat;
import fdmc2app.domain.models.service.CatServiceModel;
import fdmc2app.repository.CatRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService{

    private final CatRepository catRepository;
    private final ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveCat(CatServiceModel catServiceModel) {

        try {
            this.catRepository.save(this.modelMapper.map(catServiceModel, Cat.class));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<CatServiceModel> findAllCats() {
        return this.catRepository.findAll().stream()
                .map(e -> this.modelMapper.map(e, CatServiceModel.class))
                .collect(Collectors.toList());
    }
}