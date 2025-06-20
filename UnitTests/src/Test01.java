import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class Test01 {
	private Rental rental;

	@BeforeAll
	public void setUp() {
		rental = new Rental("Rental");
		Car car1 = new Car("Toyota", "Corola", Engine.PETROL, 5);
		Car car2 = new Car("VW", "Golf", Engine.DIESEL, 4);
		Truck truck1 = new Truck("Mercedes", "Actros", Engine.DIESEL, 10000);

		rental.addAllVehicles(car1, car2, truck1);
	}

	public void testVehicleCount() {
		assert rental.getVehicles().size() == 3;
	}

	@Test
	public void testTransformAllTrucks() {
		rental.getVehicles().stream().filter(v -> v instanceof Truck).map(v -> (Truck) v)
				.forEach(t -> assertTrue(t.isTransformed()));
		rental.transformAllTrucks();
		rental.getVehicles().stream().filter(v -> v instanceof Truck).map(v -> (Truck) v)
		.forEach(t -> assertTrue(t.isTransformed()));
		rental.transformAllTrucks();
        rental.getVehicles().stream()
            .filter(v -> v instanceof Truck)
            .map(v -> (Truck) v)
            .forEach(t -> assertFalse(t.isTransformed()));
	}
	@Test
	public void testAccelerateAllVehicles() throws InvalidValueException {
		assertThrows(InvalidValueException.class, () -> {rental.accelerateAllVehicles(-100);});
	}
}
