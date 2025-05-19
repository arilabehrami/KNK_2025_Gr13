package services;

import models.domain.Edukatoret;
import models.Dto.Edukatoret.CreateEdukatoretDto;
import models.Dto.Edukatoret.UpdateEdukatoretDto;
import repository.EdukatoretRepository;

import java.util.List;

public class EdukatoretService {

    private final EdukatoretRepository repository;

    public EdukatoretService() {
        this.repository = new EdukatoretRepository();
    }

    public Edukatoret getById(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID duhet të jetë numër pozitiv.");
        }
        Edukatoret edukatori = repository.getById((int) id);
        if (edukatori == null) {
            throw new Exception("Edukatori me ID " + id + " nuk u gjet.");
        }
        return edukatori;
    }

    public Edukatoret create(CreateEdukatoretDto dto) throws Exception {
        if (dto.getEmri() == null || dto.getEmri().isEmpty() || dto.getMbiemri() == null || dto.getMbiemri().isEmpty()) {
            throw new Exception("Emri dhe Mbiemri janë të detyrueshme.");
        }
        // Në create, ID nuk vendoset, baza e krijon automatikisht
        Edukatoret edukatori = repository.create(dto);
        if (edukatori == null) {
            throw new Exception("Edukatori nuk u krijua.");
        }
        return edukatori;
    }

    public Edukatoret update(UpdateEdukatoretDto dto) throws Exception {
        if (dto.getEdukatoriID() <= 0) {
            throw new IllegalArgumentException("ID për përditësim duhet të jetë numër pozitiv.");
        }

        Edukatoret ekzistues = this.getById(dto.getEdukatoriID());
        if (ekzistues == null) {
            throw new Exception("Edukatori për përditësim nuk ekziston.");
        }
        return repository.update(dto);
    }

    public List<Edukatoret> getAll() throws Exception {
        List<Edukatoret> lista = repository.getAll();
        if (lista == null || lista.isEmpty()) {
            throw new Exception("Nuk u gjetën edukatorë në bazë të të dhënave.");
        }
        return lista;
    }

    public void delete(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID për fshirje duhet të jetë numër pozitiv.");
        }
        Edukatoret ekzistues = this.getById(id);
        if (ekzistues == null) {
            throw new Exception("Edukatori për fshirje nuk ekziston.");
        }
        repository.delete((int) id);
    }
}
