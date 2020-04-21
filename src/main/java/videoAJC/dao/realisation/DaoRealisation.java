package videoAJC.dao.realisation;

import videoAJC.model.RealisationKey;

public interface DaoRealisation {

    public default void insert(RealisationKey obj) {
    }

    public default void deleteFromRealisateur(Integer key) {
    }

    public default void deleteFromFilm(Integer key) {
    }

}
