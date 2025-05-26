import java.util.ArrayList;
import java.util.List;

public class AnimalShelter {
	
	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<>();
		
		animals.add(new Cat("Cecile"));
		animals.add(new Cat("Carlo"));
		animals.add(new Dog("Bart"));
		animals.add(new Dog("Daisy"));
		
		for(Animal a : animals){
			a.makeSound();
		}
		
		
	
	
	
	}
	
}
