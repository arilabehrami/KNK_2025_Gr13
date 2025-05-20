package services;

import models.domain.MenyjaDitore;
import models.Dto.MenyjaDitore.CreateMenyjaDitoreDto;
import models.Dto.MenyjaDitore.UpdateMenyjaDitoreDto;
import repository.MenyjaDitoreRepository;

import java.util.List;

public class MenyjaDitoreService {

    private final MenyjaDitoreRepository menyjaDitoreRepository;

    public MenyjaDitoreService() {
        this.menyjaDitoreRepository = new MenyjaDitoreRepository();
    }

    public MenyjaDitore getById(int id) throws Exception {
        if (id <= 0) throw new Exception("ID duhet të jetë më i madh se 0.");
        MenyjaDitore menyja = menyjaDitoreRepository.getById(id);
        if (menyja == null) throw new Exception("Nuk u gjet menyja me ID " + id);
        return menyja;
    }

    public MenyjaDitore create(CreateMenyjaDitoreDto dto) throws Exception {
        if (dto.getDita() == null || dto.getDita().isEmpty())
            throw new Exception("Dita nuk mund të jetë bosh.");
        if (dto.getGrupiID() <= 0)
            throw new Exception("GrupiID duhet të jetë pozitiv.");
        if (dto.getUshqimiID() <= 0)
            throw new Exception("UshqimiID duhet të jetë pozitiv.");

        MenyjaDitore menyja = menyjaDitoreRepository.create(dto);
        if (menyja == null) throw new Exception("Shtimi dështoi.");
        return menyja;
    }

    public MenyjaDitore update(UpdateMenyjaDitoreDto dto) throws Exception {
        MenyjaDitore ekzistues = this.getById(dto.getMenuID());
        if (ekzistues == null)
            throw new Exception("Menyja për përditësim nuk ekziston.");
        return menyjaDitoreRepository.update(dto);
    }

    public List<MenyjaDitore> getAll() {
        return menyjaDitoreRepository.getAll();
    }

    public void delete(int id) throws Exception {
        if (id <= 0) throw new Exception("ID duhet të jetë më i madh se 0.");
        boolean success = menyjaDitoreRepository.delete(id);
        if (!success) throw new Exception("Fshirja dështoi. ID jo ekzistues.");
    }
}
