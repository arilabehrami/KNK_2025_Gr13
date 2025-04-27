package services;

import models.domain.Ushqimet;
import models.Dto.Ushqimet.CreateUshqimetDto;
import models.Dto.Ushqimet.UpdateUshqimetDto;
import repository.UshqimetRepository;

public class UshqimetService {

    private UshqimetRepository ushqimetRepository;

    public UshqimetService() {
        this.ushqimetRepository = new UshqimetRepository();
    }

    public Ushqimet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Ushqimet ushqimi = this.ushqimetRepository.getById(id);
        if (ushqimi == null) {
            throw new Exception("Ushqimi me ID " + id + " nuk u gjet.");
        }
        return ushqimi;
    }

    public Ushqimet create(CreateUshqimetDto createDto) throws Exception {
        if (createDto.getEmriUshqimit() == null || createDto.getEmriUshqimit().isEmpty()) {
            throw new Exception("EmriUshqimit nuk mund të jetë bosh.");
        }
        if (createDto.getKategoria() == null || createDto.getKategoria().isEmpty()) {
            throw new Exception("Kategoria nuk mund të jetë bosh.");
        }

        Ushqimet ushqimi = this.ushqimetRepository.create(createDto);
        if (ushqimi == null) {
            throw new Exception("Ushqimi nuk u krijua.");
        }
        return ushqimi;
    }

    public Ushqimet update(UpdateUshqimetDto updateDto) throws Exception {
        Ushqimet ekzistues = this.getById(updateDto.getUshqimiID());
        if (ekzistues == null) {
            throw new Exception("Ushqimi për përditësim nuk ekziston.");
        }

        return this.ushqimetRepository.update(updateDto);
    }
}
