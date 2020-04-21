package videoAJC.dao.realisation;

import java.util.List;

import videoAJC.model.Film;
import videoAJC.model.Realisateur;
import videoAJC.model.Realisation;
import videoAJC.model.RealisationKey;

public interface DaoRealisation {

    void insert(RealisationKey obj);

    void deleteFromRealisateur(Integer key);

    void deleteFromFilm(Integer key);

    List<Realisation> findAllFilm(Realisateur realisateur);

    List<Realisation> findAllRealisateur(Film film);

}
