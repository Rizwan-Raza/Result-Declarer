package rex.jfx.result.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataBase  {
	public static Connection CONN = null;

	private static final String USERNAME = "prolog";
	private static final String PASSWORD = "academy";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";

	public static boolean connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			CONN = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found, means Driver not Loaded");
			System.out.println(e);
			return false;
		} catch (SQLException e) {
			System.out.println("Connection Problem");
			System.out.println(e);
			return false;
		}
	}

	public static boolean close() {
		try {
			CONN.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Closing Problem");
			System.out.println(e);
			return false;
		}
	}
}
