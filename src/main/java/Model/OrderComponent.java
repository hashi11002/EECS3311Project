package Model;

import java.time.LocalDateTime;

import Viewer.BarViewer;
import Viewer.TextViewer;
import Viewer.Viewer;
import warehouseServerVisualizer.gui.MainServerUI;
import warehouseServerVisualizer.utils.AvailableProductList;
import warehouseServerVisualizer.utils.LastOrder;

public class OrderComponent implements Viewer {

    private String productName;
    private int quantity;
    private boolean isPending;

    // Constructor
    public OrderComponent(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
        this.isPending = false;
    }

    // Getter and Setter methods
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    
    public void markAsPending() {
        this.isPending = true;
    }

    public void markAsNotPending() {
        this.isPending = false;
    }

    

    @Override
    public void displayOrder() {
        System.out.println("Order Details: Product - " + productName + ", Quantity - " + quantity);
        if (isPending) {
            System.out.println("Order is pending – order exceeds available quantity");
        }
        
    }

    @Override
    public void update() {
        System.out.println("Order Component Updated");
    }
}

