package com.example.masproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
        	//Starting new Intent
            startActivity(nextScreen);
        }
      };
	  // New Account Button
	  View.OnClickListener myhandler2 = new View.OnClickListener() {
	    public void onClick(View v) {
	    	String response = null;
			try {
				response = SimpleHttpClient
						.executeHttpGet(
								"http://dev.m.gatech.edu/developer/pconner3/widget/4261/content/api/activities");
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



