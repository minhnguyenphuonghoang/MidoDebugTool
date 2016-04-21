package Resources;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

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
	
	
	public boolean inviteToGroup(String invitee, String message){
		if (invitee == null || invitee.equals("")){
			showErrorMessage("ERROR", "Invitee is empty");
			return false;
		}
		
		message = message.replace("\n", "\\n");
		
		String authToken = checkAuthenticationToken();
		if (authToken==null)
			return false;
		
		// create new group
		
		String result = createNewGroup(authToken);
		if (result==null)
			return false;
		
		// send group invitation
		return inviteMemberToGroup(invitee, authToken, result, message);
	}
	

	public boolean giftTicketsToSomeone(String receiver, Integer numberOfTickets, Boolean isWinning, String message){
		if (receiver == null || receiver.equals("")){
			return false;
		} 
		
		String authToken = checkAuthenticationToken();
		if (authToken==null)
			return false;
		String currentDrawID = getCurrentDrawStatus();
		if (currentDrawID==null)
			return false;
		String[] lines = currentDrawID.split("\n");
		currentDrawID = lines[1];
		currentDrawID = currentDrawID.replace("DrawID: ", "");
		
		
		double subTotalAmount = numberOfTickets;
		double serviceFeeAmount = 0;
		if (numberOfTickets < 8) 
			serviceFeeAmount = numberOfTickets * 0.5 + 1;
		else
			serviceFeeAmount = numberOfTickets * 0.5;
		double totalAmount = subTotalAmount + serviceFeeAmount;
		String preParameters = "{\"order\":{"
								+ "\"subTotalAmount\":" + subTotalAmount 
								+ ",\"serviceFeeAmount\":\"" + serviceFeeAmount
								+ "\",\"totalAmount\":\"" + totalAmount
								+ "\",\"salesTaxAmount\":0,\"description\":\"Order to buy tickets.\","
								+ "\"tickets\": [";
		
		String endParameters = "]},\"midoWallet\":{\"autoLoadAmount\":0,\"autoLoadTriggerAmount\":5,\"enableAutoLoad\":\"false\","
								+ "\"loadAmount\":" + totalAmount
								+ "},\"creditCard\":{\"paymentName\":\"VISA-1111\"}}";
		
		String parameters = "";
		message = message.replace("\n", "\\n");
		if (isWinning==true)
			parameters = getGiftTicketJson(currentDrawID, "1 6 7 8 9 6", receiver, message);
		else
			parameters = getGiftTicketJson(currentDrawID, "5 6 7 8 9 7", receiver, message);
		
		numberOfTickets -= 1;

		for (int i=0; i<numberOfTickets; i++){
			String aTicket = randomTicket();
			parameters += "," + getGiftTicketJson(currentDrawID, aTicket, receiver, message);
		}
		System.out.println(parameters);
		parameters = preParameters + parameters + endParameters;
		System.out.println(parameters);
		String response = sendPost("/orders", authToken, parameters);
		if (response == null){
			this.showErrorMessage("ERROR", "Cannot send a gift at this time.\nPlease try again later.");
			return false;
		}
		return true;
		
	}

	
	
	
	public boolean closeMidoDraw(String winning_numbers){

		String temp[] = winning_numbers.split(" ");
		if (temp.length != 6){
			this.showErrorMessage("Invalid winning numbers", 
					"Your winning numbers: " + winning_numbers + "is invalid!" +
					"\nAn example winning numbers: 01 02 03 04 05 06.");
			return false;
		}
		
		
		
		String authToken = checkAuthenticationToken();
		
		
		
		String parameters = "{\""
				+ "winningNumbers\":\"" + winning_numbers
				+"\"}";
		
		String response = sendPost("/games/mido-million/draws/current", authToken, parameters);
		if (response == null){
			this.showErrorMessage("ERROR", "Cannot close or complete Mido Millions draw at this time.\nPlease try again later.");
			return false;
		}
		return true;
		
	}
	
	public String getCurrentDrawStatus(){
		String authToken = checkAuthenticationToken();
		
		String response = sendGet("/draws", authToken);
		if (response == null){
			this.showErrorMessage("ERROR", "Cannot get current Mido Millions draw information .\nPlease try again later.");
			return null;
		}
		
		JSONArray arr = new JSONArray(response);
		JSONObject obj = null;
		for (int i=0; i<arr.length(); i++){
			if (arr.isNull(i))
				continue;
			obj = arr.getJSONObject(i);

			if (obj.getString("gameName").equals("Mido Millions")){
				if (obj.getString("state").equals("CURRENT_DRAW") || obj.getString("state").equals("CURRENT_PENDING_DRAW")){
					return "GameID: " + obj.getInt("gameId")
					+ "\nDrawID: " + obj.getString("drawId")
					+ "\nState: " + obj.getString("state")
					+ "\nGameDrawID: " + obj.getLong("gameDrawId")
					+ "\nDrawDate: " + obj.getString("drawDate")
					+ "\nExpectedCloseDate: " + obj.getString("expectedCloseDate");
				}
			}
		}
		return null;
	}
	
	public String login(String username, String password){
		
		String parameters = "{\""
				+ "emailAddress\":\"" + username + "\",\""
				+ "password\":\"" + password 
				+"\"}";
		
		String response = sendPost("/login", null, parameters);
		if (response == null) {
//			this.showErrorMessage("ERROR", "Can't login with provided account: " + username);
			return null;
		}
		
		return response;
	}
	
	public void getService(){
		
		String authToken = checkAuthenticationToken();
		
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
	private String createNewGroup(String authToken){
		String parameters = "{\"groupName\":\"" + "Automation Invitation\","
							+ "\"buyIn\":" + 10 + ","
							+ "\"groupType\":" + 2 + ","
							+ "\"gameIds\":[" + 3 + "]}";
		
		String result = sendPost("/groups", authToken, parameters);
		
		if (result==null)
			return null;
		JSONObject obj = new JSONObject(result);
		return obj.getString("groupId");

	}
	
	private boolean inviteMemberToGroup(String invitee, String authToken, String groupId, String message){
		String parameters = "[{\"contact\":{\"emailAddresses\":[{" 
							+ "\"label\":\"direct\","
							+ "\"selected\":true," 
							+ "\"value\":\"" + invitee + "\"}]," 
							+ "\"firstName\":\"First Name\"," 
							+ "\"lastName\":\"Last Name\"," 
							+ "\"name\":\"First Name Last Name\"}," 
							+ "\"groupId\":\"" + groupId + "\"," 
							+ "\"message\":\"" + message + "\"," 
							+ "\"referenceId\":\"90C6822B-6AA6-485E-66BB-01386A37762F\"}]";

		String result = sendPost("/groups/" + groupId +"/members", authToken, parameters);
		
		if (result==null)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	private String getGiftTicketJson(String drawID, String ticket, String receiver, String message){
		return "{\"numberType\":\"QUICKPICK\","
				+ "\"drawId\":\"" + drawID + "\"," 
				+ "\"numbers\":\"" + ticket + "\","
				+ "\"gift\": {"
				+ "\"referenceId\": \"9091264803731234498\","
				+ "\"recipientContact\": {"
				+ "\"firstName\": \"First Name\"," 
				+ "\"lastName\": \"Last Name\"," 
				+ "\"emailAddresses\": [" 
				+ "{\"label\": \"direct\","
				+ "\"value\": \""+ receiver +"\"," 
				+ "\"selected\": \"true\"}],"
				+ "\"name\": \"First Name Last Name\"},"
				+ "\"recipientMessage\": \"" + message + "\"}}";
	}
	
	
	private String randomTicket(){
		String aTicket = "";

		ArrayList<Integer> result = new ArrayList<>();
        Random rand = new Random();
        int randomNum = 0;
        
		//random number for normal balls
        while (result.size() < 5) {
            randomNum = rand.nextInt(24) + 1;
            if (!result.contains((Integer) randomNum)) {
                result.add(randomNum);
            }
        }
		
		//random number for special balls
        while (randomNum == 6) {
        	randomNum = rand.nextInt(10) + 1;
		}
		
		aTicket = result.get(0) + " " 
				+ result.get(1) + " "
				+ result.get(2) + " "
				+ result.get(3) + " "
				+ result.get(4) + " "
				+ randomNum;
		System.out.println(aTicket);
		return aTicket;
	}
	
	private ArrayList<String> randomTickets(Integer numberOfTickets){
		ArrayList<String> tickets = new ArrayList();
		for (int i=0; i<numberOfTickets; i++){
			tickets.add(randomTicket());
		}
		return tickets;
	}
	
	public String checkAuthenticationToken(){
		String authToken = null;
		
		if (environment.equals(STAGING) && Variable.authentication_token_staging == null){
			String response = this.login(TEST_USERNAME, TEST_PASSWORD);
			if (response == null)
				return null;
			
			JSONObject obj = new JSONObject(response);

			authToken = obj.getString("authenticationInfo");
			Variable.authentication_token_staging = authToken;
			
		} else if (environment.equals(DEMO) && Variable.authentication_token_demo == null){ 
			String response = this.login(TEST_USERNAME, TEST_PASSWORD);
			if (response == null)
				return null;
			
			JSONObject obj = new JSONObject(response);

			authToken = obj.getString("authenticationInfo");
			Variable.authentication_token_demo = authToken;
		}
		else{
			if (environment.equals(STAGING))
				return Variable.authentication_token_staging;
			else
				return Variable.authentication_token_demo;
		}
		return authToken;
	}
	
	
	
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
	        System.out.println("GET Response Message: " + con.getResponseMessage());

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
	        System.out.println("POST Response Message: " + con.getResponseMessage());
	        System.out.println("Post Body: " + parameters);

	        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_ACCEPTED || responseCode == HttpURLConnection.HTTP_CREATED) { //success
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
	
	
	public Boolean checkVaribles(){
		System.out.println("loop check variable");
		if (Variable.authentication_token_staging == null || Variable.authentication_token_demo ==null)
			return false;
		return true;
		
	}
	
	
	
}
