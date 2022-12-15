package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pojo.Plataforma;
import pojo.Videojuego;

public class PlataformaDao extends ObjetoDao implements InterfazDao<Plataforma> {

	private static Connection connection;

	public PlataformaDao() {

	}

	@Override
	public ArrayList<Plataforma> buscarTodos() {
		ArrayList<Plataforma> r = new ArrayList<Plataforma>();
		connection = openConnection();
		Plataforma a = null;
		String query = "select * from plataformas";

		Statement statement;
		try {
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String fabricante = rs.getString("fabricante");

				a = new Plataforma(id, nombre, fabricante, null);

				r.add(a);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r;
	}

	@Override
	public Plataforma buscarPorId(int i) {
		connection = openConnection();
		Plataforma a = null;
		String query = "select * from plataformas where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String nombre = rs.getString("nombre");
				String fabricante = rs.getString("fabricante");

				a = new Plataforma(i, nombre, fabricante, null);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	public Plataforma buscarPorIdP(int i) {
		connection = openConnection();
		Plataforma a = null;
		String query = "select * from plataformas where id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String nombre = rs.getString("nombre");
				String fabricante = rs.getString("fabricante");

				a = new Plataforma(i, nombre, fabricante, null);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void insertar(Plataforma plataforma) {
		connection = openConnection();

		String query = "insert into plataformas (nombre, fabricante) values (?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, plataforma.getNombre());
			ps.setString(2, plataforma.getFabricante());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void modificar(Plataforma t) {

		String query = "update plataforma set nombre = ?, fabricante = ? where id = " + t.getId();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, t.getNombre());
			ps.setString(2, t.getFabricante());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void borrar(Plataforma t) {

		connection = openConnection();

		ArrayList<Videojuego> videojuegos = obtenerVideojuegos(t);

		for (int i = 0; i < videojuegos.size(); i++) {

			VideojuegoDao vd = new VideojuegoDao();
			Videojuego temp = vd.buscarPorId(videojuegos.get(i).getId());
			vd.borrar(temp);

		}

		String query = "delete from plataformas where id = " + t.getId();

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Videojuego> obtenerVideojuegos(Plataforma plataforma) {
		ArrayList<Videojuego> videojuegos = new ArrayList<>();

		connection = openConnection();

		String query = "SELECT * FROM videojuegos WHERE plataforma_id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, plataforma.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Videojuego videojuego = new Videojuego(rs.getInt("id"), rs.getString("nombre"),
						rs.getString("desarrolladora"), rs.getInt("pegi"), plataforma);

				videojuegos.add(videojuego);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// closeConnection();
		return videojuegos;
	}
	
	public void resetAutoIncrement() {
        try {
            connection = openConnection();
            String query = "alter table plataformas AUTO_INCREMENT=1;";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
