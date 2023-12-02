package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginProxy implements ILogin{
	String url = "jdbc:mysql://localhost:3306/Users3311";
	String user = "root";
	String password = "";
	LoginBackend authenticator;
	String userpass;

	@Override
	public boolean doLogin(String username, String pass) {
		
		authenticator = new LoginBackend();

		try {
			Connection con = DriverManager.getConnection(url, user, password);



			String query = "select login.password from login where login.Username = ?;";



			PreparedStatement  preparedstatement = con.prepareStatement(query);
			preparedstatement.setString(1,username);
			ResultSet rs = preparedstatement.executeQuery();
			rs.next();
			 userpass = rs.getString(1);
			}catch ( SQLException e ) {
				System.out.println("Error");
			}
		
		return authenticator.doLogin(pass, userpass);

}
}