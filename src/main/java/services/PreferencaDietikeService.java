package services;

import models.domain.PreferencaDietike;
import models.Dto.PreferencaDietike.CreatePreferencaDietikeDto;
import models.Dto.PreferencaDietike.UpdatePreferencaDietikeDto;
import repository.PreferencaDietikeRepository;

public class PreferencaDietikeService {

    private PreferencaDietikeRepository preferencaDietikeRepository;

    public PreferencaDietikeService() {
        this.preferencaDietikeRepository = new PreferencaDietikeRepository();
    }

    public PreferencaDietike getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        PreferencaDietike preferencaDietike = this.preferencaDietikeRepository.getById(id);
        if (preferencaDietike == null) {
            throw new Exception("PreferencaDietike me ID " + id + " nuk u gjet.");
        }
        return preferencaDietike;
    }

    public PreferencaDietike create(CreatePreferencaDietikeDto createDto) throws Exception {
        if (createDto.getFemijaID() <= 0) {
            throw new Exception("FemijaID duhet të jetë më i madh se 0.");
        }
        if (createDto.getLlojiPreferences() == null || createDto.getLlojiPreferences().isEmpty()) {
            throw new Exception("LlojiPreferences nuk mund të jetë bosh.");
        }
        if (createDto.getDetaje() == null || createDto.getDetaje().isEmpty()) {
            throw new Exception("Detaje nuk mund të jetë bosh.");
        }

        PreferencaDietike preferencaDietike = this.preferencaDietikeRepository.create(createDto);
        if (preferencaDietike == null) {
            throw new Exception("PreferencaDietike nuk u krijua.");
        }
        return preferencaDietike;
    }

    public PreferencaDietike update(UpdatePreferencaDietikeDto updateDto) throws Exception {
        PreferencaDietike ekzistues = this.getById(updateDto.getPreferencaID());
        if (ekzistues == null) {
            throw new Exception("PreferencaDietike për përditësim nuk ekziston.");
        }

        return this.preferencaDietikeRepository.update(updateDto);
    }
}
