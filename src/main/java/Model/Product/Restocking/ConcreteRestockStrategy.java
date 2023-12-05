package Model.Product.Restocking;

public class ConcreteRestockStrategy implements RestockStrategy {
	private int restockAmount;
	
	public ConcreteRestockStrategy(int restockAmount) {
		this.restockAmount = restockAmount;
	}

	@Override
	public int restock() {
		return restockAmount;
	}

}
