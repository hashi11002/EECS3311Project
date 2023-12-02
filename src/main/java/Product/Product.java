package Product;

/* 
 * The Product class is just a simple way for the Controller to keep track of the info
 * of the Product without having to fetch info through the Model from the Database
 * every single time. However, in the end all of the real info is reflected in the Database,
 * and the Product obj should be in sync with its info in the Database. 
 */
public class Product {
	 private final String productName;
	 private int availableQuantity;
	 private final int maxStockQuantity;
	 private final int minStockQuantity;
	 private final PricingStrategy priceStrategy;
	 private final RestockStrategy restockStrategy;
	 
	 Product(String name, int currentQuantity, int maxQuantity, int minQuantity, PricingStrategy priceStrategy, RestockStrategy restockStrategy) {
		 productName = name;
		 availableQuantity = currentQuantity;
		 maxStockQuantity = maxQuantity;
		 minStockQuantity = minQuantity;
		 this.priceStrategy = priceStrategy;
		 this.restockStrategy = restockStrategy;
	 }
	 public Product(String name, int maxQuantity, int availableQuantity){
		this.productName = name;
		this.maxStockQuantity = maxQuantity;
		this.minStockQuantity = 0;
		this.availableQuantity = availableQuantity;
		this.priceStrategy = null;
		this.restockStrategy = null; 
		}

	 public String getName() {
	     return productName;
	 }

	 public int getAvailableQuantity() {
	     return availableQuantity;
	 }

	 /* The only purpose of the following two methods is
	  * for the Controller to easily track how the Product's Info
	  * changes without having to fetch it from the Model every second.
	  * Once again, any real changes are afterwards done to the
	  * Database. 
	  */
	 public void reduceQuantity(int quantity) {
	     availableQuantity -= quantity;
	 }

	 public void restock(int quantity) {
	     availableQuantity += quantity;
	 }
	 
	 public double calculatePrice() {
		 return priceStrategy.calculateTotalPrice(availableQuantity);
	 }

	 public int getRestockAmount() {
		 return restockStrategy.restock();
	 }

	 public int getMaxQuantity() {
			// TODO Auto-generated method stub
			return maxStockQuantity;
	 }
	 
	 public int getMinQuantity() {
			// TODO Auto-generated method stub
			return minStockQuantity;
	 }

	
}
