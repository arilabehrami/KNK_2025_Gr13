package services;

import models.domain.Prinderit;
import models.Dto.Prinderit.CreatePrinderitDto;
import models.Dto.Prinderit.UpdatePrinderitDto;
import repository.PrinderitRepository;

import java.util.List;

public class PrinderitService {

    private final PrinderitRepository prinderitRepository;

    public PrinderitService() {
        this.prinderitRepository = new PrinderitRepository();
    }

    public Prinderit getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Prinderit prindi = prinderitRepository.getById(id);
        if (prindi == null) {
            throw new Exception("Prindi me ID " + id + " nuk u gjet.");
        }
        return prindi;
    }

    public Prinderit create(CreatePrinderitDto dto) throws Exception {
        if (dto.getEmri().isEmpty() || dto.getMbiemri().isEmpty() || dto.getTelefoni().isEmpty() || dto.getLlojiLidhjes() == null) {
            throw new Exception("Të gjitha fushat kryesore duhet të plotësohen.");
        }

        Prinderit prindi = prinderitRepository.create(dto);
        if (prindi == null) {
            throw new Exception("Prindi nuk u krijua.");
        }
        return prindi;
    }

    public Prinderit update(UpdatePrinderitDto dto) throws Exception {
        Prinderit ekzistues = this.getById(dto.getPrindiID());
        if (ekzistues == null) {
            throw new Exception("Prindi për përditësim nuk ekziston.");
        }

        return prinderitRepository.update(dto);
    }
    public List<Prinderit> getAll() throws Exception {
        return prinderitRepository.getAll();
    }
    public boolean delete(int id) {
        if (id <= 0) {
            return false;
        }
        return prinderitRepository.delete(id);
    }



}
