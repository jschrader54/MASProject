package com.example.masproject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondScreen extends Activity {
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.add_activity_screen);
	 
	        TextView txtName = (TextView) findViewById(R.id.txtName);
	      //  TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
	        Button btnClose = (Button) findViewById(R.id.btnClose);
	 
	        Intent i = getIntent();
	        // Receiving the Data
	        String name = i.getStringExtra("name");
	        String email = i.getStringExtra("email");
	        Log.e("Second Screen", name + "." + email);
	 
	        // Displaying Received data
	        txtName.setText(name);
	    //    txtEmail.setText(email);
	 
	        // Binding Click event to Button
	        btnClose.setOnClickListener(new View.OnClickListener() {
	 
	            public void onClick(View arg0) {
	            	//Starting a new Intent
	                Intent nextScreen = new Intent(getApplicationContext(), MainListActivity.class);

	                //Sending data to another Activity
	                //nextScreen.putExtra("name", inputName.getText().toString());
	                //nextScreen.putExtra("email", inputPassword.getText().toString());
	              //  Log.e("n", inputName.getText()+"."+ inputPassword.getText());

	                startActivity(nextScreen);
	               
	               // finish();
	            }
	        });
	 
	    }
	}


