package services;

import models.Dto.PreferencaDietike.CreatePreferencaDietikeDto;
import models.Dto.PreferencaDietike.UpdatePreferencaDietikeDto;
import models.domain.PreferencaDietike;
import repository.PreferencaDietikeRepository;

import java.util.List;

public class PreferencaDietikeService {

    private PreferencaDietikeRepository preferencaDietikeRepository;

    public PreferencaDietikeService() {
        this.preferencaDietikeRepository = new PreferencaDietikeRepository();
    }

    public PreferencaDietike createPreferenca(CreatePreferencaDietikeDto dto) {
        return preferencaDietikeRepository.create(dto);
    }

    public PreferencaDietike updatePreferenca(UpdatePreferencaDietikeDto dto) {
        return preferencaDietikeRepository.update(dto);
    }

    public PreferencaDietike getPreferencaById(int id) {
        return preferencaDietikeRepository.getById(id);
    }

    public List<PreferencaDietike> getAllPreferencat() {
        return preferencaDietikeRepository.getAll();
    }

    public boolean deletePreferenca(int id) {
        return preferencaDietikeRepository.delete(id);
    }
}
