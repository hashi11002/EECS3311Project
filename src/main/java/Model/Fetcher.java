package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Product.Product;
import Model.Product.Pricing.PricingFactory;
import Model.Product.Restocking.RestockingFactory;

public class Fetcher {
	
	static String url = "jdbc:sqlite:/C:\\Users\\Amin\\git\\EECS3311Project\\ab.db";
	static String user = "root";
	static String password = "";
	
	public static Product getProductObj(String ProductName) {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection(url);
			String query = "select * from products where itemName = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, ProductName);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				Product product = new Product(
				rs.getString("itemName"),
				rs.getInt("remainingStock"),
				rs.getInt("maxCapacity"),
				rs.getInt("minStockQuantity"),
				PricingFactory.createStrategy(rs.getInt("pricingID")),
				RestockingFactory.createStrategy(rs.getInt("restockID"))
				);
				rs.close();
				ps.close();
				con.close();
				return product;
			}
			rs.close();
			ps.close();
			con.close();
			
		} catch(Exception e){
				e.printStackTrace();
				System.out.println("ERROR IN ORDERING");
		}
		return null;
	
		
	}
	
	public static ArrayList<String> getProductNames() {
		/* LET'S ASSUME it gets the following info from some database */
		ArrayList<String> products = new ArrayList<>();
//		products.add("ProductA");
//		products.add("ProductB");
//		products.add("ProductC");
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection(url);
			
			String query = "select * from products";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
//			System.out.println(rs.getString("itemName"));
			while(rs.next()) {
//				System.out.println(rs.getString("itemName"));
//				Product product = new Product(
//				rs.getString("itemName"), 
//				0,
//				0);
				products.add(rs.getString("itemName"));
			}
			rs.close();
			statement.close();
			con.close();
		}

		catch(Exception e){
			e.printStackTrace();
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
