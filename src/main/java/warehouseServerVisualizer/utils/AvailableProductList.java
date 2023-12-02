package warehouseServerVisualizer.utils;

import java.util.HashMap;

public class AvailableProductList {
	private static AvailableProductList instance = null;

	
	private HashMap<String, Integer> availableProductList = new HashMap<String, Integer>();

	public HashMap<String, Integer> getAvailableProductList() {
		return availableProductList;
	}

	public static AvailableProductList getInstance() {
		if (instance == null)
			instance = new AvailableProductList();
		

		return instance;
	}

	private AvailableProductList() {
		//findAvailableProductsAndQuantities();
	}

	public HashMap<String, Integer> findAvailableProductsAndQuantities() {
		
		// Here we query the Product DB and we get the product names or the product IDs
 
		availableProductList.clear();
		
		// Here we query the Product DB and we get the product names or the product IDs
		
		availableProductList.put("Product 1", 10);
		availableProductList.put("Product 2", 20);
		availableProductList.put("Product 3", 30);
		availableProductList.put("Product 4", 20);
		availableProductList.put("Product 5", 10);
		
		return availableProductList;

	}
	
	public void setAvailableProductsAndQuantities(HashMap<String, Integer> availableProductList) {
		this.availableProductList = availableProductList;
	}



}
