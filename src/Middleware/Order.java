package Middleware;

import java.util.Date;
import java.util.HashMap;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 

public class Order {
	public static String getProductName() {
		return productName;
	}

	public static void setProductName(String productName) {
		Order.productName = productName;
	}

	public static int getQuantity() {
		return quantity;
	}

	public static void setQuantity(int quantity) {
		Order.quantity = quantity;
	}

	public static LocalDateTime getDate() {
		return date;
	}

	public static void setDate(LocalDateTime date) {
		Order.date = date;
	}

	private static Order instance = null;

	private static String productName;
	private static int quantity;
	private static LocalDateTime date;
	
	

	

	public static Order getInstance() {
		if (instance == null)
			instance = new Order();

		return instance;
	}

	private Order() {
		//findAvailableProductsAndQuantities();
	}

	public Order findOrder() {

		// Here we query the Product DB and we get the product names or the product IDs
		
		productName = "Product1";
		quantity = 22;
		date = java.time.LocalDateTime.now();
		

		return this;

		// Here we query the Product DB and we get the product names or the product IDs


	}


}
