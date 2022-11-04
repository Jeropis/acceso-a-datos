package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pojo.Serie;
import pojo.Temporada;
import util.DatabaseConnection;

public class TemporadaDao extends ObjetoDao implements InterfazDao<Temporada> {

	private static Connection connection;

	@Override
	public ArrayList<Temporada> buscarTodos() {
		ArrayList<Temporada> r = new ArrayList<Temporada>();
		connection = openConnection();
		Temporada a = null;
		String query = "select * from temporadas";

		Statement statement;
		try {
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				int numTemporada = rs.getInt("num_temporada");
				String titulo = rs.getString("titulo");
				int serie = rs.getInt("serie_id");

				SerieDao sd = new SerieDao();

				Serie s = sd.buscarPorId(serie);

				a = new Temporada(id, numTemporada, titulo, s);

				r.add(a);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
		return r;
	}

	@Override
	public Temporada buscarPorId(int i) {

		connection = openConnection();
		Temporada a = null;
		String query = "select * from temporadas where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int numTemporada = rs.getInt("num_temporada");
				String titulo = rs.getString("titulo");
				int serie = rs.getInt("serie_id");

				SerieDao sd = new SerieDao();

				Serie s = sd.buscarPorId(serie);

				a = new Temporada(id, numTemporada, titulo, s);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
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
		closeConnection();
	}


	@Override
	public void modificar(Temporada t) {

		String query = "update temporadas set num_temporada = ?, titulo = ?, serie_id = ? where id = " + t.getId();
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

		closeConnection();

	}

	@Override
	public void borrar(Temporada t) {

		connection = openConnection();

		String query = "delete from temporadas where serie_id =" + t.getSerie().getId() + " and id =" + t.getId();

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeConnection();
		
	}

}
