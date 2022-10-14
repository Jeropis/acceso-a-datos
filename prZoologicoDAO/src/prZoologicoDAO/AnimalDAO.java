package prZoologicoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AnimalDAO {

	private static Connection connection;
	
	public static void insertAnimal(Animal animal) {
		connection = openConnection();
		
		String query = "insert into animales(nombre, habitat, peso_aproximado) values (?,?,?);";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, animal.getNombre());
			ps.setString(2, animal.getHabitat());
			ps.setDouble(3, animal.getPeso_aproximado());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection();
		
	}
	
	private static Connection openConnection() {
		DatabaseConnection dbConnection = new DatabaseConnection();
		connection = dbConnection.getConnection();
		return connection;
	}
	
	private static void closeConnection() {
		try {
			connection.close();
			connection = null;
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
