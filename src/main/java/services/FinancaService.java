package services;

import models.Dto.Financat.CreateFinancatDto;
import models.Dto.Financat.UpdateFinancatDto;
import models.domain.Financat;
import repository.FinancatRepository;

import java.util.ArrayList;
import java.util.List;

public class FinancaService {
    private final FinancatRepository financatRepository;

    public FinancaService() {
        this.financatRepository = new FinancatRepository();
    }

    public Financat getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më e madhe se 0.");
        }
        Financat financat = this.financatRepository.getById(id);
        if (financat == null) {
            throw new Exception("Financa me ID " + id + " nuk u gjet!");
        }
        return financat;
    }

    public Financat create(CreateFinancatDto createDto) throws Exception {
        if (createDto.getDate() == null || createDto.getDate().isEmpty()) {
            throw new Exception("Data e regjistrimit të financës është e detyrueshme.");
        }
        if (createDto.getTeArdhura() < 0) {
            throw new Exception("Të ardhurat nuk mund të jenë negative!");
        }
        if (createDto.getShpenzime() < 0) {
            throw new Exception("Shpenzimet nuk mund të jenë negative!");
        }
        Financat financa = this.financatRepository.create(createDto);
        if (financa == null) {
            throw new Exception("Financa nuk u regjistrua!");
        }
        return financa;
    }

    public Financat update(UpdateFinancatDto updateDto) throws Exception {
        Financat financa = this.getById(updateDto.getFinancatID());
        if (financa == null) {
            throw new Exception("Financa për përditësim nuk ekziston.");
        }
        return this.financatRepository.update(updateDto);
    }

    public List<Financat> getAllFinancat() {
        return this.financatRepository.getAll();
    }

    public List<Financat> kerkoFinanca(String input) throws Exception {
        if (input == null || input.isBlank()) {
            throw new Exception("Kërkimi nuk mund të jetë bosh.");
        }

        try {
            int id = Integer.parseInt(input);
            Financat f = this.getById(id);
            List<Financat> result = new ArrayList<>();
            if (f != null) result.add(f);
            return result;
        } catch (NumberFormatException e) {
            return financatRepository.getByPershkrimiOseData(input);
        }
    }

    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më e madhe se 0.");
        }
        boolean success = financatRepository.delete(id);
        if (!success) {
            throw new Exception("Fshirja dështoi. Kontrollo nëse financa ekziston.");
        }
    }
}
