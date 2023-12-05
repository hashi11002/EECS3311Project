package Model.Product;

import java.util.HashMap;

import Model.Product.Pricing.PricingStrategy;
import Model.Product.Restocking.RestockStrategy;

public class ProductFactory {
	private static ProductFactory instance = null;
	private static HashMap<String, Product> productRepo;
	
	private ProductFactory() {
		productRepo = new HashMap<String, Product>();
	}
	
	public static ProductFactory getInstance() {
		if (instance == null) {
			instance = new ProductFactory();
		}
		
		return instance;
	}
	
	/* This is simply to prevent multiple unnecessary objects being made for the same Product
	*/
	public Product createProduct(String name, int currentQuantity, int maxQuantity, int minQuantity, PricingStrategy priceStrategy, RestockStrategy restockStrategy) {
	    if (!productRepo.containsKey(name)) {
	    	productRepo.put(name, new Product(name, currentQuantity, maxQuantity, minQuantity, priceStrategy, restockStrategy));
	    }
	    
		return productRepo.get(name);
	}
}