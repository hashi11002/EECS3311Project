package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Updater {
	
	static String url = "jdbc:mysql://localhost:3306/3311ProjectDatabase";
	static String user = "root";
	static String password = "";
	
	public static void updateProduct(String ProductName, int restockQuantity) {
		/* TO BE IMPLEMENTED */
			
	        try {
	           
	        	Connection con = DriverManager.getConnection(url, user, password);
				String query = "select * from product where itemName = ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, ProductName);
				ResultSet rs = ps.executeQuery();
				
	            if (rs.next()) {
	
	                int currentStock = rs.getInt("remainingStock");
	                int newStock = currentStock + restockQuantity;

	           
	                String updateQuery = "UPDATE product SET remainingStock = ? WHERE productID = ?";
	                ps = con.prepareStatement(updateQuery);
	                ps.setInt(1, newStock);
	                ps.setString(2, ProductName);

	                
	                ps.executeUpdate();
	                System.out.println("Product updated: " +  ProductName);
	            } else {
	                System.out.println("Product not found: " +  ProductName);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	         
	           
	        }
	    
	
	
	public static void depleteProduct(String ProductID, int depleteQuantity) {
		/* TO BE IMPLEMENTED */
	}

}
