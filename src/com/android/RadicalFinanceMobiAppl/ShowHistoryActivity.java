package com.android.RadicalFinanceMobiAppl;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
public class ShowHistoryActivity extends ListActivity{
	
	 	//protected TextView clientName;
		//protected TextView typeText;
		//protected TextView datetimeText;
		//protected TextView amountText;
		//protected TextView mobileText;
		
		//protected int clientId;
		private DBAdapter mDbHelper;
		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	      // set the activity layout
	        setContentView(R.layout.history1);
	        
	        mDbHelper = new DBAdapter(this);
		      mDbHelper.open();
		      filldatafromDB();
		      
		}
		 private void filldatafromDB() {
		        Cursor cCursor = mDbHelper.getClientTransactions();
		        startManagingCursor(cCursor);
		        
		        // Create an array to specify the fields we want to display in the list (name, Surname, mobile)
		        
		        String[] fromColumns = new String[]{DBAdapter.KEY_NAME, DBAdapter.KEY_MOBILE, DBAdapter.KEY_TYPE, 
		DBAdapter.KEY_AMOUNT, DBAdapter.KEY_DATETIME};

		        //and an array of the fields we want to bind those fields to (in this case just text1)
		        int[] toLayoutIDs = new int[]{R.id.clientName, R.id.mobile, R.id.Type, R.id.Amount, R.id.DateTime};
		     // Now create a simple cursor adapter and set it to display
		        SimpleCursorAdapter hist = new SimpleCursorAdapter(this, R.layout.historylist, cCursor, fromColumns, toLayoutIDs);
		         setListAdapter(hist);
		         
		        }
		 @Override
		    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		        super.onActivityResult(requestCode, resultCode, intent);
		        filldatafromDB();
		       

		    }

}

		      
	      /** 
	       // SQLiteDatabase db = (new DatabaseHelper(this)).getWritableDatabase();
	        //Cursor cursor = mDbHelper.getClientTransactions();
	       // startManagingCursor(cursor);
	        
				//	new String[]{""+clientId});
	        clientId = getIntent().getIntExtra("CLIENT_ID", 0);
	        SQLiteDatabase db = (new DBAdapter(this)).getWritableDatabase();
	        Cursor cursor = db.rawQuery("SELECT clientstable._id,clientstable.name, clientstable.surname, clientstable.mobile,TransactionsTable.Amount,TransactionsTable.DateTime1,TransactionsTable.Type  FROM clientstable inner join TransactionsTable on TransactionsTable._id=clientstable._id", 
					new String[]{""+clientId });
	        
	     
	        
	        if (cursor.getCount() == 1)
	        {
	        	cursor.moveToFirst();
	        
		        clientName = (TextView) findViewById(R.id.clientName);
		        clientName.setText(cursor.getString(cursor.getColumnIndex("name")) + " " + cursor.getString(cursor.getColumnIndex("surname")));
		        
		        typeText = (TextView) findViewById(R.id.Type);
		        typeText.setText(cursor.getString(cursor.getColumnIndex("Type")));

		        datetimeText = (TextView) findViewById(R.id.DateTime);
		        datetimeText.setText(cursor.getString(cursor.getColumnIndex("DateTime1")));
		        
		        amountText = (TextView) findViewById(R.id.Amount);
		        amountText.setText(cursor.getString(cursor.getColumnIndex("Amount")));
		        
		        mobileText = (TextView) findViewById(R.id.mobile);
		        mobileText.setText(cursor.getString(cursor.getColumnIndex("mobile")));


	        }
      }
}
		      **/