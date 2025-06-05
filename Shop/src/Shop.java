import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public record Shop<T extends Comparable<T>>(String name, Map<T, List<Integer>> assortment) {

	public void addProduct(T product) {
//		if (!assortment.containsKey(product)) {
//			assortment.put(product, new ArrayList<>());
//		}
		assortment.putIfAbsent(product, new ArrayList<>());
	}

	public void rateProduct(T product, int rating) throws NoProductFoundException, InvalidRatingException {
		if (!assortment.containsKey(product)) {
			throw new NoProductFoundException();
		}
		if (rating < 0 || rating > 5) {
			throw new InvalidRatingException();
		}
		assortment.get(product).add(rating);
	}

	public Optional<T> getBestRatedProduct() {
		return assortment.entrySet().stream().filter(e -> e.getValue() != null && !e.getValue().isEmpty())
				.max(Comparator
						.comparingDouble(e -> e.getValue().stream().mapToInt(Integer::intValue).average().orElse(0)))
				.map(Entry::getKey);
	}

	public List<T> getAllProductsByNaturalOrder() {
//		List<T> allProducts = new ArrayList<>(assortment.keySet());
//		return allProducts;
		return assortment.keySet().stream().sorted().toList();

	}

}
