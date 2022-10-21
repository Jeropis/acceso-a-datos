package prZoologicoDAO;

import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {

//		Animal a1 = new Animal("Ardilla", "Bosque", 0.1);
//		AnimalDAO.insertAnimal(a1);
//		Animal a2 = new Animal("Perro", "Domestico", 0.2);
//		AnimalDAO.insertAnimal(a2);

//		AnimalDAO.deleteAllAnimal();

		ArrayList<Animal> listaAnimales = AnimalDAO.findAllAnimales();

		for (int i = 0; i < listaAnimales.size(); i++) {
			System.out.println(listaAnimales.get(i));
		}

	}

}
