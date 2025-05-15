package services;

import models.Dto.Financat.CreateFinancatDto;
import models.Dto.Financat.UpdateFinancatDto;
import models.domain.Financat;
import repository.FinancatRepository;

public class FinancaService {
    private FinancatRepository financatRepository;

    public FinancaService() {
        this.financatRepository = new FinancatRepository();
    }

    public Financat getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet te jete me e madhe se 0");
        }
        Financat financat = this.financatRepository.getById(id);
        if (financat == null) {
            throw new Exception("Financa me ID " + id + "nuk u gjet!");
        }
        return financat;
    }

    public Financat create(CreateFinancatDto createDto) throws Exception {
        if (createDto.getDate() == null || createDto.getDate().isEmpty()) {
            throw new Exception("Data e regjistrimit te finances eshte e detyrueshme");
        }
        if (createDto.getTeArdhura() < 0) {
            throw new Exception("Te ardhurat nuk mund te jene negative!");
        }
        if (createDto.getShpenzime() < 0) {
            throw new Exception("Shpenzimet nuk mund te jene negative!");
        }
        Financat financa = this.financatRepository.create(createDto);
        if(financa == null){
            throw new Exception("Financa nuk u regjistrua!");
        }
        return financa;
    }

    public Financat update(UpdateFinancatDto updateDto) throws Exception{
        Financat financa = this.getById(updateDto.getFinancatID());
        if(financa == null){
            throw new Exception("Financa per perditesim nuk ekziston");
        }
        return this.financatRepository.update(updateDto);
    }
}
