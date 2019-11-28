package db01;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main 
{
	String url = "jdbc:mysql://127.0.0.1/map01";
	String user = "root";
	String psw = "root";
	
//	public static void main(String[] args) 
//	{
//		String url = "jdbc:mysql://127.0.0.1:3306/map01";
//		String user = "root";
//		String psw = "root";
//		try 
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(url, user, psw);
//			Statement stmt = conn.createStatement();
//			
//			ResultSet result = stmt.executeQuery("SELECT * FROM persona");
//			while(result.next())
//			{
//				int id = result.getInt("id");
//				String nome = result.getString("nome");
//				String cognome = result.getString("cognome");
//				Persona persona = new Persona(id, nome, cognome);
//				System.out.println(persona);
//			}
//		} 
//		catch (ClassNotFoundException e) 
//		{
//			e.printStackTrace();
//		} 
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//		}
//		
//	}
		
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException 
	{
		Connection conn = DBManager2.getInstance().getConnectionMySql();
		PersonaDao personaDao = new PersonaDao();
		Persona p = new Persona();
		p.setNome("Pippo");
		p.setCognome("Franco");
		
		try {
			Statement stmt = conn.createStatement();
//			personaDao.savePersona(p);
			ResultSet rs = stmt.executeQuery("SELECT * FROM persona");
			while(rs.next())
			{
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				Persona persona = new Persona(id, nome, cognome);
				System.out.println(persona);
				
			}
			personaDao.savePersona2(p);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}