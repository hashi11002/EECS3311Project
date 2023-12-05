package Model.Product.Pricing;

import java.util.HashMap;

public class PricingFactory {
	
	private static PricingFactory instance = null;
	private static HashMap<Integer, PricingStrategy> repo;
	
	private PricingFactory() {
		repo = new HashMap<Integer, PricingStrategy>();
		repo.put(1, new ConcretePricingStrategy(1, 1.5, 800, 0.05, 0, 0));
		repo.put(2, new ConcretePricingStrategy(2, 1.25, 0, 0, 812.5, 0.1));
		repo.put(3, new ConcretePricingStrategy(3, 1, 450, 0.025, 375.5, 0.03));
	}
	
	public static PricingFactory getInstance() {
		if (instance == null) {
			instance = new PricingFactory();
		}
		
		return instance;
	}
	
	public static PricingStrategy createStrategy(int ID) {
		if (instance == null) {
			getInstance();
		}
		
		if (repo.containsKey(ID)) {
			return repo.get(ID);
		}
		
		return null;
	}

}
