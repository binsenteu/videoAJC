package videoAJC.dao.realisateur;

import java.util.List;

import videoAJC.dao.DaoGeneric;
import videoAJC.model.Realisateur;

public interface DaoRealisateur extends DaoGeneric<Realisateur, Integer> {
    List<Realisateur> findByName(String nom);
}
