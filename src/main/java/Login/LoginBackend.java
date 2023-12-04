package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import frontEnd.Starter;

public class LoginBackend {

	
		public boolean doLogin(String pass, String userpass) {
			boolean result = false;

				if(pass.equals(userpass)) {
					result = true;
				}else {
					result = false;
				}
			Starter.main(null);
			return result;
		}

}
