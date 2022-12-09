package main;

import java.util.ArrayList;

import dao.SerieDao;
import dao.TemporadaDao;
import pojo.Serie;
import pojo.Temporada;

public class Main {

	public static void main(String[] args) {

//		Serie serie = new Serie("Los Simpsons", 7, "Disney Plus");
//		SerieDao serieDao = new SerieDao();
//		serieDao.insertar(serie);
		
		SerieDao serieDao = new SerieDao();
//		System.out.println(serieDao.buscarPorId(1));
//		Serie serie = serieDao.buscarPorId(1);
//		System.out.println(serie);
//		Temporada t1 = new Temporada(1,"Temporada 1", serie);
//		Temporada t2 = new Temporada(2,"Temporada 2", serie);
		
//		TemporadaDao temporadaDao = new TemporadaDao();
//		temporadaDao.insertar(t1);
//		temporadaDao.insertar(t2);
		
//		Serie los_simpsons = serieDao.buscarPorId(1);
//		
//		serieDao.borrar(los_simpsons);
		
		
//		los_simpsons.setPlataforma("Netflix");
//		serieDao.modificar(los_simpsons);
//		
//		
		ArrayList<Serie> series = serieDao.buscarTodos();
		
		for (Serie serie : series) {
			System.out.println("SERIE: " + serie.getTitulo());
		
			for (Temporada temporada : serie.getTemporada()) {
				System.out.println("TEMPORADA: " + temporada.getTitulo());
			}
			
		}
//		
//		for (Serie serie : series) {
//			System.out.println(serie.getTitulo());
//		}
//		
//		TemporadaDao temporadaDao = new TemporadaDao();
//		
//		ArrayList<Temporada> temporadas = temporadaDao.buscarTodos();
//		
//		for (Temporada temporada : temporadas) {
//			System.out.println(temporada);
//		}
		

	}

}
