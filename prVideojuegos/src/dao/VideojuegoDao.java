package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pojo.Plataforma;
import pojo.Videojuego;

public class VideojuegoDao extends ObjetoDao implements InterfazDao<Videojuego> {

	private static Connection connection;

	public VideojuegoDao() {

	}

	@Override
	public ArrayList<Videojuego> buscarTodos() {
		ArrayList<Videojuego> r = new ArrayList<Videojuego>();
		connection = openConnection();
		Videojuego a = null;
		String query = "select * from videojuegos";

		Statement statement;
		try {
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String desarrolladora = rs.getString("desarrolladora");
				int pegi = rs.getInt("pegi");
				int plataforma = rs.getInt("plataforma_id");
				
				PlataformaDao pd = new PlataformaDao();
				
				Plataforma p = pd.buscarPorIdP(plataforma);

				a = new Videojuego(id, nombre, desarrolladora, pegi, p);

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
	public Videojuego buscarPorId(int i) {
		connection = openConnection();
		Videojuego a = null;
		String query = "select * from videojuegos where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String desarrolladora = rs.getString("desarrolladora");
				int pegi = rs.getInt("pegi");
				int plataforma = rs.getInt("plataforma_id");
				
				PlataformaDao pd = new PlataformaDao();
				
				Plataforma p = pd.buscarPorId(plataforma);

				a = new Videojuego(id, nombre, desarrolladora, pegi, p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void insertar(Videojuego t) {
		connection = openConnection();

		String query = "insert into videojuegos (nombre, desarrolladora, pegi, plataforma_id) values (?,?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, t.getNombre());
			ps.setString(2, t.getDesarrolladora());
			ps.setInt(3, t.getPegi());
			ps.setInt(4, t.getPlataforma().getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();

	}

	@Override
	public void modificar(Videojuego t) {
		String query = "update videojuegos set nombre = ?, desarrolladora = ?,pegi = ?, plataforma_id = ? where id = " + t.getId();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, t.getNombre());
			ps.setString(2, t.getDesarrolladora());
			ps.setInt(3, t.getPegi());
			ps.setInt(4, t.getPlataforma().getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	@Override
	public void borrar(Videojuego t) {
		connection = openConnection();

		String query = "delete from videojuegos where id =" + t.getId();

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
