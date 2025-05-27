import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Excercise01 {
	static List<String> colors = Arrays.asList("ReD", "grEEn", "BLUE");
	static List<Integer> luki = Arrays.asList(1,2,3,5,6);
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(upperCase());
		System.out.println(lowerCase());
		System.out.println(zweitgroesstes(luki));
		System.out.println(zweitkleinstes(luki));
	}

	public static List<String> upperCase() {
		List<String> lena = colors.stream().map(b -> b.toUpperCase()).toList();
		return lena;
	}

	public static List<String> lowerCase() {
		List<String> lenainklein = colors.stream().map(l -> l.toLowerCase()).toList();
		return lenainklein;
	}
	public static Optional<Integer> zweitgroesstes(List<Integer> luki){
		Optional<Integer> secondLargest = luki.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
		return secondLargest;
	}
	public static Optional<Integer> zweitkleinstes(List<Integer> luki){
		Optional<Integer> secondSmallest = luki.stream().distinct().sorted().skip(1).findFirst();
		return secondSmallest;
	}

}
