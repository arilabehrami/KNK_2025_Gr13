package services;

import models.domain.Aktivitetet;
import models.Dto.Aktivitetet.CreateAktivitetetDto;
import models.Dto.Aktivitetet.UpdateAktivitetetDto;
import repository.AktivitetetRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AktivitetetService {

    private AktivitetetRepository repository;

    public AktivitetetService(AktivitetetRepository repository) {
        this.repository = repository;
    }

    public List<Aktivitetet> getAllAktivitetet() throws SQLException {
        return repository.findAll();
    }

    public void addAktivitet(CreateAktivitetetDto dto) throws SQLException {
        Aktivitetet a = new Aktivitetet(
                dto.getEmriAktivitetit(),
                dto.getPershkrimi(),
                LocalDate.parse(dto.getData()),
                dto.getGrupiID()
        );
        repository.add(a);
    }

    public void updateAktivitet(UpdateAktivitetetDto dto) throws SQLException {
        Aktivitetet a = new Aktivitetet(
                dto.getAktivitetiID(),
                dto.getEmriAktivitetit(),
                dto.getPershkrimi(),
                LocalDate.parse(dto.getData()),
                dto.getGrupiID()
        );
        repository.update(a);
    }

    public void deleteAktivitet(int id) throws SQLException {
        repository.delete(id);
    }
}
