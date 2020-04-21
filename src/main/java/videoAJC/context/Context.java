package videoAJC.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Context {
	private Connection connection = null;

	private static Context ctx = null; // singleton

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Context() {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/gestion_film", "postgres", "postgres");
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static Context getInstance() {
		if (ctx == null) {
			ctx = new Context();
		}
		return ctx;
	}

	public static void destroy() {
		if (ctx != null) {
			try {
				ctx.connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			ctx = null;
		}
	}
}
