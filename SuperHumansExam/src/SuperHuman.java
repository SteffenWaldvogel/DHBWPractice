
public abstract class SuperHuman {
	private final String name;
	private final Universe universe;
	private final int power;
	
	public SuperHuman(String name, Universe universe, int power) {
		this.name = name;
		this.universe = universe;
		this.power = power;
	}
	
	public String name() {
		return name;
	}
	public Universe universe() {
		return universe;
	}
	public int power() {
		return power;
	}
}
