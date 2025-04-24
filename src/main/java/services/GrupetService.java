package services;


import models.domain.Grupet;
import models.dto.Grupet.CreateGrupetDto;
import models.dto.Grupet.UpdateGrupetDto;
import repository.GrupetRepository;

public class GrupetService {
    private GrupetRepository grupetRepository;

    public GrupetService() {
        this.grupetRepository = new GrupetRepository();
    }

    public Grupet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Grupet grupi = this.grupetRepository.getById(id);
        if (grupi == null) {
            throw new Exception("Grupi me ID " + id + " nuk u gjet.");
        }
        return grupi;
    }

    public Grupet create(CreateGrupetDto createGrupet) throws Exception {
        if (createGrupet.getEmriGrupit() == null || createGrupet.getEmriGrupit().isEmpty()) {
            throw new Exception("Emri i grupit nuk mund të jetë bosh.");
        }
        if (createGrupet.getMoshaMin() < 0 || createGrupet.getMoshaMax() < createGrupet.getMoshaMin()) {
            throw new Exception("Mosha është e pavlefshme.");
        }

        Grupet grupi = this.grupetRepository.create(createGrupet);
        if (grupi == null) {
            throw new Exception("Grupi nuk u krijua.");
        }
        return grupi;
    }

    public Grupet update(UpdateGrupetDto updateGrupet) throws Exception {
        Grupet ekzistues = this.getById(updateGrupet.getGrupiId());
        if (ekzistues == null) {
            throw new Exception("Grupi për përditësim nuk ekziston.");
        }

        return this.grupetRepository.update(updateGrupet);
    }
}
