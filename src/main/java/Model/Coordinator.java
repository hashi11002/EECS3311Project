package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Viewer.BarViewer;
import Viewer.TextViewer;
import Viewer.Viewer;
import warehouseServerVisualizer.gui.MainServerUI;
import warehouseServerVisualizer.utils.AvailableProductList;
import warehouseServerVisualizer.utils.LastOrder;

public class Coordinator {
	
	private static Coordinator instance = null;
	private static List<Viewer> observers = new ArrayList<Viewer>();
	
	private Coordinator() {}
	
	
	public static Coordinator getInstance() {
		if (instance == null) {
			instance = new Coordinator();
		}
		
		return instance;
	}
	
	public void addViewer(Viewer viewer) {
		observers.add(viewer);
	}
	
	public List<Viewer> getViewers() {
		return observers;
	}
	
	public void startOrder(HashMap<String, Integer> productData, String productName, int orderQuantity, LocalDateTime timestamp) {
		LastOrder order = LastOrder.getInstance();
		order.setDate(timestamp);
		order.setProductName(productName);
		order.setQuantity(orderQuantity);
		
		AvailableProductList productList = AvailableProductList.getInstance();
		productList.setAvailableProductsAndQuantities(productData);
		
		MainServerUI ui = MainServerUI.getInstance();
		
		addViewer(TextViewer.getInstance());
		addViewer(BarViewer.getInstance());
		
		notifyStartOrder();
	}
	
	private void notifyStartOrder() {
		for (Viewer e : observers) {
			e.displayOrder();
		}
	}
	
	private void notifyViewers() {
		AvailableProductList productList = AvailableProductList.getInstance();
		productList.setAvailableProductsAndQuantities(Fetcher.getAvailableQuantitiesFor(Fetcher.getProductNames()));
		for (Viewer e : observers) {
			e.update();
		}
	}
	
	public void displayPending() {
		TextViewer.getInstance().displayPending();
	}
	
	public void restockBegun() {
		TextViewer.getInstance().displayRestockBegun();
	}
	
	public void restockEnd() {
		TextViewer.getInstance().displayRestockEnded();
		// After the restocking ended update the viewers with the new quantities.
		notifyViewers();
	}
	
	public void rejectOrder() {
		TextViewer.getInstance().displayRejected();
	}
	
	public void productDepleted() {
		notifyViewers();
	}
}
