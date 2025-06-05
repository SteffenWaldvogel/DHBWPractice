import java.util.List;
import java.util.Map;

public record Shop<T extends Comparable<T>>(String name, Map<T, List<Integer>> assortment) {
	
	public void addProduct(T product) {
		if(!assortment.containsKey(product)) {
			assortment.put(product, null);
		}
	}
	public void rateProduct(T product, int rating) {
		assortment.entrySet().stream().filter(b -> b.getKey().equals(product)).
	}
}
