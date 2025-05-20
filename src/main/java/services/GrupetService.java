package services;

import models.domain.Grupet;
import models.Dto.Grupet.CreateGrupetDto;
import models.Dto.Grupet.UpdateGrupetDto;
import repository.GrupetRepository;

import java.util.List;

public class GrupetService {
    private final GrupetRepository grupetRepository;

    public GrupetService() {
        this.grupetRepository = new GrupetRepository();
    }

    // Merr grupin sipas ID-së, me validim
    public Grupet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Grupet grupi = grupetRepository.getById(id);
        if (grupi == null) {
            throw new Exception("Grupi me ID " + id + " nuk u gjet.");
        }
        return grupi;
    }

    // Merr listën e të gjitha grupeve
    public List<Grupet> getAll() throws Exception {
        List<Grupet> grupet = grupetRepository.getAll();
        if (grupet == null || grupet.isEmpty()) {
            throw new Exception("Nuk u gjet asnjë grup.");
        }
        return grupet;
    }

    // Fshin grupin me ID të dhënë
    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        boolean deleted = grupetRepository.delete(id);
        if (!deleted) {
            throw new Exception("Fshirja nuk u krye. Grupi mund të mos ekzistojë.");
        }
    }

    // Validimi i të dhënave për krijim
    private void validateDto(CreateGrupetDto dto) throws Exception {
        if (dto.getEmriGrupit() == null || dto.getEmriGrupit().trim().isEmpty()) {
            throw new Exception("Emri i grupit nuk mund të jetë bosh.");
        }
        if (dto.getMoshaMin() < 0) {
            throw new Exception("Mosha minimale nuk mund të jetë negative.");
        }
        if (dto.getMoshaMax() < dto.getMoshaMin()) {
            throw new Exception("Mosha maksimale nuk mund të jetë më e vogël se mosha minimale.");
        }
        if (dto.getEdukatoriId() <= 0) {
            throw new Exception("ID e edukatorit duhet të jetë më e madhe se 0.");
        }
    }

    // Validimi i të dhënave për përditësim
    private void validateDto(UpdateGrupetDto dto) throws Exception {
        if (dto.getGrupiId() <= 0) {
            throw new Exception("ID e grupit duhet të jetë më e madhe se 0.");
        }
        if (dto.getEmriGrupit() == null || dto.getEmriGrupit().trim().isEmpty()) {
            throw new Exception("Emri i grupit nuk mund të jetë bosh.");
        }
        if (dto.getMoshaMin() < 0) {
            throw new Exception("Mosha minimale nuk mund të jetë negative.");
        }
        if (dto.getMoshaMax() < dto.getMoshaMin()) {
            throw new Exception("Mosha maksimale nuk mund të jetë më e vogël se mosha minimale.");
        }
        if (dto.getEdukatoriId() <= 0) {
            throw new Exception("ID e edukatorit duhet të jetë më e madhe se 0.");
        }
    }

    // Krijon një grup të ri
    public Grupet create(CreateGrupetDto dto) throws Exception {
        validateDto(dto);
        Grupet grupi = grupetRepository.create(dto);
        if (grupi == null) {
            throw new Exception("Grupi nuk u krijua.");
        }
        return grupi;
    }

    // Përditëson grupin ekzistues
    public Grupet update(UpdateGrupetDto dto) throws Exception {
        validateDto(dto);
        Grupet ekzistues = getById(dto.getGrupiId());
        if (ekzistues == null) {
            throw new Exception("Grupi për përditësim nuk ekziston.");
        }
        return grupetRepository.update(dto);
    }
}
