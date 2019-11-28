package db01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBManager 
{
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://127.0.0.1:3306/map01";
	private final String USERN = "root";
	private final String PSW = "root";
	private Connection conn;

	public DBManager()
	{
		try 
		{
			Class.forName(DRIVER);
			this.conn = DriverManager.getConnection(URL, USERN, PSW);
		}
		catch
		(
		ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch
		(
		SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Persona> getPersona() throws SQLException
	{
		Statement stmt = this.conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM persona");
		List<Persona> list = new ArrayList<Persona>();
		
		while(rs.next())
		{
			list.add( new Persona(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome")));
		}
		
		return list;
	}
	
	public void clode() throws SQLException
	{
		if(this.conn != null)
			this.conn.close();
	}
}