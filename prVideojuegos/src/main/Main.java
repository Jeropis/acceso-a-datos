package main;

import java.util.ArrayList;

import dao.PlataformaDao;
import dao.VideojuegoDao;
import pojo.Plataforma;
import pojo.Videojuego;

public class Main {

	public static void main(String[] args) {
		PlataformaDao pd = new PlataformaDao();
//		Plataforma p1 = new Plataforma("Xbox Series S", "Microsoft");
//
//		pd.insertar(p1);

		VideojuegoDao vd = new VideojuegoDao();

		Plataforma p = pd.buscarPorId(1);
//
		pd.borrar(p);
		
//		Videojuego v1 = new Videojuego("Pokemon Escarlata", "Gamefreak", 7, p );

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
		
//		ArrayList<Videojuego> videojuegos = vd.buscarTodos();
//		System.out.println(videojuegos);

	}
	
	

}
