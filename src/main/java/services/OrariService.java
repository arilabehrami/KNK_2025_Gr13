package services;

import models.Dto.Orari.CreateOrariDto;
import models.Dto.Orari.UpdateOrariDto;
import models.domain.Orari;
import repository.OrariRepository;
import Database.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrariService {

    private final OrariRepository repository;

    // Konstruktor pa parametra që krijon vetë repo-n
    public OrariService() {
        Connection connection = DBConnection.getConnection();
        this.repository = new OrariRepository(connection);
    }

    // Konstruktor me parametër për injektim të repo-së nga jashtë
    public OrariService(OrariRepository repository) {
        this.repository = repository;
    }

    public List<Orari> getAllOrari() throws SQLException {
        return repository.findAll();
    }

    public void addOrari(CreateOrariDto dto) throws SQLException {
        Orari orari = new Orari();
        orari.setFemijaID(dto.getFemijaID());
        orari.setDita(dto.getDita());
        orari.setOraHyrjes(dto.getOraHyrjesAsLocalTime());
        orari.setOraDaljes(dto.getOraDaljesAsLocalTime());
        repository.save(orari);
    }

    public void updateOrari(UpdateOrariDto dto) throws SQLException {
        Orari orari = new Orari();
        orari.setOrariID(dto.getOrariID());
        orari.setFemijaID(dto.getFemijaID());
        orari.setDita(dto.getDita());
        orari.setOraHyrjes(dto.getOraHyrjesAsLocalTime());
        orari.setOraDaljes(dto.getOraDaljesAsLocalTime());
        repository.update(orari);
    }

    public void deleteOrari(int id) throws SQLException {
        repository.delete(id);
    }
}
