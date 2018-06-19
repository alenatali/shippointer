package dao.mssqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection c = null;
	
	private static final String databaseName = "shippointer";
	private static final String user = "commom";
	private static final String pass = "1234";
	private static final String port = "1433";

	private Conexao() {
	}
	
	private static void instancia () {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = String.format("jdbc:sqlserver://localhost:%s;database=%s;user=%s;password=%s;", port, databaseName, user, pass);
			c = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConection() {
		if(c == null) {
			instancia ();
		}
		return c;
	}
	
	public static void close() {
		try {
			c.close();
			c = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
