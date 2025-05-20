package services;

import models.Dto.Orari.CreateOrariDto;
import models.Dto.Orari.UpdateOrariDto;
import models.domain.Orari;
import repository.OrariRepository;

import java.util.List;

public class OrariService {

    private final OrariRepository repository;
    OrariService service;

    // Konstruktor pa parametra – krijon repository me lidhje të vetme DB
    public OrariService() {
        this.repository = new OrariRepository();
    }

    // Konstruktor për injektim të repository
    public OrariService(OrariRepository repository) {
        this.repository = repository;
    }

    public List<Orari> getAllOrari() {
        return repository.getAll();
    }

    public Orari addOrari(CreateOrariDto dto) {
        return repository.create(dto);
    }

    public Orari updateOrari(UpdateOrariDto dto) {
        return repository.update(dto);
    }

    public boolean deleteOrari(int id) {
        return repository.delete(id);
    }

    public Orari getOrariById(int id) {
        return repository.getById(id);
    }
}