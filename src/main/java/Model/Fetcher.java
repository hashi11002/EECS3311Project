package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Product.Product;
import Product.ProductFactory;

public class Fetcher {
	
	static String url = "jdbc:mysql://localhost:3306/3311ProjectDatabase";
	static String user = "root";
	static String password = "";
	
	public static Product getProductObj(String ProductName) {
		
		
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			String query = "select * from product where itemName = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, ProductName);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				Product product = new Product(
				rs.getString("itemName"),
				rs.getInt("remainingStock"),
				rs.getInt("maxQuantity"),
				rs.getInt("minStockQuantity"),
				PricingFactory.createStrategy(
						rs.getInt("quantityForDiscount"),
						rs.getFloat("quantityDiscountPercent"),
						rs.getFloat("priceForDiscount"),
						rs.getFloat("priceDiscountPercent")),
				RestockFactory.createStrategy(rs.getInt("restockingSchedule"),
							      rs.getInt("maxStockQuantity"),
							      rs.getInt("currentQuantity"),
							      rs.getInt("productsToRestock"))
					
						
				);
				return product;
			}
			
			
			
	}
		catch(Exception e){
			System.out.println("ERROR IN ORDERING");
		}
		return null;
	
		
	}
	
	public static ArrayList<String> getProductNames() {
		/* LET'S ASSUME it gets the following info from some database */
		ArrayList<String> products = new ArrayList<>();
		
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			
			String query = "select * from product";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Product product = new Product(
				rs.getString("itemName"), 
				0,
				0);
				products.add(product.getName());
			}
		}

		catch(Exception e){
			System.out.println("ERROR IN ORDERING");
		}
		return products;
		
		
	}
	
	public static HashMap<String, Integer> getAvailableQuantitiesFor(ArrayList<String> productNames) {
		/* LET'S ASSUME it gets the following info from some database */
		HashMap<String, Integer> productData = new HashMap<String, Integer>();
		for (String e : productNames) {
			Product prod = getProductObj(e);
			productData.put(e, prod.getAvailableQuantity());
		}
		return productData;
	}

}
