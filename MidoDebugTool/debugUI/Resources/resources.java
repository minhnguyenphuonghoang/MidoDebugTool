package Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import javax.swing.JOptionPane;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import com.sun.org.apache.xml.internal.security.keys.ContentHandlerAlreadyRegisteredException;

import org.apache.*;




public class resources {
	public final String STAGING_URL = "stg-api.midoplay.com/api";
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
	
		
		
	}
	
	
	
	public String login(String username, String password){
		String result = null;
		String authToken = null;
		String parameters = "emailAddress=" + username + "&" + "password=" + password;  
		
		String response = sendPost("/login", null, parameters);
		System.out.println(response);
		return response;
	}
	
	
	
	public String getService(String authToken){
		String a = sendGet("/service", authToken);
		return a;
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
		HttpGet get = new HttpGet();
		URIBuilder a = new URIBuilder();

		
		return null;
	}
	
	
	private String sendPost(String uri, String authToken, String parameters){
		String url = null;
		if (environment.equals("Staging")){
			url = STAGING_URL + uri;
		} else {
			url = DEMO_URL + uri;
		}
		HttpPost post = null;
		
		
		
		
		
		
		
		return null;
	}

	
}
