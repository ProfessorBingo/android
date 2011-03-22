package com.profbingo.android;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;

/**
 * Utility class that provides multiple network communication methods
 * 
 * @author ericstokes
 * 
 */
public class NetUtil {

	/**
	 * Returns SHA hashed string
	 * @param str raw string
	 * @return
	 */
	public static String hashStringSHA(String str) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "HASH ERROR";
	}
	
	
	/**
	 * Get the return data from a specific HTTP connection.
	 * 
	 * @param url The url to request data from.
	 * @return The resulting string from the request.
	 */
	public static String getHTTPData(String url) {
		URLConnection connection;
		String httpResult = "";
		try {
			connection = new URL(url).openConnection();
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			StringBuffer buffer = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = inputStream.read(b)) != -1;) {
				buffer.append(new String(b, 0, n));
			}
			httpResult = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpResult;
	}
	
	
	public static String postJsonData(JSONObject data, String url) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpEntity entity;
		try {
			//Build Post Object			
			
			
			Log.d("PB", "Posting JSON Object: " + data.toString());
			Log.d("PB", "Posting URL: " + url);
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("data", data.toString()));

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(post, responseHandler);

			
			
			
			
			
			
			
			
			

			return responseBody;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ERROR fetching data";
	}
	public static String postJsonData(HashMap<String,String> data, String url) {
		return postJsonData(new JSONObject(data), url);
		
	}

	
}
