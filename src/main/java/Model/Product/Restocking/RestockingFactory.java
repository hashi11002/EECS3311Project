package Model.Product.Restocking;

import java.util.HashMap;

public class RestockingFactory {
	
	private static RestockingFactory instance = null;
	private static HashMap<Integer, RestockStrategy> repo;
	
	private RestockingFactory() {
		repo = new HashMap<Integer, RestockStrategy>();

	}
	
	public static RestockingFactory getInstance() {
		if (instance == null) {
			instance = new RestockingFactory();
		}
		
		return instance;
	}
	
	public static RestockStrategy createStrategy(int ID) {
		if (instance == null) {
			getInstance();
		}
		
		if (!repo.containsKey(ID)) {
			RestockStrategy newStrat = new ConcreteRestockStrategy(ID);
			repo.put(ID, newStrat);
		}
		
		
		return repo.get(ID);
	}

}
