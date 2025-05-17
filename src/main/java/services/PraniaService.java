package services;

import models.domain.Prania;
import repository.PraniaRepository;

import java.util.List;

public class PraniaService {

    private PraniaRepository repo = new PraniaRepository();

    public List<Prania> getAll() {
        return repo.getAll();
    }

    public void add(Prania p) {
        repo.insert(p);
    }

    public void update(Prania p) {
        repo.update(p);
    }

    public void delete(int id) {
        repo.delete(id);
    }

    public void save(Prania prania) {
        repo.save(prania);
    }
}
