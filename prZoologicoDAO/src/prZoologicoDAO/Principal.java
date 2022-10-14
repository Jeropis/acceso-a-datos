package prZoologicoDAO;

public class Principal {

	public static void main(String[] args) {
		
		Animal a1 = new Animal("Ardilla", "Bosque", 0.1);
		
		AnimalDAO.insertAnimal(a1);
		
		
	}
	
}
