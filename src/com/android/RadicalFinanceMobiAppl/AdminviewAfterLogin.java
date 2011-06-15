package com.android.RadicalFinanceMobiAppl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminviewAfterLogin extends Mscreen1{
	
	 private Button btnViewAgents;
	 private Button btnRegisterAgent;
	 private Button btnViewTransaction;
	
	 
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.adminviewafterlogin);
	        

	        btnViewAgents = (Button)findViewById(R.id.AdminviewAgent_button);
	        btnRegisterAgent = (Button)findViewById(R.id.AdminRegisterAgent_button);
	        btnViewTransaction = (Button)findViewById(R.id.AdminViewTransactions_button);
	        
	      //Set the events(action listeners)
	        btnViewAgents.setOnClickListener(new OnClickListener()
	        {
	        	@Override
	        	public void onClick(View v){
	        		Intent k = new Intent(AdminviewAfterLogin.this,AdminAgentslistview.class);
	        		startActivity(k); 
	        		
	        		
	        	        
	        	    
	        	}
	        });
	        
	        btnRegisterAgent.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){
	        		Intent k = new Intent(AdminviewAfterLogin.this,Adminlogin.class);
	    			startActivity(k); 
	        		
	        		}
	        	});
	        btnViewTransaction.setOnClickListener(new OnClickListener(){
	        	@Override
	        	public void onClick(View v){
	        		Intent k = new Intent(AdminviewAfterLogin.this,HistoryActivity.class);
	    			startActivity(k); 
	        		
	        		}
	        	});
	 }
	
	       
}	       

	 

