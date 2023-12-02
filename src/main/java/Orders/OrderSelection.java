package Orders;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderSelection {



	String url = "jdbc:mysql://localhost:3306/3311ProjectDatabase";
	String user = "root";
	String password = "";

//	public OrderSelection(Product product , int qty) {
//		this.product = new Product();
//		this.qty = qty;
//	}

	public List<Product> getProducts(){
		List<Product> products = new ArrayList<>();
		Product product = new Product();
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			
			String query = "select * from product";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				product.setId(rs.getInt("ID"));
				product.setitemNname(rs.getString("itemName"));
				product.setMaxCapacity(rs.getInt("maxQuantity"));
				product.setRemainingStock(rs.getInt("remainingStock"));
				products.add(product);
			}
		}

		catch(Exception e){
			System.out.println("ERROR IN ORDERING");
		}
		return products;
	}
	
	public List<String> getProductNames(){
		List<String> products = new ArrayList<>();
		Product product = new Product();
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			
			String query = "select * from product";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {

				product.setitemNname(rs.getString("itemName"));

				products.add(product.getitemName());
			}
		}

		catch(Exception e){
			System.out.println("ERROR IN ORDERING");
		}
		return products;
	}
	
	public Product getProductByName(String itemName) {
		Product product = new Product();
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			String query = "select * from product where itemName = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, itemName);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				product.setId(rs.getInt("ID"));
				product.setitemNname(rs.getString("itemName"));
				product.setMaxCapacity(rs.getInt("maxQuantity"));
				product.setRemainingStock(rs.getInt("remainingStock"));
			}
			
			
			
	}
		catch(Exception e){
			System.out.println("ERROR IN ORDERING");
		}
		return product;
	}
	
	
	
}