package dbconnection;

import java.sql.*;

public class DBConnection {
	private String driverClassName = "com.mysql.cj.jdbc.Driver";
	private String connectionUrl = "jdbc:mysql://localhost/votoelettronico";
	private String dbUser = "root";
	private String dbPwd = "";
	
	private static DBConnection instance = null;
	private Connection connection = null;
	private DBConnection() {
		try {
			Class.forName(driverClassName);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void openConnection() throws SQLException{
		connection = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
	}
	
	public void closeConnection() throws SQLException{
		if(!connection.isClosed())
			connection.close();
	}
	
	public static DBConnection getInstance() {
		if(instance==null)
			instance=new DBConnection();
		return instance;
	}
	
	public PreparedStatement prepara(String s) {
		PreparedStatement result = null;
		try {
			result = connection.prepareStatement(s);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public PreparedStatement prepara(String s, int key) {
		PreparedStatement result = null;
		try {
			result = connection.prepareStatement(s, key);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;	
	}
	
}
