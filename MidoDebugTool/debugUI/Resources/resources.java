package Resources;
import java.awt.image.ComponentColorModel;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;




public class resources {
	public final String STAGING_URL = "https://stg-api.midoplay.com/api";
	public final String DEMO_URL = "https://demo-api.midoplay.com/api";
	public final String STAGING = "Staging";
	public final String DEMO = "Demo";
	
	
	public String baseURL = null;
	public String environment = null;
	
	public void showErrorMessage(String title, String errorMessage){
		JOptionPane.showMessageDialog(null, errorMessage, title, JOptionPane.ERROR_MESSAGE);
		
	}
	
	
	
	
	public void closeDraw(String environment){
		URL midoURL = null;
		HttpURLConnection connection = null;
	
		
		try {
			if (environment == null){
				showErrorMessage("Error", "Environment is null!");
				return;
			}
			if (environment.equals(STAGING)){
				midoURL = new URL(STAGING_URL);
			}
			else {
				midoURL = new URL(DEMO_URL);
			}
			midoURL = new URL("https://stg-api.midoplay.com/api/service");
			connection = (HttpURLConnection)midoURL.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type", "application/json");
		    connection.setRequestProperty("Authorization", "Bearer MTQ2MDUzNjYwNDQxOTpiMTM2Y2Y0YzAzZWI1NmRlOGU4MDJhOWFiZWVhMmRiNjU3NGU4MDRkMTA3YWE2MmEyODBlN2ExNmY0ZTNmMDY2OlVTRVJfTE9HSU46Om10NUB5b3BtYWlsLmNvbTo6X186Ol9fOjoxMTUuNzkuNTUuMTc3OjoyODU0YWRmNy05OWM5LTQyNTEtOWU2Yy0xNGJiOTRkYWZlNDQ6YzI0ODQ0YzU1NjdkMjU5ZWRmODBmMWJmYzQyNDk0YmZjYmFkN2Y3Zjc0NjYwNTJhMDRkYTQxNDI3ZjE4OWQ0ODZjMTk5NzVmYzAwMDQ3NDcyNDQ0MWM3ZTg1ZmFhNjRkMmE4MzI2ODhiMmViNjNmYjVjNzYxOTA1YzUyMWFhMjg=");
		    
		    //send request
		    
		    int responseCode = connection.getResponseCode();
		    
		    
		    
			
		    //Get Response  
	        InputStream is = connection.getInputStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	        StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
	        String line;
	        while((line = rd.readLine()) != null) {
	          response.append(line);
	          response.append('\r');
	        }
	        rd.close();
	        System.out.print(response.toString());
			
			return;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connection.disconnect();
			}
		}
		
		
		showErrorMessage("", "");
	}
	
	
	
	public String[] login(String username, String password){
		String result = null;
		String authToken = null;
		
		
		
		
		
		
		
		
		showErrorMessage("ERROR", "this is an error message");
		return new String[]{result, authToken};
	}
	
	
	
	
	
	
	
	
	public void sendGet(String uri, String authToken){
		String url = null;
		
		if (environment.equals("Staging")){
			url = STAGING_URL;
		} else {
			url = DEMO_URL;
		}

		try {
			URL obj = new URL(url);
			HttpURLConnection con;
			con = (HttpURLConnection) obj.openConnection();
		

			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", authToken);
		    
			//add request header
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			//print result
			System.out.println(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
