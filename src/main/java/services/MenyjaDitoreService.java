package services;

import models.domain.MenyjaDitore;
import models.Dto.MenyjaDitore.CreateMenyjaDitoreDto;
import models.Dto.MenyjaDitore.UpdateMenyjaDitoreDto;
import repository.MenyjaDitoreRepository;

public class MenyjaDitoreService {

    private MenyjaDitoreRepository menyjaDitoreRepository;

    public MenyjaDitoreService() {
        this.menyjaDitoreRepository = new MenyjaDitoreRepository();
    }

    // Metoda për të marrë një MenyjaDitore nga ID
    public MenyjaDitore getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        MenyjaDitore menyjaDitore = this.menyjaDitoreRepository.getById(id);
        if (menyjaDitore == null) {
            throw new Exception("MenyjaDitore me ID " + id + " nuk u gjet.");
        }
        return menyjaDitore;
    }

    // Metoda për krijimin e një MenyjaDitore
    public MenyjaDitore create(CreateMenyjaDitoreDto createDto) throws Exception {
        // Validimi i të dhënave
        if (createDto.getDita() == null || createDto.getDita().isEmpty()) {
            throw new Exception("Dita nuk mund të jetë bosh.");
        }
        if (createDto.getGrupiID() <= 0) {
            throw new Exception("GrupiID duhet të jetë më i madh se 0.");
        }
        if (createDto.getUshqimiID() <= 0) {
            throw new Exception("UshqimiID duhet të jetë më i madh se 0.");
        }

        // Krijimi i MenyjaDitore
        MenyjaDitore menyjaDitore = this.menyjaDitoreRepository.create(createDto);
        if (menyjaDitore == null) {
            throw new Exception("MenyjaDitore nuk u krijua.");
        }
        return menyjaDitore;
    }

    // Metoda për përditësimin e një MenyjaDitore
    public MenyjaDitore update(UpdateMenyjaDitoreDto updateDto) throws Exception {
        // Verifikim se ekziston MenyjaDitore për përditësim
        MenyjaDitore ekzistues = this.getById(updateDto.getMenuID());
        if (ekzistues == null) {
            throw new Exception("MenyjaDitore për përditësim nuk ekziston.");
        }

        // Përshtat update në repository
        return this.menyjaDitoreRepository.update(updateDto);
    }
}
