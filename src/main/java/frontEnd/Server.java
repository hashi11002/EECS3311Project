package frontEnd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.HashMap;

import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import Controller.ControllerFacade;
import Middleware.Middleware;
import Model.Order;

// The client calls http://localhost:8000/test1?p1=10&p2=20 (e.g. from a Web browser or a Java Client program)
// and gets back as a response "Hello World! P1 was: 10 and p2 was: 20"
// If the client calls http://http://localhost:8000/test2/?p3=1000
// it gets as a response "Hello New Brave World!  p3 was: 1000"

// Note that the server can respond by sending back a Json or XML string
// which is interpreted by the client appropriately as per the logic of the client

public class Server {
	private static Middleware middleware;
	
	public void startServer() throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8002), 0);
		server.createContext("/test1", new MyHandler1());
		server.setExecutor(Executors.newCachedThreadPool());
		this.middleware = Middleware.getInstance(this);
		server.start();
	}

//	static class MyHandler1 implements HttpHandler {
//		public void handle(HttpExchange exchange) throws IOException {
//			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
//			String response = "Hello World! " + "P1 was: " + parms.get("p1") + " and p2 was: " + parms.get("p2");
//			exchange.sendResponseHeaders(200, response.length());
//			OutputStream os = exchange.getResponseBody();
//			os.write(response.getBytes());
//			try {
//				wait(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			response = " FOO";
//			exchange.sendResponseHeaders(200, response.length());
//			OutputStream os2 = exchange.getResponseBody();
//			os2 = exchange.getResponseBody();
//			os2.write(response.getBytes());
//			os.close();
//			os2.close();
//		}
//	}
	
	static class MyHandler1 implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
			Order order = new Order(parms.get("ProductName"), Integer.parseInt(parms.get("ProductQuantity")), LocalDateTime.parse(parms.get("Timestamp")));
			String response;
			
			try {
				System.out.print("New order");
				double price = Server.middleware.processOrder(order);
				response = getOrderFinalizedMessage(parms.get("ProductName"), Integer.parseInt(parms.get("ProductQuantity")), price);
			} catch (IllegalArgumentException e) {
				response = getOrderRejectedMessage();
			}
			
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
//			try {
//				wait(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			response = " FOO";
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os2 = exchange.getResponseBody();
			os2 = exchange.getResponseBody();
			os2.write(response.getBytes());
			os.close();
			os2.close();
		}
	}

	
	public static Map<String, String> queryToMap(String query){

	    Map<String, String> result = new HashMap<String, String>();
	    for (String param : query.split("&")) {
	        String pair[] = param.split("=");
	        if (pair.length>1) {
	            result.put(pair[0], pair[1]);
	        }else{
	            result.put(pair[0], "");
	        }
	    }
	    return result;
	}

	private static String getOrderRejectedMessage() {
		return "Order exceeds the max quantity set for this product and cannot be processed";
	}

    private static String getOrderFinalizedMessage(String ProductName, int OrderQuantity, double price) {
        return "Order is finalized for Product " + ProductName +
                " and Quantity " + OrderQuantity +
                " with total price " + price + "\n";
    }

    
}
