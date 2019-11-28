package db01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager2
{
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://127.0.0.1:3306/map01";
	private final String USERN = "root";
	private final String PSW = "root";

	private static DBManager2 _instance = null;
	
	private DBManager2()
	{
		try {
			Class.forName(DRIVER);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static DBManager2 getInstance()
	{
		if(_instance == null)
			return (_instance = new DBManager2());
		
		return _instance;
	}
	
	public Connection getConnectionMySql()
	{
		Connection conn = null;
		try {
			conn =  DriverManager.getConnection(URL, USERN, PSW);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}
}