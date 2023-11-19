package Middleware;

public class Order {
	public final int orderedQuantity;
	public final String productName;
	
	public Order(String name, int quantity) {
		orderedQuantity = quantity;
		productName = name;
	}

}
