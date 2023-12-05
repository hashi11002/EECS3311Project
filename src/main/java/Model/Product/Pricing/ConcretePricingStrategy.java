package Model.Product.Pricing;

public class ConcretePricingStrategy implements PricingStrategy {
	private int ID;
	private double pricePerItem;
	private int discountQuantity;
	private double quantityDiscount;
	private double discountPrice;
	private double priceDiscount;
	
	public ConcretePricingStrategy(int ID, double pricePerItem, int discountQuantity, double quantityDiscount, double discountPrice, double priceDiscount) {
		this.ID = ID;
		this.priceDiscount= priceDiscount;
		this.pricePerItem = pricePerItem;
		this.discountQuantity = discountQuantity;
		this.discountPrice = discountPrice;
		this.quantityDiscount = quantityDiscount;
	}
	

	@Override
	public double calculateTotalPrice(int quantity) {
		double price = pricePerItem * quantity;
		
		if (quantityDiscount > 0 && quantity >= discountQuantity) {
			price -= price * quantityDiscount;
		}
		
		if (priceDiscount > 0 && price >= discountPrice) {
			price -= price * priceDiscount;
		}
		
		return price;
		
	}
	
}
