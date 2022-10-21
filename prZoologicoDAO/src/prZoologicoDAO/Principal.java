package prZoologicoDAO;

public class Principal {

	public static void main(String[] args) {
		
//		Animal a1 = new Animal("Ardilla", "Bosque", 0.1);
//		AnimalDAO.insertAnimal(a1);
//		Animal a2 = new Animal("Perro", "Domestico", 0.2);
//		AnimalDAO.insertAnimal(a2);
		
//		AnimalDAO.deleteAllAnimal();
		
		Animal buscador = AnimalDAO.findById(1);
		
		System.out.println(buscador);
		

	}
	
}
