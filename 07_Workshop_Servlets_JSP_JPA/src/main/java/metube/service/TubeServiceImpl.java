package metube.service;

import metube.domain.entities.Tube;
import metube.domain.model.service.TubeServiceModel;
import metube.repository.TubeRepository;
import metube.util.ModelMapper;

import javax.inject.Inject;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, UserService userService, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean uploadTube(TubeServiceModel tubeServiceModel) {
        tubeServiceModel.setUploader(this.userService.findUserByUsername(tubeServiceModel.getUploader().getUsername()));

        try {
            this.tubeRepository.save(this.modelMapper.map(tubeServiceModel, Tube.class));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
