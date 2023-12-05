package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Model.Product.Product;
import Model.Product.Pricing.PricingFactory;
import Model.Product.Restocking.RestockingFactory;
import frontEnd.Starter;

public class LoginBackend {
		private static String url = "jdbc:sqlite:/C:\\Users\\Amin\\git\\EECS3311Project\\ab.db";
	
		public boolean doLogin(String username, String userpass) {
			boolean result = false;
			try {
				Class.forName("org.sqlite.JDBC");
				
				Connection con = DriverManager.getConnection(url);
				String query = "select * from login where username = ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, username);
				
				ResultSet rs = ps.executeQuery();

				if(rs.next()) {
					if (rs.getString("password").equals(userpass)) {
						result = true;
						Starter.main(null);
					}

				}
				rs.close();
				ps.close();
				con.close();			
			} catch(Exception e){
					e.printStackTrace();
					System.out.println("ERROR IN ORDERING");
			}

			return result;
		}

}
