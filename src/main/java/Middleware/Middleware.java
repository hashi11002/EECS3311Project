package Middleware;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import Controller.ControllerFacade;
import Model.Order;
import frontEnd.Server;

public class Middleware {
    private static Queue<Order> orderQueue;
    private static Server Server;
    private static Middleware instance = null;

    private Middleware(Server server) {
    	this.Server = server;
    	this.orderQueue = new LinkedList<Order>();
    }
    
    public static Middleware getInstance(Server server) {
    	if (instance == null) {
    		instance = new Middleware(server) ;
    	}
    	
    	return instance;
    }
    // Method to add an order to the processing queue
    private void placeOrder(Order order) {
        orderQueue.add(order);

    }

//    // Method to process orders in the queue
//    private void processOrders() {
//        while (!orderQueue.isEmpty()) {
//            Order order = orderQueue.poll();
//            processOrder(order);
//        }
//    }

    // Method to process a single order
    public double processOrder(Order order) {
        // Simulate delay of fulfilling an order
    	placeOrder(order);
//        simulateOrderFulfillmentDelay();

        double price = ControllerFacade.getInstance().buy(order.getProductName(), order.getQuantity(), order.getDate());
        return price;
//        Server.displayOrderFinalizedMessage(order, price);
        
    }

    // Method to simulate the delay of fulfilling an order
    private void simulateOrderFulfillmentDelay() {
//        try {
//            Thread.sleep((long) (new Random().nextInt(16) + 30) * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    	try {
			wait(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

//    public static void main(String[] args) {
//        // Instantiate the necessary components
//        Server Server = new Server();
//        Middleware controller = new Middleware(Server);
//
//    }
}

