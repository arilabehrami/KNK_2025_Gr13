package services;

import models.domain.Pagesa;
import models.dto.Pagesa.CreatePagesaDto;
import repository.PagesaRepository;

public class PagesaService {
    private PagesaRepository pagesaRepository;
    public PagesaService(){
        this.pagesaRepository = new PagesaRepository();
    }
    public Pagesa getById(int id) throws Exception{
        if(id>=0){
            throw new Exception("Id duhet te jete me e madhe se 0");
        }
        Pagesa pagesa = this.pagesaRepository.getById(id);
        if(pagesa == null){
            throw new Exception("Aktiviteti me id " + id +"nuk u gjet!");
        }
        return pagesa;
    }

    public Pagesa create(CreatePagesaDto createPagesaDto) throws Exception{
        if(createPagesaDto.getFemijaId() == null()){
            throw new Exception("ID-ja e femijes nuk mund te jete bosh");
        }
        if(createPagesaDto.getData()==null || createPagesaDto.getData().isEmpty()){
            throw new Exception("Data e aktivitetit eshte e detyrueshme");
        }
        Pagesa pagesa = this.pagesaRepository.create(createPagesaDto);
        if(pagesa == null){
            throw new Exception("Pagesa nuk u krye!");
        }
        return pagesa;
    }

    
}
