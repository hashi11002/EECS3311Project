package Middleware;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import Controller.ControllerFacade;
import frontEnd.Server;

public class Middleware {
    private Queue<Order> orderQueue;
    private Server Server;

    public Middleware(Server server) {
    	this.Server = server;
    	this.orderQueue = new LinkedList<Order>();
    }
    // Method to add an order to the processing queue
    public void placeOrder(Order order) {
        orderQueue.add(order);
        processOrders();
    }

    // Method to process orders in the queue
    private void processOrders() {
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();
            processOrder(order);
        }
    }

    // Method to process a single order
    private void processOrder(Order order) {
        // Simulate delay of fulfilling an order
        simulateOrderFulfillmentDelay();

        double price = ControllerFacade.getInstance().buy(order.getProductName(), order.getQuantity(), order.getDate());
        
        Server.displayOrderFinalizedMessage(order, price);
        
    }

    // Method to simulate the delay of fulfilling an order
    private void simulateOrderFulfillmentDelay() {
        try {
            Thread.sleep((long) (new Random().nextInt(16) + 30) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Instantiate the necessary components
        Server Server = new Server();
        Middleware controller = new Middleware(Server);

    }
}

