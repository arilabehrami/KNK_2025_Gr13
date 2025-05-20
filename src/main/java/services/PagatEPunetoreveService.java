package services;

import models.domain.PagatEPunetoreve;
import models.dto.PagatEPunetoreve.CreatePagatEPunetoreveDto;
import models.Dto.PagatEPunetoreve.UpdatePagatEPunetoreveDto;
import repository.PagatEPunetoreveRepository;

public class PagatEPunetoreveService {
    private PagatEPunetoreveRepository pagaRepository;
    public PagatEPunetoreveService(){
        this.pagaRepository = new PagatEPunetoreveRepository();
    }
    public PagatEPunetoreve getByID(int id) throws Exception{
        if(id<=0){
            throw new Exception("ID duhet te jete me e madhe se 0");
        }
        PagatEPunetoreve paga = this.pagaRepository.getById(id);
        if(paga==null){
            throw new Exception("Paga me id: "+id+"nuk u gjet!");
        }
        return paga;
    }

    public PagatEPunetoreve create(CreatePagatEPunetoreveDto createDto) throws Exception{
        if(createDto.getEdukatoriID() <= 0){
            throw new Exception("ID-ja e edukatorit nuk mund te jete bosh!");
        }
        if(createDto.getDataEPageses().isEmpty() || createDto.getDataEPageses() == null){
            throw new Exception("Data nuk mund te jete bosh");
        }
        if(createDto.getShumaPaga() <= 0){
            throw new Exception("Shuma e pages duhet te plotesohet");
        }
        PagatEPunetoreve paga = this.pagaRepository.create(createDto);
        if(paga == null){
            throw new Exception("Paga nuk u regjistrua");
        }
        return paga;
    }

    public PagatEPunetoreve update(UpdatePagatEPunetoreveDto updateDto) throws Exception{
        PagatEPunetoreve pag = this.getByID(updateDto.getPagaID());
        if(pag == null){
            throw new Exception("Paga per perditesim nuk ekziston");
        }
        return this.pagaRepository.update(updateDto);
    }

}
