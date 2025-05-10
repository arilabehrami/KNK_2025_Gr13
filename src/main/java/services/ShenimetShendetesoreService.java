package services;

import models.domain.ShenimetShendetesore;
import models.Dto.ShenimetShendetsore.CreateShenimetShendetsoreDto;
import models.Dto.ShenimetShendetsore.UpdateShenimetShendetsoreDto;
import repository.ShenimetShendetesoreRepository;

public class ShenimetShendetesoreService {

    private ShenimetShendetesoreRepository shenimiShendetesoreRepository;

    public ShenimetShendetesoreService() {
        this.shenimiShendetesoreRepository = new ShenimetShendetesoreRepository();
    }

    public ShenimetShendetesore getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        ShenimetShendetesore shenimi = this.shenimiShendetesoreRepository.getById(id);
        if (shenimi == null) {
            throw new Exception("Shenimi shëndetësor me ID " + id + " nuk u gjet.");
        }
        return shenimi;
    }

    public ShenimetShendetesore create(CreateShenimetShendetsoreDto createShenimi) throws Exception {
        if (createShenimi.getData() == null || createShenimi.getData().isEmpty()) {
            throw new Exception("Data është e detyrueshme.");
        }
        if (createShenimi.getPershkrimi() == null || createShenimi.getPershkrimi().isEmpty()) {
            throw new Exception("Përshkrimi nuk mund të jetë bosh.");
        }
        if (createShenimi.getFemijaId() <= 0) {
            throw new Exception("FemijaID është i detyrueshëm dhe duhet të jetë më i madh se 0.");
        }

        ShenimetShendetesore shenimi = this.shenimiShendetesoreRepository.create(createShenimi);
        if (shenimi == null) {
            throw new Exception("Shenimi shëndetësor nuk u krijua.");
        }
        return shenimi;
    }

    public ShenimetShendetesore update(UpdateShenimetShendetsoreDto updateShenimi) throws Exception {
        ShenimetShendetesore ekzistues = this.getById(updateShenimi.getShenimiId());
        if (ekzistues == null) {
            throw new Exception("Shenimi për përditësim nuk ekziston.");
        }

        return this.shenimiShendetesoreRepository.update(updateShenimi);
    }
}

