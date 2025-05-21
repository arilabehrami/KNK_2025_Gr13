package services;

import models.domain.KontaktiEmergjent;
import models.Dto.KontatetEmergjente.CreateKontaktetEmergjenteDto;
import models.Dto.KontatetEmergjente.UpdateKontaktetEmergjenteDto;
import repository.KontaktiEmergjentRepository;

import java.util.List;

public class KontaktiEmergjentService {

    private final KontaktiEmergjentRepository kontaktiEmergjentRepository;

    public KontaktiEmergjentService() {
        this.kontaktiEmergjentRepository = new KontaktiEmergjentRepository();
    }

    public KontaktiEmergjent getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        KontaktiEmergjent kontakti = this.kontaktiEmergjentRepository.getById(id);
        if (kontakti == null) {
            throw new Exception("Kontakti emergjent me ID " + id + " nuk u gjet.");
        }
        return kontakti;
    }

    public KontaktiEmergjent create(CreateKontaktetEmergjenteDto createKontakti) throws Exception {
        if (createKontakti.getEmri() == null || createKontakti.getEmri().isEmpty()) {
            throw new Exception("Emri nuk mund të jetë bosh.");
        }
        if (createKontakti.getMbiemri() == null || createKontakti.getMbiemri().isEmpty()) {
            throw new Exception("Mbiemri nuk mund të jetë bosh.");
        }
        if (createKontakti.getTelefoni() == null || createKontakti.getTelefoni().isEmpty()) {
            throw new Exception("Telefoni nuk mund të jetë bosh.");
        }
        if (createKontakti.getFemijaId() <= 0) {
            throw new Exception("FemijaID është i detyrueshëm dhe duhet të jetë më i madh se 0.");
        }

        KontaktiEmergjent kontakti = this.kontaktiEmergjentRepository.create(createKontakti);
        if (kontakti == null) {
            throw new Exception("Kontakti emergjent nuk u krijua.");
        }
        return kontakti;
    }

    public KontaktiEmergjent update(UpdateKontaktetEmergjenteDto updateKontakti) throws Exception {
        KontaktiEmergjent ekzistues = this.getById(updateKontakti.getKontaktiId());
        if (ekzistues == null) {
            throw new Exception("Kontakti për përditësim nuk ekziston.");
        }

        return this.kontaktiEmergjentRepository.update(updateKontakti);
    }

    public boolean delete(int id) {
        return this.kontaktiEmergjentRepository.delete(id);
    }

    public List<KontaktiEmergjent> getAll() {
        return this.kontaktiEmergjentRepository.getAll();
    }
}