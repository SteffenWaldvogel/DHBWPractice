
public record FastFood(String name, FastFoodCategory fastFoodCategory, double calorificValueInKcal, boolean isVegetarian) {
	
	
	public int compareTo(FastFood otherFood) {
		return Double.compare(otherFood.calorificValueInKcal(), calorificValueInKcal);
	}
}
