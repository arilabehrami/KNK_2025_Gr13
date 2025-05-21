package services;

import models.domain.Sugjerimet;
import models.Dto.Sugjerimet.CreateSugjerimetDto;
import models.Dto.Sugjerimet.UpdateSugjerimetDto;
import repository.SugjerimetRepository;

import java.util.List;

public class SugjerimetService {

    private SugjerimetRepository sugjerimetRepository;

    public SugjerimetService() {
        this.sugjerimetRepository = new SugjerimetRepository();
    }

    public Sugjerimet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Sugjerimet sugjerimi = this.sugjerimetRepository.getById(id);
        if (sugjerimi == null) {
            throw new Exception("Sugjerimi me ID " + id + " nuk u gjet.");
        }
        return sugjerimi;
    }

    public Sugjerimet create(CreateSugjerimetDto dto) throws Exception {
        if (dto.getEmriSugjeruesit() == null || dto.getEmriSugjeruesit().isEmpty()) {
            throw new Exception("Emri i sugjeruesit është i detyrueshëm.");
        }
        if (dto.getRoli() == null || dto.getRoli().isEmpty()) {
            throw new Exception("Roli është i detyrueshëm.");
        }
        if (dto.getData() == null) {
            throw new Exception("Data është e detyrueshme.");
        }
        if (dto.getPershkrimi() == null || dto.getPershkrimi().isEmpty()) {
            throw new Exception("Përshkrimi nuk mund të jetë bosh.");
        }

        Sugjerimet sugjerimi = this.sugjerimetRepository.create(dto);
        if (sugjerimi == null) {
            throw new Exception("Sugjerimi nuk u krijua.");
        }
        return sugjerimi;
    }

    public Sugjerimet update(UpdateSugjerimetDto dto) throws Exception {
        Sugjerimet ekzistues = this.getById(dto.getSugjerimiId());
        if (ekzistues == null) {
            throw new Exception("Sugjerimi për përditësim nuk ekziston.");
        }

        return this.sugjerimetRepository.update(dto);
    }

    public List<Sugjerimet> getAll() {
        return sugjerimetRepository.getAll();
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë pozitiv.");
        }
        return sugjerimetRepository.delete(id);
    }

}