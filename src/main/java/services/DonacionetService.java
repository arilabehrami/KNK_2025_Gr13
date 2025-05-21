package services;

import models.domain.Donacionet;
import models.Dto.Donacionet.CreateDonacionetDto;
import models.Dto.Donacionet.UpdateDonacionetDto;
import repository.DonacionetRepository;

import java.util.ArrayList;

public class DonacionetService {
    private DonacionetRepository donacionetRepository;

    public DonacionetService() {
        this.donacionetRepository = new DonacionetRepository();
    }


    public ArrayList<Donacionet> getAll() {
        return donacionetRepository.getAll();
    }


    public Donacionet getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më i madh se 0.");
        }
        Donacionet donacioni = this.donacionetRepository.getById(id);
        if (donacioni == null) {
            throw new Exception("Donacioni me ID " + id + " nuk u gjet.");
        }
        return donacioni;
    }


    public Donacionet create(CreateDonacionetDto createDonacionet) throws Exception {
        if (createDonacionet.getEmriOrganizates() == null || createDonacionet.getEmriOrganizates().isEmpty()) {
            throw new Exception("Emri i organizatës nuk mund të jetë bosh.");
        }
        if (createDonacionet.getLlojiDonatori() == null || createDonacionet.getLlojiDonatori().isEmpty()) {
            throw new Exception("Lloji i donatorit nuk mund të jetë bosh.");
        }
        if (createDonacionet.getShuma() == null || createDonacionet.getShuma() <= 0) {
            throw new Exception("Shuma e donacionit duhet të jetë më e madhe se 0.");
        }

        Donacionet donacioni = this.donacionetRepository.create(createDonacionet);
        if (donacioni == null) {
            throw new Exception("Donacioni nuk u krijua.");
        }
        return donacioni;
    }


    public Donacionet update(UpdateDonacionetDto updateDonacionet) throws Exception {
        Donacionet ekzistues = this.getById(updateDonacionet.getDonacioniID());
        if (ekzistues == null) {
            throw new Exception("Donacioni për përditësim nuk ekziston.");
        }

        return this.donacionetRepository.update(updateDonacionet);
    }


    public boolean delete(int id) throws Exception {
        boolean success = donacionetRepository.delete(id);
        if (!success) {
            throw new Exception("Fshirja e donacionit me ID " + id + " dështoi.");
        }
        return true;
    }
}
