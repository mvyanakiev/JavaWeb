package fdmc2app.service;

import fdmc2app.domain.models.service.CatServiceModel;

import java.util.List;

public interface CatService {

    boolean saveCat(CatServiceModel catServiceModel);

    List<CatServiceModel> findAllCats();




}
