package com.android.RadicalFinanceMobiAppl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AgentLoginLayout extends Mscreen1 {
	//Declaring my Views for easier access later in the class
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnCancel;
    private TextView lblResult;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      // set the activity layout
        setContentView(R.layout.main);
      //Getting the EditText and Button References
        
        etUsername = (EditText)findViewById(R.id.username);
        etPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.login_button);
        btnCancel = (Button)findViewById(R.id.cancel_button);
        lblResult = (TextView)findViewById(R.id.result);
        
        //Set the events(action listeners)
        btnLogin.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		//check login
        		String username = etUsername.getText().toString();
        		String password = etPassword.getText().toString();
        		
        		if(username.equals("agent") && password.equals("agent")){
        			lblResult.setText("Login Successful.");
        			
        			Intent i = new Intent(AgentLoginLayout.this,Mscreenactivity.class);
        			startActivity(i);  
        		}
        		else
        		{
        			lblResult.setText("Login failed . Username and/or Password doesn't match.");
        	
        			
        		}
        	}
        });
        btnCancel.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// Close the Application
        		finish();
        		
        		}
        	});
    }
}



