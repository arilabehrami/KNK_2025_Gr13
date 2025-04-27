package services;

import models.Dto.Ushqimet.CreateUshqimetDto;
import models.Dto.Ushqimet.UpdateUshqimetDto;
import models.domain.Ushqimet;
import repository.UshqimetRepository;

import java.util.List;

public class UshqimetService {

    private UshqimetRepository ushqimetRepository;

    public UshqimetService() {
        this.ushqimetRepository = new UshqimetRepository();
    }

    public Ushqimet createUshqim(CreateUshqimetDto dto) {
        return ushqimetRepository.create(dto);
    }

    public Ushqimet updateUshqim(UpdateUshqimetDto dto) {
        return ushqimetRepository.update(dto);
    }

    public Ushqimet getUshqimById(int id) {
        return ushqimetRepository.getById(id);
    }

    public List<Ushqimet> getAllUshqimet() {
        return ushqimetRepository.getAll();
    }

    public boolean deleteUshqim(int id) {
        return ushqimetRepository.delete(id);
    }
}
