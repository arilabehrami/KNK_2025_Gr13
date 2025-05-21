package services;

import models.Dto.ShenimetShendetsore.CreateShenimetShendetsoreDto;
import models.Dto.ShenimetShendetsore.UpdateShenimetShendetsoreDto;
import models.domain.ShenimetShendetesore;
import repository.ShenimetShendetesoreRepository;

import java.util.ArrayList;

public class ShenimetShendetesoreService {
    private final ShenimetShendetesoreRepository repository;

    public ShenimetShendetesoreService() {
        this.repository = new ShenimetShendetesoreRepository();
    }

    public ShenimetShendetesore create(CreateShenimetShendetsoreDto dto) {
        return repository.create(dto);
    }

    public ShenimetShendetesore update(UpdateShenimetShendetsoreDto dto) {
        return repository.update(dto);
    }

    public ArrayList<ShenimetShendetesore> getAll() {
        return repository.getAll();
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public ShenimetShendetesore getById(int id) {
        return repository.getById(id);
    }
}