package prZoologicoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	public static ArrayList<Animal> findAllAnimales() {

		ArrayList<Animal> r = new ArrayList<Animal>();
		connection = openConnection();
		Animal a = null;
		String query = "select * from animales";

		Statement statement;
		try {
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String habitat = rs.getString("habitat");
				double peso = rs.getDouble("peso_aproximado");

				a = new Animal(id, nombre, habitat, peso);

				r.add(a);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public static Animal findById(int id) {

		connection = openConnection();
		Animal a = null;
		String query = "select * from animales where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String habitat = rs.getString("habitat");
				double peso = rs.getDouble("peso_aproximado");

				a = new Animal(id, nombre, habitat, peso);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;

	}

	public static void deleteAnimalByNombre(String nombre) {
		connection = openConnection();

		String query = "delete from animales where nombre = '" + nombre + "'";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeConnection();

	}

	public static void deleteAllAnimal() {
		connection = openConnection();

		String query = "delete from animales";

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate(query);

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
