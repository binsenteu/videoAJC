package videoAJC.dao.realisation;

public class DaoRealisationFactory {
    public static DaoRealisation singleton = null;

    public static DaoRealisation getInstance() {
        if (singleton == null) {
            singleton = new DaoRealisationJdbcImpl();
        }

        return singleton;
    }
}
