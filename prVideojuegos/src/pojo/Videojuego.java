package pojo;

import java.util.ArrayList;

public class Videojuego {

	private int id;
	private String nombre;
	private String desarrolladora;
	private int pegi;
	private Plataforma plataforma;

	public Videojuego() {

	}

	public Videojuego(int id, String nombre, String desarrolladora, int pegi, Plataforma plataforma) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.desarrolladora = desarrolladora;
		this.pegi = pegi;
		this.plataforma = plataforma;
	}
	
	public Videojuego( String nombre, String desarrolladora, int pegi, Plataforma plataforma) {
		super();
		this.nombre = nombre;
		this.desarrolladora = desarrolladora;
		this.pegi = pegi;
		this.plataforma = plataforma;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDesarrolladora() {
		return desarrolladora;
	}

	public void setDesarrolladora(String desarrolladora) {
		this.desarrolladora = desarrolladora;
	}

	public int getPegi() {
		return pegi;
	}

	public void setPegi(int pegi) {
		this.pegi = pegi;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Videojuego:\n\tID: " + id + "\n\tNOMBRE: " + nombre + "\n\tDESARROLLADORA: " + desarrolladora
				+ "\n\tPEGI: " + pegi + "\nPLATAFORMA: " + plataforma;
	}

}
