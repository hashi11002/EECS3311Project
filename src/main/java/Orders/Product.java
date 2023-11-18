package Orders;

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
