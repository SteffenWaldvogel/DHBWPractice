
public class Dog extends Animal {

	public Dog(String name) {
		super(name);
	}

	@Override
	public void makeSound() {
		System.out.println("Woof, ich bin: " + this.getName());
	}
}
