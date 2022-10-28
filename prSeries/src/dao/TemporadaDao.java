package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Temporada;
import util.DatabaseConnection;

public class TemporadaDao implements Dao<Temporada> {

	private static Connection connection;


	
	@Override
	public ArrayList<Temporada> buscarTodos() {

		return null;
	}

	@Override
	public Temporada buscarPorId(int i) {

		return null;
	}

	@Override
	public void insertar(Temporada t) {

		connection = openConnection();

		String query = "insert into temporadas (num_temporada, titulo, serie_id) values (?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, t.getNum_temporada());
			ps.setString(2, t.getTitulo());
			ps.setInt(3, t.getSerie().getId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void modificar(Temporada t) {

	}

	@Override
	public void borrar(Temporada t) {

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
