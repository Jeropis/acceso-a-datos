package main;

import java.util.ArrayList;

import dao.PlataformaDao;
import dao.VideojuegoDao;
import pojo.Plataforma;
import pojo.Videojuego;

public class Main {

	public static void main(String[] args) {

		try {

			PlataformaDao pd = new PlataformaDao();

			VideojuegoDao vd = new VideojuegoDao();
			/**
			 * Borramos todos los datos de ambas tablas
			 */
			vd.deleteAllTables();
			/**
			 * Reseteamos autoincrement de la tabla plataformas
			 */
			pd.resetAutoIncrement();
			/**
			 * Reseteamos autoincrement de la tabla videojuegos
			 */
			vd.resetAutoIncrement();

			/**
			 * Creamos tres registros de la tabla plataformas
			 */
			Plataforma p1 = new Plataforma("Xbox Series S", "Microsoft");
			pd.insertar(p1);

			Plataforma p2 = new Plataforma("PS4", "Sony");
			pd.insertar(p2);

			Plataforma p3 = new Plataforma("Nintendo Switch", "Nintendo");
			pd.insertar(p3);
			/**
			 * Modificamos las variables de todas las plataformas creadas para que recoja de
			 * base de datos el id de cada uno de los registros
			 */
			p1 = pd.buscarPorId(1);
			p2 = pd.buscarPorId(2);
			p3 = pd.buscarPorId(3);

			/**
			 * Creamos tres registros de la tabla videojuegos
			 */
			Videojuego v1 = new Videojuego("Pokemon Diamante", "Gamefreak", 7, p3);
			vd.insertar(v1);

			Videojuego v2 = new Videojuego("Halo 3", "343 Industries", 16, p1);
			vd.insertar(v2);

			Videojuego v3 = new Videojuego("God of war Ragnarok", "SIE Santa Monica Studio", 18, p2);
			vd.insertar(v3);

			/**
			 * Mostramos el videojuego con id 1, lo modificamos y lo volvemos a mostrar para
			 * verificar los cambios
			 */
			Videojuego v = vd.buscarPorId(1);
			System.out.println(v);
			v.setPegi(3);
			System.out.println("Modificando registros...");
			vd.modificar(v);
			Thread.sleep(2000);
			System.out.println(v);
			System.out.println("---------------------------------------------------");
			Thread.sleep(3000);
			
			/**
			 * Mostramos todas los videojuegos y todas las plataformas, borramos una
			 * plataforma, y mostramos todo de nuevo para verificar que se ha borrado esa
			 * plataforma, y todos los videojuegos que tuviera esa plataforma
			 */
			System.out.println("Lista de videojuegos: ");
			ArrayList<Videojuego> videojuegos = vd.buscarTodos();
			for (Videojuego juegos : videojuegos) {
				System.out.println("\t - " + juegos.getNombre());
			}
			System.out.println("Lista de plataformas: ");
			ArrayList<Plataforma> plataformas = pd.buscarTodos();
			for (Plataforma p : plataformas) {
				System.out.println("\t - " + p.getNombre());
			}
//			System.out.println(videojuegos);
			System.out.println("Borrando registros...");
			pd.borrar(p3);
			Thread.sleep(2000);
			videojuegos = vd.buscarTodos();
			System.out.println("Lista de videojuegos: ");
			for (Videojuego juegos : videojuegos) {
				System.out.println("\t - " + juegos.getNombre());
			}
			plataformas = pd.buscarTodos();
			System.out.println("Lista de plataformas: ");
			for (Plataforma p : plataformas) {
				System.out.println("\t - " + p.getNombre());
			}
//			System.out.println(videojuegos);
			System.out.println("---------------------------------------------------");
			Thread.sleep(3000);
			
			/**
			 * Vamos a añadir unos cuantos videojuegos,
			 * para poder mostrar bien la funcion de buscarVideojuegosPorPlataforma()
			 * y los mostramos. Mostramos tambien todos los videojuegos que tenemos en la base de datos.
			 */
			Videojuego v4 = new Videojuego("Ghost of tsushima", "Sucker Punch Productions", 18, p2);
			vd.insertar(v4);
			Videojuego v5 = new Videojuego("Spider-Man Miles Morales", "Insomniac Games", 16, p2);
			vd.insertar(v5);
			Videojuego v6 = new Videojuego("Forza Horizon 4", "Playground Games", 3, p1);
			vd.insertar(v6);
			ArrayList<Videojuego> videojuegosPS4 = vd.buscarPorPlataformaId(p2.getId());
			System.out.println("Lista de videojuegos de la plataforma " + p2.getNombre() + ": ");
			for (Videojuego juegos : videojuegosPS4) {
				System.out.println("\t - " + juegos.getNombre());
			}
			Thread.sleep(2000);
			videojuegos = vd.buscarTodos();
			System.out.println("Lista de videojuegos: ");
			for (Videojuego juegos : videojuegos) {
				System.out.println("\t - " + juegos.getNombre());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		pd.borrar(p);

//		Videojuego v1 = new Videojuego("Pokemon Diamante", "Gamefreak", 7, p );
//
//		vd.insertar(v1);

//		Videojuego v = new Videojuego();
////		
//		v = vd.buscarPorId(3);
//		
//		v.setNombre("Pokemon púrpura");
//		
//		vd.modificar(v);
//		
//		System.out.println(v);

//		ArrayList<Plataforma> plataformas = pd.buscarTodos();
//		System.out.println(plataformas);
//		
//		ArrayList<Videojuego> videojuegos = vd.buscarPorPlataformaId(p.getId());
//		System.out.println(videojuegos);

	}

}
