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
		
		Product obj = ModelFacade.getInstance().startOrder(productID, orderedQuantity, timestamp);
		if (orderedQuantity > obj.getMaxQuantity()) {
			ModelFacade.getInstance().rejectOrder();
			throw new IllegalArgumentException();
		}
		
		if (orderedQuantity > obj.getAvailableQuantity()) {
			ModelFacade.getInstance().displayPending();
			Restocker.restock(obj);
		}
		
		double price = obj.calculatePrice();
		ModelFacade.getInstance().depleteProduct(productID, orderedQuantity);
		obj.reduceQuantity(orderedQuantity);
		
		if (obj.getAvailableQuantity() < obj.getMinQuantity()) {
			ModelFacade.getInstance().displayPending();
			Restocker.restock(obj);
		}
		
		return price;
		
		
	}

}
