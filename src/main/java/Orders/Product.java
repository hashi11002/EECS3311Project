package Orders;

import java.util.List;

public class Product {
	
	private int id;
	private String itemName;
	private int MaxCapacity;
	private int remainingStock;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getitemName() {
		return itemName;
	}
	public void setitemNname(String itemname) {
		itemName = itemname;
	}
	public int getMaxCapacity() {
		return MaxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		MaxCapacity = maxCapacity;
	}
	public int getRemainingStock() {
		return remainingStock;
	}
	public void setRemainingStock(int remainingStock) {
		this.remainingStock = remainingStock;
	}
	
}

//List<Product> productList = selection.getProducts();
//String[] products = new String[productList.size()];
//for(int i = 0; i < productList.size(); i++) {
//	products[i] = productList.get(i).getitemName();
//}
