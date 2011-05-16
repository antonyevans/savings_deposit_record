package com.android.ideos;
// my second screen as seen by the user.
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log; 
public class Mscreenactivity extends Mscreen1 {
	   
	//Declaring my Views for easier access later in the project
    
    private Button btnClients;
    private Button btnHistory;
    private Button btnBank;
    /** Called when the activity is first created. */
    private static final String TAG = "Mscreenactivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      // set the activity layout
        setContentView(R.layout.mscreen);
      //Getting the EditText and Button References
        
        btnClients = (Button)findViewById(R.id.Clients_button);
        btnHistory = (Button)findViewById(R.id.History_button);
        btnBank = (Button)findViewById(R.id.Bank_button);
        
        //Set the events(action listeners) 
        btnClients.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        //Calls the next class (ClientsActivity) 
        		
    			Intent i = new Intent(Mscreenactivity.this,ClientsActivity.class);
    			startActivity(i);  
               
        		}
        });
        
        btnHistory.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		//Calls the next HistoryActivity 
        		Intent k = new Intent(Mscreenactivity.this,HistoryActivity.class);
    			startActivity(k);  
               
        		}
        	});
        btnBank.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		//Calls the next BankActivity 
        		Intent n = new Intent(Mscreenactivity.this,BankActivity.class);
    			startActivity(n);  
               //enabling loging of errors in logcat
    			String Mscreenactivity=null;
				Log.e(TAG, "whole class" + Mscreenactivity);
        }
      });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     MenuInflater myMenuInflater = getMenuInflater();
     myMenuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     // TODO Auto-generated method stub
     switch(item.getItemId())
     {
      case(R.id.menu_new_client):
       Toast.makeText(this, "New client", Toast.LENGTH_LONG).show();
       break;
      case(R.id.menu_edit):
       Toast.makeText(this, "Edit", Toast.LENGTH_LONG).show();
       break; 
      case(R.id.menu_delete):
       Toast.makeText(this, "Delete", Toast.LENGTH_LONG).show();
       break;
      case(R.id.menu_search):
          Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
          break;
     } 
     return true;
     
   
    
}
}