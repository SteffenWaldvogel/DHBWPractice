import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Excercises02 {

	public static void main(String[] args) {
		NAMES.put("Anne", "12345");
		NAMES.put("Luki", "123");
		NAMES.put("Noah", "6789");
		System.out.println(findPhonenumberByName("Anne"));
	}
	static HashMap<String, String> NAMES = new HashMap<String, String>();

	public static Optional<String> findPhonenumberByName(String name){
		if(NAMES.containsKey(name)) {
			return Optional.ofNullable(NAMES.get(name));
		}
		return Optional.empty();
	}
}
