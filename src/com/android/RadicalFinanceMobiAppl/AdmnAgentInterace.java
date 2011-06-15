package com.android.RadicalFinanceMobiAppl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdmnAgentInterace extends Mscreen1{
	
	 private Button btnAdminLogin;
	 private Button btnAgentLogin;
	 
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main1);
	        

	        btnAdminLogin = (Button)findViewById(R.id.Adminlogin_button);
	        btnAgentLogin = (Button)findViewById(R.id.Agentlogin_button);
	        
	        //Set the events(action listeners)
	        btnAdminLogin.setOnClickListener(new OnClickListener()
	        {
	        	@Override
	        	public void onClick(View v){
	        		Intent k = new Intent(AdmnAgentInterace.this,Ideosactivity.class);
	    			startActivity(k); 
	        	}
	        });
	        
	        btnAgentLogin.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){
	        		Intent k = new Intent(AdmnAgentInterace.this,AgentLoginLayout.class);
	    			startActivity(k); 
	        		
	        		}
	        	});
	       

}
}