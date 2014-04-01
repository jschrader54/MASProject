package com.example.masproject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.os.StrictMode;
import android.util.Log;

public class HTTPInteraction {

	InputStream is;

	public HTTPInteraction() {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectNetwork() // or .detectAll() for all detectable problems
				.penaltyDialog() // show a dialog
				.permitNetwork() // permit Network access
				.build());
		is = null;
	}

	public InputStream httpRequest(String url,
			ArrayList<NameValuePair> nameValuePairs) {

		// http post
		try {

			System.out.println("test1");
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			System.out.println("test2");
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			System.out.println(is + "response");

		}

		catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
		return is;
	}

	public JSONArray parseHttpResponse() {

		JSONArray jArray = null;
		try {
			String line = null;
			String jsonString = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			jsonString = sb.toString();
			jArray = new JSONArray(jsonString);
			System.out.println(jArray);
		} catch (Exception e) {
			Log.e("log_tag",
					"Error in http connection and jsonh" + e.toString());
		}
		return jArray;
	}

}

