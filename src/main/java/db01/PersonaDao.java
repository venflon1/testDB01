package db01;

import java.lang.Character.Subset;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonaDao 
{
	public boolean savePersona(Persona p) throws SQLException
	{
		Connection connection = (Connection) DBManager2.getInstance().getConnectionMySql();
		PreparedStatement stm = null;
		stm = connection.prepareStatement("INSERT INTO persona(nome, cognome) VALUES(?,?)");
		stm.setString(1, p.getNome());
		stm.setString(2, p.getCognome());
		
		return stm.execute();
	}
	
	public boolean savePersona2(Persona p) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		Connection connection = DBManager2.getInstance().getConnectionMySql();
		String query = "INSERT INTO ";
		query+=p.getClass().getSimpleName();
		query+=" (";
		
		// Persona.class mi da la struttura delle classe e il metodo getField mi da un array con i campi dell'oggetto
		Field[] fieldsObjectPersonaInst = Persona.class.getDeclaredFields();
//		System.out.println(fieldsObjectPersonaInst.length);

		int i=0;
		for(Field f : fieldsObjectPersonaInst) {
//			System.out.println(f.getName());
			if(!f.getName().equals("id")){
				query+=f.getName() + ",";
				i++;
			}
		}		
		query = query.substring(0, query.length()-1);
		query+=") VALUES(";
		
		for(Field f : fieldsObjectPersonaInst) {
			if(!f.getName().contentEquals("id"))
				query+="?,";
		}
		query=query.substring(0, query.length()-1);
		query+=")";
		PreparedStatement stm = connection.prepareStatement(query);
		System.out.println(query);
		i=1;
		for(Field f : fieldsObjectPersonaInst) {
			if(!f.getName().equals("id")){
				String s = f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
				
				Object v = p.getClass().getMethod("get"+s).invoke(p);
				stm.setString(i, (String) v);
				i+=1;
			}
		}		
		
		return stm.execute();
	}
}
