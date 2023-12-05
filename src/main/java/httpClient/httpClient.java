package httpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;




public class httpClient {
	
	public String doThehttpCalltest1(String p1, String p2, String p3) {

		String urlString = String.format(
				"http://localhost:8002/test1?ProductName=%s&ProductQuantity=%s&Timestamp=%s", p1, p2, p3);
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
			
				return inline;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong with the API call.");
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		httpClient aCaller = new httpClient();
		System.out.println("The server responded : " + aCaller.doThehttpCalltest1(args[0], args[1], args[2]));
//		PlacingOrderGui.displayResponse(aCaller.doThehttpCalltest1(args[0], args[1], args[2]);

	}

}