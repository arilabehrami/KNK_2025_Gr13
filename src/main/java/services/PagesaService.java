package services;

import models.domain.Pagesa;
import models.Dto.Pagesa.CreatePagesaDto;
import models.Dto.Pagesa.UpdatePagesaDto;
import repository.PagesaRepository;

import java.util.ArrayList;

public class PagesaService {
    private PagesaRepository pagesaRepository;
    public PagesaService(){
        this.pagesaRepository = new PagesaRepository();
    }
    public Pagesa getById(int id) throws Exception{
        if(id<=0){
            throw new Exception("Id duhet te jete me e madhe se 0");
        }
        Pagesa pagesa = this.pagesaRepository.getById(id);
        if(pagesa == null){
            throw new Exception("Aktiviteti me id " + id +"nuk u gjet!");
        }
        return pagesa;
    }

    public Pagesa create(CreatePagesaDto createPagesaDto) throws Exception{
        if(createPagesaDto.getFemijaId() <= 0){
            throw new Exception("ID-ja e femijes nuk mund te jete bosh");
        }
        if(createPagesaDto.getData()==null){
            throw new Exception("Data e aktivitetit eshte e detyrueshme");
        }
        Pagesa pagesa = this.pagesaRepository.create(createPagesaDto);
        if(pagesa == null){
            throw new Exception("Pagesa nuk u krye!");
        }
        return pagesa;
    }

    public Pagesa update(UpdatePagesaDto updatePagesaDto) throws Exception{
        Pagesa pag = this.getById(updatePagesaDto.getPagesaId());
        if(pag == null){
            throw new Exception("Pagesa per perditesim nuk ekziston!");
        }
        return this.pagesaRepository.update(updatePagesaDto);
    }
    public ArrayList<Pagesa> getAll() {
        return this.pagesaRepository.getAll();
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë më e madhe se 0");
        }
        Pagesa pagesa = this.pagesaRepository.getById(id);
        if (pagesa == null) {
            throw new Exception("Pagesa me ID " + id + " nuk ekziston.");
        }
        return this.pagesaRepository.delete(id);
    }

}
