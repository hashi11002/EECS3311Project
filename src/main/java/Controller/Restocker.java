package Controller;

import Model.ModelFacade;
import Product.Product;

public class Restocker {

	public static void restock(Product obj) {
		while (obj.getAvailableQuantity() < obj.getMaxQuantity()) {
			int restockQuantity = (obj.getRestockAmount() <= obj.getMaxQuantity() - obj.getAvailableQuantity()) ? obj.getRestockAmount() : obj.getMaxQuantity() - obj.getAvailableQuantity();
			ModelFacade.getInstance().restock(obj.getName(), restockQuantity);
			obj.restock(restockQuantity);
		}
		
	}

}