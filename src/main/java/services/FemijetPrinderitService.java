package services;

import models.domain.FemijetPrinderit;
import models.Dto.FemijetPrinderit.CreateFemijetPrinderitDto;
import models.Dto.FemijetPrinderit.UpdateFemijetPrinderitDto;
import repository.FemijetPrinderitRepository;

public class FemijetPrinderitService {

    private final FemijetPrinderitRepository repository;

    public FemijetPrinderitService() {
        this.repository = new FemijetPrinderitRepository();
    }

    public FemijetPrinderit getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        FemijetPrinderit lidhja = repository.getById(id);
        if (lidhja == null) {
            throw new Exception("Lidhja me ID " + id + " nuk u gjet.");
        }
        return lidhja;
    }

    public FemijetPrinderit create(CreateFemijetPrinderitDto dto) throws Exception {
        if (dto.getFemijaID() <= 0 || dto.getPrindiID() <= 0) {
            throw new Exception("ID-të duhet të jenë më të mëdha se 0.");
        }

        FemijetPrinderit lidhja = repository.create(dto);
        if (lidhja == null) {
            throw new Exception("Lidhja nuk u krijua.");
        }
        return lidhja;
    }

    public FemijetPrinderit update(UpdateFemijetPrinderitDto dto) throws Exception {
        FemijetPrinderit ekzistues = this.getById(dto.getId());
        if (ekzistues == null) {
            throw new Exception("Lidhja për përditësim nuk ekziston.");
        }

        return repository.update(dto);
    }
}
