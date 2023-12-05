package Model;

import java.util.Date;
import java.util.HashMap;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 

public class Order {
	private final String productName;
	private final int quantity;
	private final LocalDateTime date;

	public Order(String productName, int quantity, LocalDateTime date) {
		this.date = date;
		this.productName = productName;
		this.quantity = quantity;
	}


	public String getProductName() {
		return productName;
	}
//
//	public void setProductName(String productName) {
//		Order.productName = productName;
//	}
//
	public int getQuantity() {
		return quantity;
	}
//
//	public void setQuantity(int quantity) {
//		Order.quantity = quantity;
//	}
//
	public LocalDateTime getDate() {
		return date;
	}
//
//	public void setDate(LocalDateTime date) {
//		Order.date = date;
//	}


//
//	public Order findOrder() {
//
//		// Here we query the Product DB and we get the product names or the product IDs
//		
//		productName = "Product1";
//		quantity = 22;
//		date = java.time.LocalDateTime.now();
//		
//
//		return this;
//
//		// Here we query the Product DB and we get the product names or the product IDs
//
//
//	}


}
