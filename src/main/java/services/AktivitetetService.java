package services;

import models.domain.Aktivitetet;
import models.Dto.Aktivitetet.CreateAktivitetetDto;
import models.Dto.Aktivitetet.UpdateAktivitetetDto;
import repository.AktivitetetRepository;

public class AktivitetetService {
    private AktivitetetRepository aktivitetetRepository;

    public AktivitetetService() {
        this.aktivitetetRepository = new AktivitetetRepository();
    }

    public Aktivitetet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Aktivitetet aktiviteti = this.aktivitetetRepository.getById(id);
        if (aktiviteti == null) {
            throw new Exception("Aktiviteti me ID " + id + " nuk u gjet.");
        }
        return aktiviteti;
    }

    public Aktivitetet create(CreateAktivitetetDto createAktivitet) throws Exception {
        if (createAktivitet.getEmriAktivitetit() == null || createAktivitet.getEmriAktivitetit().isEmpty()) {
            throw new Exception("Emri i aktivitetit nuk mund të jetë bosh.");
        }
        if (createAktivitet.getData() == null || createAktivitet.getData().isEmpty()) {
            throw new Exception("Data e aktivitetit është e detyrueshme.");
        }

        Aktivitetet aktiviteti = this.aktivitetetRepository.create(createAktivitet);
        if (aktiviteti == null) {
            throw new Exception("Aktiviteti nuk u krijua.");
        }
        return aktiviteti;
    }

    public Aktivitetet update(UpdateAktivitetetDto updateAktivitet) throws Exception {
        Aktivitetet ekzistues = this.getById(updateAktivitet.getAktivitetiId());
        if (ekzistues == null) {
            throw new Exception("Aktiviteti për përditësim nuk ekziston.");
        }

        return this.aktivitetetRepository.update(updateAktivitet);
    }
}
