package Product;

import java.util.Timer;
import java.util.TimerTask;

public class RestockFactory implements Observer{

	private Observable observableSubject;
    private Timer restockTimer;

    void RestockStrategy(Observable observableSubject) {
        this.observableSubject = observableSubject;
        observableSubject.addObserver(this);
        restockTimer = new Timer();
    }

    public void update(Product product) {
        if (product != null) {
            // Implement restocking logic here
            System.out.println("Restocking operation initiated for Product " + product.getName());
            performRestocking(product);
        }
    }
    private void performRestocking(final Product product) {
        // Simulate restocking based on the restocking schedule from the database
    final int restockingSchedule;
    int maxStockQuantity = product.getMaxQuantity();
    int currentQuantity = product.getAvailableQuantity();
    final int[] productsToRestock = { maxStockQuantity - currentQuantity };

    if (productsToRestock[0] > 0) {
        restockTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                int restockAmount = Math.min(restockingSchedule, productsToRestock[0]);
                product.restock(restockAmount);
                productsToRestock[0] -= restockAmount;

                System.out.println("Restocking operation for Product " + product.getName() +
                        " - Restocked: " + restockAmount + " units, Remaining: " + productsToRestock[0] + " units");

                if (productsToRestock[0] <= 0) {
                    restockTimer.cancel();
                    System.out.println("Restocking operation completed for Product " + product.getName());
                    observableSubject.notifyObservers(product);
                }
            }
        }, 0, 1000); // Delayed start (0 milliseconds), repeat every 1000 milliseconds (1 second)
    }
}
}
