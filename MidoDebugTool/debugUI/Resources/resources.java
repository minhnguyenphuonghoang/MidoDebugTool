package Resources;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import org.json.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import Resources.Variable;





public class resources {
	public final String STAGING_URL = "https://stg-api.midoplay.com/api";
	public final String DEMO_URL = "https://demo-api.midoplay.com/api";
	public final String STAGING = "Staging";
	public final String DEMO = "Demo";
	
	
	public final String TEST_USERNAME = "midodebugtool@yopmail.com";
	public final String TEST_PASSWORD = "qwerty";
	
	public String baseURL = null;
	public String environment = null;
	
	public void showErrorMessage(String title, String errorMessage){
		JOptionPane.showMessageDialog(null, errorMessage, title, JOptionPane.ERROR_MESSAGE);
	}
	

	public void closeMidoDraw(String winning_numbers){

		String temp[] = winning_numbers.split(" ");
		if (temp.length != 6){
			this.showErrorMessage("Invalid winning numbers", 
					"Your winning numbers: " + winning_numbers + "is invalid!" +
					"\nAn example winning numbers: 01 02 03 04 05 06.");
			return;
		}
		
		
		
		String authToken = null;
		if (Variable.authentication_token == null){
		
			String response = this.login(TEST_USERNAME, TEST_PASSWORD);
			if (response == null)
				return;
			
			
			JSONObject obj = new JSONObject(response);
	
			authToken = obj.getString("authenticationInfo");
			Variable.authentication_token = authToken;
		}
		else 
			authToken = Variable.authentication_token;

		
		String parameters = "{\""
				+ "winningNumbers\":\"" + winning_numbers
				+"\"}";
		
		String response = sendPost("/games/mido-million/draws/current", authToken, parameters);
		if (response == null){
			this.showErrorMessage("ERROR", "Cannot close or complete Mido Millions draw at this time.\nPlease try again later.");
			return;
		}
		
	}
	
	public String login(String username, String password){
		
		String parameters = "{\""
				+ "emailAddress\":\"" + username + "\",\""
				+ "password\":\"" + password 
				+"\"}";
		
		String response = sendPost("/login", null, parameters);
		if (response == null) {
			this.showErrorMessage("ERROR", "Can't login with provided account: " + username);
			return null;
		}
		
		return response;
	}
	
	public void getService(){
		

		String authToken = null;
		if (Variable.authentication_token == null){
			String response = this.login(TEST_USERNAME, TEST_PASSWORD);
			if (response == null) 
				return;
			
			
			JSONObject obj = new JSONObject(response);
	
			authToken = obj.getString("authenticationInfo");
			Variable.authentication_token = authToken;

		}
		else
			authToken = Variable.authentication_token;
		

		
		
		
		String response = sendGet("/service", authToken);
		if (response == null){
			this.showErrorMessage("ERROR", "Can't get current server's code information at this time");
			return;
		}
		String version = null;
		String releaseDate = null;
		
		JSONObject obj = new JSONObject(response);
		version = obj.getString("applicationVersion");
		releaseDate = obj.getString("applicationBuildDate");
		JOptionPane.showMessageDialog(null, 
				"Version: " + version + 
				"\nRelease date: " + releaseDate,
				"Server version in " + environment + " environment",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	/*
		private methods
	*/

	
	private String sendGet(String uri, String authToken){
		
		String url = null;
		
		if (environment.equals(STAGING)){
			url = STAGING_URL + uri;
		} else {
			url = DEMO_URL + uri;
		}
		
        URL obj;
		try {
			obj = new URL(url);
		
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        con.setRequestMethod("GET");
	        con.setConnectTimeout(5000);
        	con.setRequestProperty("Authorization", authToken);
	        int responseCode = con.getResponseCode();
	        System.out.println("GET Response Code: " + responseCode);
	        if (responseCode == HttpURLConnection.HTTP_OK) { // success
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    con.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	 
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
	 
	            // print result
	            System.out.println(response.toString());
	            return response.toString();
	        } else {
	            System.out.println("GET request not worked");
	    		return null;
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
    }
	
	
	
	private String sendPost(String uri, String authToken, String parameters){
		String url = null;
		if (environment.equals(STAGING)){
			url = STAGING_URL + uri;
		} else {
			url = DEMO_URL + uri;
		}
		
		
        URL obj;
		try {
			obj = new URL(url);
		
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        con.setRequestMethod("POST");
	        con.setConnectTimeout(5000);
	        con.setRequestProperty("Content-Type", "application/json");
	        if (authToken != null)
	        	con.setRequestProperty("Authorization", authToken);
	 
	        // For POST only - START
	        con.setDoOutput(true);
	        OutputStream os = con.getOutputStream();
	        os.write(parameters.getBytes("UTF-8"));
	        os.flush();
	        os.close();
	        // For POST only - END
	 
	        int responseCode = con.getResponseCode();
	        System.out.println("POST Response Code: " + responseCode);
	        

	        if (responseCode == HttpURLConnection.HTTP_OK) { //success
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    con.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	 
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
	 
	            // print result
	            System.out.println(response.toString());
	            return response.toString();
	        } else {
	            System.out.println("POST request not worked");
	        }
	        
	        return null;
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

    }
	
	
	
	
	
	
}
