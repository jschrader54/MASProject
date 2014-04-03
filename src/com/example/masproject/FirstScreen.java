package com.example.masproject;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstScreen extends Activity {

	 Button b1;
	 Button b2;
	 TextView inputName;
	 TextView inputPassword;
	 AlertDialog alert;
	 AlertDialog.Builder builder;
	 String jSessionid;
	 String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        //System.out.println("First Screen Created");
        
        inputName = (TextView) findViewById(R.id.name);
        //inputPassword = (TextView) findViewById(R.id.);
        
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);        
        b1.setOnClickListener(myhandler1);
        b2.setOnClickListener(myhandler2);
        jSessionid = "blank";
        builder = new AlertDialog.Builder(this);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                alert.dismiss();
	           }
		});
        
        HTTPInteraction httpobj= new HTTPInteraction();
        httpobj.httpRequest("http://scdeducationtest.x10.mx/defaultjson.txt", null);
        httpobj.parseHttpResponse();

    }
	
	//Login Button     
    View.OnClickListener myhandler1 = new View.OnClickListener() {
        public void onClick(View v) {       	
        	//Creating a new Intent
            Intent nextScreen = new Intent(getApplicationContext(), SecondScreen.class);
            //Sending data to another Activity
            nextScreen.putExtra("name", inputName.getText().toString());
            //Log.e("n", inputName.getText());
            HttpGet httget = new HttpGet("http://dev.m.gatech.edu/developer/pconner3/widget/4261/c/api/login?username=test1&password=test");
            DefaultHttpClient httpclient = new DefaultHttpClient();
            try {
				HttpResponse response = httpclient.execute(httget);
				msg = response.toString();
                System.out.println("response: "+msg);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            List<Cookie> cookies = httpclient.getCookieStore().getCookies();
            if (cookies.isEmpty()) {
                System.out.println("no cookie found");
    			builder.setMessage("no cookie found");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    Cookie cookie = cookies.get(i);
                    if(cookie.getName().equals("JSESSIONID")){
                        jSessionid = cookie.getValue();
                        Log.i("Cookies","- " + cookies.get(i).toString());
                        break;
                    }
                }
    			builder.setMessage(jSessionid);
            }

			alert = builder.create();				
			alert.show();		
        	//Starting new Intent
            //startActivity(nextScreen);
        }
      };
	  // New Account Button
	  View.OnClickListener myhandler2 = new View.OnClickListener() {
	    public void onClick(View v) {
	    	String response = null;
			try {
				response = SimpleHttpClient
						.executeHttpGet(
								"http://dev.m.gatech.edu/developer/pconner3/widget/4261/c/api/login?username=test1&password=test");
				String res = response.toString();
				System.out.println(res);
				builder.setMessage(res);
				alert = builder.create();				
				alert.show();				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	  };       
}



