package pojo;

import java.util.ArrayList;

public class Plataforma {

	private int id;
	private String nombre;
	private String fabricante;
	private ArrayList <Videojuego> videojuegos;
	
	
	public Plataforma(int id, String nombre, String fabricante, ArrayList<Videojuego> videojuegos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fabricante = fabricante;
		this.videojuegos = videojuegos;
	}


	public Plataforma(String nombre, String fabricante) {
		super();
		this.nombre = nombre;
		this.fabricante = fabricante;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getFabricante() {
		return fabricante;
	}


	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}


	public ArrayList<Videojuego> getVideojuegos() {
		return videojuegos;
	}


	public void setVideojuegos(ArrayList<Videojuego> videojuegos) {
		this.videojuegos = videojuegos;
	}


	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "\n\tID: " + id + "\n\tNOMBRE: " + nombre + "\n\tFABRICANTE: " + fabricante;
	}
	
	
	
}
