package Product;

import java.util.ArrayList;
import java.util.List;

public interface Observer {
	void update(Product product);

	class Observable {
	    private List<Observer> observers = new ArrayList<>();

	    void addObserver(Observer observer) {
	        observers.add(observer);
	    }

	    void notifyObservers(Product product) {
	        for (Observer observer : observers) {
	            observer.update(product);
	        }
	    }
	}
}
