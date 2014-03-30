package com.example.masproject;

import java.util.Locale;

import com.example.masproject.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.masproject.HTTPInteraction;

public class FirstScreen extends Activity {

	 Button b1;
	 Button b2;
	 TextView inputName;
	 TextView inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        System.out.println("You are here.");
        
        inputName = (TextView) findViewById(R.id.name);
 //       inputPassword = (TextView) findViewById(R.id.);
        
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        
        b1.setOnClickListener(myhandler1);
        b2.setOnClickListener(myhandler2);
        HTTPInteraction httpobj= new HTTPInteraction();
        httpobj.httpRequest("http://scdeducationtest.x10.mx/defaultjson.txt", null);
        httpobj.parseHttpResponse();

    }

    View.OnClickListener myhandler1 = new View.OnClickListener() {
        public void onClick(View v) {
        	
        	
        	//Starting a new Intent
            Intent nextScreen = new Intent(getApplicationContext(), SecondScreen.class);

            //Sending data to another Activity
            nextScreen.putExtra("name", inputName.getText().toString());
            //nextScreen.putExtra("email", inputPassword.getText().toString());

          //  Log.e("n", inputName.getText()+"."+ inputPassword.getText());

            startActivity(nextScreen);
        }
      };
      View.OnClickListener myhandler2 = new View.OnClickListener() {
        public void onClick(View v) {
          // it was the 2nd button
        }
      };
    

    
}



