package services;

import models.Dto.MenyjaDitore.CreateMenyjaDitoreDto;
import models.Dto.MenyjaDitore.UpdateMenyjaDitoreDto;
import models.domain.MenyjaDitore;
import repository.MenyjaDitoreRepository;

import java.util.List;

public class MenyjaDitoreService {

    private MenyjaDitoreRepository menyjaDitoreRepository;

    public MenyjaDitoreService() {
        this.menyjaDitoreRepository = new MenyjaDitoreRepository();
    }

    public MenyjaDitore createMenu(CreateMenyjaDitoreDto dto) {
        return menyjaDitoreRepository.create(dto);
    }

    public MenyjaDitore updateMenu(UpdateMenyjaDitoreDto dto) {
        return menyjaDitoreRepository.update(dto);
    }

    public MenyjaDitore getMenuById(int id) {
        return menyjaDitoreRepository.getById(id);
    }

    public List<MenyjaDitore> getAllMenus() {
        return menyjaDitoreRepository.getAll();
    }

    public boolean deleteMenu(int id) {
        return menyjaDitoreRepository.delete(id);
    }
}

