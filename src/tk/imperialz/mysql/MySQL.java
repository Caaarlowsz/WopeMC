package tk.imperialz.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
	public static Connection con;
	private String HOST;
	private String DATABASE;
	private String USER;
	private String PASSWORD;

	public MySQL(final String host, final String database, final String user, final String password) {
		this.HOST = "localhost";
		this.DATABASE = "test";
		this.USER = "root";
		this.PASSWORD = "";
		this.HOST = host;
		this.DATABASE = database;
		this.USER = user;
		this.PASSWORD = password;
		this.connectSql();
	}

	public synchronized void connectSql() {
		if (!alreadyConnected()) {
			try {
				MySQL.con = DriverManager.getConnection(
						"jdbc:mysql://" + this.HOST + ":3306/" + this.DATABASE + "?autoReconnect=true", this.USER,
						this.PASSWORD);
				System.out.println("[MySQL] Conex\u00e3o iniciada com sucesso.");
			} catch (SQLException e) {
				System.out.println("[MySQL] A conex\u00e3o com MYSQL deu erro: " + e.getMessage());
			}
		}
	}

	public static boolean alreadyConnected() {
		return MySQL.con != null;
	}

	public static void disconnect() {
		try {
			MySQL.con.close();
			System.out.print("O servidor se desconectou do MYSQL.");
		} catch (SQLException ex) {
		}
	}

	public static PreparedStatement getStatement(final String sql) {
		if (alreadyConnected()) {
			try {
				return MySQL.con.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ResultSet getResult(final String sql) {
		if (alreadyConnected()) {
			try {
				final PreparedStatement ps = getStatement(sql);
				return ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
