package services;


import models.domain.Femijet;
import models.Dto.Femijet.CreateFemijetDto;
import models.Dto.Femijet.UpdateFemijetDto;

import repository.FemijetRepository;

public class FemijetService {
    private FemijetRepository femijetRepository;

    public FemijetService() {
        this.femijetRepository = new FemijetRepository();
    }

    public Femijet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Femijet femija = this.femijetRepository.getById(id);
        if (femija == null) {
            throw new Exception("Femija me ID " + id + " nuk u gjet.");
        }
        return femija;
    }

    public Femijet create(CreateFemijetDto createFemijet) throws Exception {
        if (createFemijet.getEmri() == null || createFemijet.getEmri().isEmpty()) {
            throw new Exception("Emri nuk mund të jetë bosh.");
        }
        if (createFemijet.getMbiemri() == null || createFemijet.getMbiemri().isEmpty()) {
            throw new Exception("Mbiemri nuk mund të jetë bosh.");
        }
        if (createFemijet.getDataLindjes() == null || createFemijet.getDataLindjes().isEmpty()) {
            throw new Exception("Data e lindjes është e detyrueshme.");
        }

        Femijet femija = this.femijetRepository.create(createFemijet);
        if (femija == null) {
            throw new Exception("Femija nuk u krijua.");
        }
        return femija;
    }

    public Femijet update(UpdateFemijetDto updateFemijet) throws Exception {
        Femijet ekzistues = this.getById(updateFemijet.getFemijaID());
        if (ekzistues == null) {
            throw new Exception("Femija për përditësim nuk ekziston.");
        }

        return this.femijetRepository.update(updateFemijet);
    }

    public boolean checkIfFemijaExists(int id) {
        try {
            Femijet femija = this.femijetRepository.getById(id);
            return femija != null;
        } catch (Exception e) {
            return false;
        }
    }

}
