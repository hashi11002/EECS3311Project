package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Product.Product;
import Product.ProductFactory;

public class Fetcher {
	
	
	public static Product getProductObj(String ProductName) {
		/* LET'S ASSUME it gets the following info from some database */
		return ProductFactory.getInstance().createProduct(ProductName, 30, 20, 40, null, null);
	}
	
	public static ArrayList<String> getProductNames() {
		/* LET'S ASSUME it gets the following info from some database */
		ArrayList<String> productNames = new ArrayList<String>();
		productNames.add("Product1");
		productNames.add("Product2");
		productNames.add("Product3");
		productNames.add("Product4");
		return productNames;
		
	}
	
	public static HashMap<String, Integer> getAvailableQuantitiesFor(ArrayList<String> productNames) {
		/* LET'S ASSUME it gets the following info from some database */
		HashMap<String, Integer> productData = new HashMap<String, Integer>();
		for (String e : productNames) {
			productData.put(e, 20);
		}
		return productData;
	}

}
