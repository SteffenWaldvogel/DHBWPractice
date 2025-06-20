

/**
 * Motor
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public enum Engine {

   DIESEL("Diesel"), PETROL("Benzin"), GAS("Gas"), ELECTRO("Elektro");

   private final String description;

   Engine(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

}