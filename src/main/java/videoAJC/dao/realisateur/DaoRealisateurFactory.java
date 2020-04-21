package videoAJC.dao.realisateur;

public class DaoRealisateurFactory {
    public static DaoRealisateur singleton = null;

    public static DaoRealisateur getInstance() {
        if (singleton == null) {
            singleton = new DaoRealisateurJdbcImpl();
        }
        
        return singleton;
    }
}
