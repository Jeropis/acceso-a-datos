package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Serie;
import util.DatabaseConnection;

public class SerieDao implements Dao<Serie> {

	private static Connection connection;

	public SerieDao() {
		
	}
	
	@Override
	public ArrayList<Serie> buscarTodos() {

		return null;
	}

	@Override
	public Serie buscarPorId(int i) {

		connection = openConnection();
		Serie a = null;
		String query = "select * from series where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				String titulo = rs.getString("titulo");
				int edad = rs.getInt("edad");
				String plataforma = rs.getString("plataforma");

				a = new Serie(i,titulo, edad, plataforma,null);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void insertar(Serie serie) {

		connection = openConnection();

		String query = "insert into series(titulo, edad, plataforma) values (?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, serie.getTitulo());
			ps.setInt(2, serie.getEdad());
			ps.setString(3, serie.getPlataforma());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
	}

	@Override
	public void modificar(Serie t) {

	}

	@Override
	public void borrar(Serie t) {

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
