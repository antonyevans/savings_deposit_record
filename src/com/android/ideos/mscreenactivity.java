package com.android.ideos;
// my second screen.
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class mscreenactivity extends Mscreen1 {
    
	//Declaring my Views for easier access later in the project
    
    private Button btnClients;
    private Button btnHistory;
    private Button btnBank;
    /** Called when the activity is first created. */
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
        		Intent i = new Intent(mscreenactivity.this,ClientsActivity.class);
    			startActivity(i);  
               
        		}
        });
        
        btnHistory.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		
        		}
        	});
        btnBank.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
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