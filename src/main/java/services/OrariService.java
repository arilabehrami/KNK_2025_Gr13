package services;

import models.domain.Orari;
import models.Dto.Orari.CreateOrariDto;
import models.Dto.Orari.UpdateOrariDto;
import repository.OrariRepository;

import java.util.List;

public class OrariService {
    private OrariRepository orariRepository;
    public List<Orari> getAll() {
        return this.orariRepository.getAll();
    }


    public OrariService() {
        this.orariRepository = new OrariRepository();
    }

    public Orari getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Orari orari = this.orariRepository.getById(id);
        if (orari == null) {
            throw new Exception("Orari me ID " + id + " nuk u gjet.");
        }
        return orari;
    }

    public Orari create(CreateOrariDto createOrariDto) throws Exception {
        if (createOrariDto.getDita() == null || createOrariDto.getDita().isEmpty()) {
            throw new Exception("Dita e orarit nuk mund të jetë bosh.");
        }
        if (createOrariDto.getOraHyrjes() == null || createOrariDto.getOraHyrjes().isEmpty()) {
            throw new Exception("Ora e hyrjes është e detyrueshme.");
        }
        if (createOrariDto.getOraDaljes() == null || createOrariDto.getOraDaljes().isEmpty()) {
            throw new Exception("Ora e daljes është e detyrueshme.");
        }

        Orari orari = this.orariRepository.create(createOrariDto);
        if (orari == null) {
            throw new Exception("Orari nuk u krijua.");
        }
        return orari;
    }

    public Orari update(UpdateOrariDto updateOrariDto) throws Exception {
        Orari ekzistues = this.getById(updateOrariDto.getOrariId());
        if (ekzistues == null) {
            throw new Exception("Orari për përditësim nuk ekziston.");
        }

        return this.orariRepository.update(updateOrariDto);
    }
}
