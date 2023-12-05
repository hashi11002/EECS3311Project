package Controller;

import java.time.LocalDateTime;

import Model.ModelFacade;
import Product.Product;

public class ControllerFacade {
	
	private static ControllerFacade instance = null;
	
	private ControllerFacade() {}
	
	public static ControllerFacade getInstance() {
		if (instance == null) {
			instance = new ControllerFacade();
		}
		
		return instance;
	}
	
	public double buy(String productID, int orderedQuantity, LocalDateTime timestamp) {
		
		Product product = ModelFacade.getInstance().startOrder(productID, orderedQuantity, timestamp);
		if (orderedQuantity > product.getMaxQuantity()) {
			ModelFacade.getInstance().rejectOrder();
			throw new IllegalArgumentException();
		}
		
		if (orderedQuantity > product.getAvailableQuantity()) {
			ModelFacade.getInstance().displayPending();
			Restocker.restock(product);
		}
		
		double price = product.calculatePrice();
		ModelFacade.getInstance().depleteProduct(productID, orderedQuantity);
		product.reduceQuantity(orderedQuantity);
		
		if (product.getAvailableQuantity() < product.getMinQuantity()) {
			ModelFacade.getInstance().displayPending();
			Restocker.restock(product);
		}
		
		return price;
		
		
	}

}
