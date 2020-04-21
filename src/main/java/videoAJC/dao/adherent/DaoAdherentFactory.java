package videoAJC.dao.adherent;

public class DaoAdherentFactory {
	private static DaoAdherent daoAdherent = null;

	public static DaoAdherent getInstance() {
		if (daoAdherent == null) {
			daoAdherent = new DaoAdherentJdbcImpl();
		}
		return daoAdherent;
	}
}
