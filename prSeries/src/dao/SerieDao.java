package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pojo.Serie;
import pojo.Temporada;

public class SerieDao extends ObjetoDao implements InterfazDao<Serie> {

	private static Connection connection;

	public SerieDao() {

	}

	@Override
	public ArrayList<Serie> buscarTodos() {

		ArrayList<Serie> r = new ArrayList<Serie>();
		connection = openConnection();
		Serie a = null;
		String query = "select * from series";

		Statement statement;
		try {
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {

				ArrayList<Temporada> temporadas = new ArrayList<Temporada>();

				String query_temporadas = "select * from temporadas where serie_id = ?";
				PreparedStatement ps_temporadas = connection.prepareStatement(query_temporadas);
				ps_temporadas.setInt(1, rs.getInt("id"));
				ResultSet rs_temporadas = ps_temporadas.executeQuery();

				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				int edad = rs.getInt("edad");
				String plataforma = rs.getString("plataforma");

				a = new Serie(id, titulo, edad, plataforma, temporadas);

				while (rs_temporadas.next()) {

					Temporada temporada = new Temporada(rs_temporadas.getInt("id"), rs_temporadas.getInt("num_temporada"),
							rs_temporadas.getString("titulo"), a);

					temporadas.add(temporada);

				}
				
				

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

				a = new Serie(i, titulo, edad, plataforma, null);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
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

		String query = "update series set titulo = ?, edad = ?, plataforma = ? where id = " + t.getId();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, t.getTitulo());
			ps.setInt(2, t.getEdad());
			ps.setString(3, t.getPlataforma());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeConnection();

	}

	public ArrayList<Temporada> obtenerTemporadas(Serie serie) {
		ArrayList<Temporada> temporadas = new ArrayList<>();

		connection = openConnection();

		String query = "SELECT * FROM temporadas WHERE serie_id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, serie.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Temporada temporada = new Temporada(rs.getInt("id"), rs.getInt("num_temporada"), rs.getString("Titulo"),
						serie);

				temporadas.add(temporada);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// closeConnection();
		return temporadas;
	}

	@Override
	public void borrar(Serie t) {

		connection = openConnection();

		ArrayList<Temporada> temporadas = obtenerTemporadas(t);

		for (int i = 0; i < temporadas.size(); i++) {

			TemporadaDao td = new TemporadaDao();
			Temporada temp = td.buscarPorId(temporadas.get(i).getId());
			td.borrar(temp);

		}

		String query = "delete from series where id = " + t.getId();

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
