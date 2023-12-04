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

		try {

			Connection con = DriverManager.getConnection(url, user, password);

			String updateQuery = "UPDATE products SET remainingStock = remainingStock + ? WHERE itemName = ?";
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.setInt(1, restockQuantity);
			ps.setString(2,ProductName);


			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Product stock updated: " + ProductName);
			} else {
				System.out.println("Product not found or no change in stock: " + ProductName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



	public static void depleteProduct(String ProductName, int depleteQuantity) {

		try {

			Connection con = DriverManager.getConnection(url, user, password);


			String updateQuery = "UPDATE products  SET remainingStock = remainingStock - ? WHERE itemName = ?";
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.setInt(1, depleteQuantity);
			ps.setString(2, ProductName);


			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Product stock decreased: " + ProductName);
			} else {
				System.out.println("Product not found or no change in stock: " + ProductName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
