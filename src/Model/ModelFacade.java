package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Product.Product;

public class ModelFacade {
	
	private static ModelFacade instance = null;
	
	private ModelFacade() {}
	
	public static ModelFacade getInstance() {
		if (instance == null) {
			instance = new ModelFacade();
		}
		
		return instance;
	}
	
	public Product startOrder(String productName, int orderQuantity, LocalDateTime timestamp) {
		/* Here, we assume a class Fetcher inside of the Model package, gets the info from the database and creates a Product Obj */
		Product obj = Fetcher.getProductObj(productName);
		ArrayList<String> productNames = Fetcher.getProductNames();
		HashMap<String, Integer> productQuantityList = Fetcher.getAvailableQuantitiesFor(productNames);
		Coordinator.getInstance().startOrder(productQuantityList, productName, orderQuantity, timestamp);;
		return obj;
	}
	
	public void rejectOrder() {
		Coordinator.getInstance().rejectOrder();
	}
	
	public void restock(String ProductID, int restockQuantity) {
		Coordinator.getInstance().restockBegun();
		Updater.updateProduct(ProductID, restockQuantity);
		Coordinator.getInstance().restockEnd();
	}
	
	public void displayPending() {
		Coordinator.getInstance().displayPending();
	}
	
	public void depleteProduct(String ProductID, int depletedQuantity ) {
		Updater.depleteProduct(ProductID, depletedQuantity);
		Coordinator.getInstance().productDepleted();
	}

}
