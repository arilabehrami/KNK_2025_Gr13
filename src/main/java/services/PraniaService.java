package services;

import models.domain.Prania;
import models.Dto.Prania.CreatePraniaDto;
import models.Dto.Prania.UpdatePraniaDto;
import repository.PraniaRepository;

public class PraniaService {
    private PraniaRepository praniaRepository;

    public PraniaService() {
        this.praniaRepository = new PraniaRepository();
    }

    public Prania getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Prania prania = this.praniaRepository.getById(id);
        if (prania == null) {
            throw new Exception("Prania me ID " + id + " nuk u gjet.");
        }
        return prania;
    }

    public Prania create(CreatePraniaDto createPrania) throws Exception {
        if (createPrania.getData() == null || createPrania.getData().isEmpty()) {
            throw new Exception("Data nuk mund të jetë bosh.");
        }
        if (createPrania.getStatusi() == null || createPrania.getStatusi().isEmpty()) {
            throw new Exception("Statusi është i detyrueshëm.");
        }

        Prania prania = this.praniaRepository.create(createPrania);
        if (prania == null) {
            throw new Exception("Prania nuk u krijua.");
        }
        return prania;
    }

    public Prania update(UpdatePraniaDto updatePrania) throws Exception {
        Prania ekzistuese = this.getById(updatePrania.getPraniaId());
        if (ekzistuese == null) {
            throw new Exception("Prania për përditësim nuk ekziston.");
        }

        return this.praniaRepository.update(updatePrania);
    }
}
