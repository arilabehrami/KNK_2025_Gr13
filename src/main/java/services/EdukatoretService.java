package services;

import models.domain.Edukatoret;
import models.Dto.Edukatoret.CreateEdukatoretDto;
import models.Dto.Edukatoret.UpdateEdukatoretDto;
import repository.EdukatoretRepository;

public class EdukatoretService {

    private final EdukatoretRepository repository;

    public EdukatoretService() {
        this.repository = new EdukatoretRepository();
    }

    public Edukatoret getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Edukatoret edukatori = repository.getById(id);
        if (edukatori == null) {
            throw new Exception("Edukatori me ID " + id + " nuk u gjet.");
        }
        return edukatori;
    }

    public Edukatoret create(CreateEdukatoretDto dto) throws Exception {
        if (dto.getEmri().isEmpty() || dto.getMbiemri().isEmpty()) {
            throw new Exception("Emri dhe Mbiemri janë të detyrueshme.");
        }

        Edukatoret edukatori = repository.create(dto);
        if (edukatori == null) {
            throw new Exception("Edukatori nuk u krijua.");
        }
        return edukatori;
    }

    public Edukatoret update(UpdateEdukatoretDto dto) throws Exception {
        Edukatoret ekzistues = this.getById(dto.getEdukatoriID());
        if (ekzistues == null) {
            throw new Exception("Edukatori për përditësim nuk ekziston.");
        }

        return repository.update(dto);
    }
}
