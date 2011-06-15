package com.android.RadicalFinanceMobiAppl;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	//defining columns(fields) in all 4 tables
	public static final String KEY_CLIENTID = "_id";
	public static final String KEY_TRANSID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_MOBILE= "mobile"; 
    public static final String KEY_TYPE = "Type";
    public static final String KEY_DATETIME = "DateTime1";
    public static final String KEY_AMOUNT = "Amount";
    public static final String KEY_BALANCE = "Balance";
    public static final String KEY_AGENTID = "_id";
    public static final String KEY_CASH = "cash";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_AGENTPASSKEY = "passkey";
    public static final String KEY_PASSWORD = "password";
    
    
    private static final String TAG = "DBAdapter";
 //the database name and  the tables names
    private static final String DATABASE_NAME = "radicalfinance";
    private static final String DATABASE_CLIENTSTable = "clientstable";
    private static final String DATABASE_TRANSACTIONS = "TransactionsTable";
    private static final String DATABASE_CLIENTSBALANCE = "ClientsBalanceTable";
    private static final String DATABASE_AGENTSTable = "agentstable";
    private static final String DATABASE_AGENTSDetailsTABLE = "agentsDetailstable";
    private static final String DATABASE_UserNameTable = "usernamepasswordtable";
    
    
    private static final int DATABASE_VERSION = 1;
 //Creating the database radicalfinance
    //CREATING CLIENTSTable
    private static final String DATABASE_CREATE_CLIENTSTABLE =
        "create table clientstable (_id integer primary key autoincrement, "
        + "name text not null, surname text not null, " 
        + "mobile integer not null);";
    
 //CREATING TransactionsTable    
    private static final String DATABASE_CREATE_TRANSACTIONSTABLE =
    	  "create table TransactionsTable (_id integer primary key autoincrement, "
    	//deleted tthe transID
    	+ "name text not null,"
        + "Type text not null,"
        + "DateTime1 Date not null, " 
        + "Amount long not null);";
       
 //CREATING ClientsBalanceTable
    private static final String DATABASE_CREATE_CLIENTSBALANCETABLE =
        "create table ClientsBalanceTable (_id integer primary key autoincrement, "
        + "Balance long not null); "; 
        
 //CREATING AGENTSTable
    private static final String DATABASE_CREATE_AGENTSTABLE =
  	  "create table agentstable (_id integer primary key autoincrement, "
    	+ "name text not null,"
    	+ "surname text not null,"
    	+ "mobile integer not null,"
    	+ "DateTime1 Date not null, " 
    	+ "cash long not null);";
    //Creating AGENTSDetailsTABLE
    private static final String DATABASE_CREATE_AGENTSDETAILSTABLE1 =
  	  "create table agentsdetailstable (_id integer primary key autoincrement, "
  
    	+ "name text not null,"
    	+ "surname text not null,"
    	+ "mobile integer not null, " 
    	+ "passkey text not null);";
     
   // //Creating AGENTSDetailsTABLE
    private static final String DATABASE_CREATE_USERNAMEANDPASSWORD =
    	  "create table usernamepasswordtable (_id integer primary key autoincrement, "
    
      	+ "username text not null,"
      	+ "password text not null);";
       
    	/*	
    //CREATING AGENTSDetailsTABLE
    private static final String DATABASE_CREATE_AGENTSDETAILSTABLE =
  	  "create table agentsDetailstable (_id integer primary key autoincrement, "
    	+ "name text not null,"
    	+ "surname text not null,"
    	+ "passkey text not null, " 
    	+ "mobile integer not null);";
    	*/
    private final Context context;  
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    
    
    //---opens the database---
      public DBAdapter open() throws SQLException 
      {
          db = DBHelper.getWritableDatabase();
          return this;
          
         }
      //---closes the database---    
      public void close() 
      {
          DBHelper.close();
      }
     
	
     
      public DBAdapter(Context ctx) 
      {
          this.context = ctx;
          DBHelper = new DatabaseHelper(context);
      }
 
    public class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
 
        @Override
        public void onCreate(SQLiteDatabase db) 
        {
        	db.execSQL(DATABASE_CREATE_CLIENTSTABLE);
            db.execSQL(DATABASE_CREATE_TRANSACTIONSTABLE);
            db.execSQL(DATABASE_CREATE_CLIENTSBALANCETABLE);
            db.execSQL(DATABASE_CREATE_AGENTSTABLE);
            db.execSQL(DATABASE_CREATE_AGENTSDETAILSTABLE1);
            db.execSQL(DATABASE_CREATE_USERNAMEANDPASSWORD); 
            		          
        }
       
        
        
        
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS clientstable");
            onCreate(db);
        }
    }
        public SQLiteDatabase getWritableDatabase() {
    		// TODO Auto-generated method stub
    		return null;
    	}

	//methods for opening and closing the database, as well as the methods for adding/editing/deleting rows in the table.
    //---insert a client and his info into the database (name,surname,mobile)---

    public long insertClient(String name, String surname, String mobile) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_SURNAME, surname);
        initialValues.put(KEY_MOBILE, mobile);
        
        return db.insert(DATABASE_CLIENTSTable, null, initialValues);
    }
    
    public long insertUsernameAndPassword(String username, String password) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, username);
        initialValues.put(KEY_PASSWORD, password);
        
        return db.insert(DATABASE_UserNameTable, null, initialValues);
    }
    //registering an agent who is an employee
   
        
    public long insertAgent(String name, String surname, String mobile, String passkey) 
    {
        ContentValues initialValues = new ContentValues();
       initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_SURNAME, surname);
        initialValues.put(KEY_MOBILE, mobile);
        initialValues.put(KEY_AGENTPASSKEY, passkey);
        
       return db.insert(DATABASE_AGENTSDetailsTABLE, null, initialValues);
        
    }
    //agent taking a transaction
    public long insertAgentDeposit(String surname, String mobile, String DateTime1, Long cash) 
    {
        ContentValues initialValues = new ContentValues();
       
        initialValues.put(KEY_SURNAME, surname);
        initialValues.put(KEY_MOBILE, mobile);
        initialValues.put(KEY_DATETIME, DateTime1);
        initialValues.put(KEY_CASH, cash);
        
        return db.insert(DATABASE_AGENTSTable, null, initialValues);
    }
 
   
    
    	//create a clients transaction
    public long insertClientTransaction(String name, String Type, String DateTime1, long Amount) 
    {
        ContentValues initialValues = new ContentValues();
       //initialValues.put(KEY_TRANSID, transId);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_TYPE, Type);
        initialValues.put(KEY_DATETIME, DateTime1);
        initialValues.put(KEY_AMOUNT, Amount);
        
        return db.insert(DATABASE_TRANSACTIONS, null, initialValues);
    }
    public long insertClientBalance(String Balance) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_BALANCE, Balance);
        
        return db.insert(DATABASE_CLIENTSBALANCE, null, initialValues);
    }
 
    //---deletes a particular client---
    public boolean deleteClient(long _id) 
    {
        return db.delete(DATABASE_CLIENTSTable,KEY_CLIENTID + "=" + _id, null) > 0;
    }
 
    //---retrieves all the clients---
    public Cursor getAllClients() 
    {
        return db.query(DATABASE_CLIENTSTable, new String[] {
        		KEY_CLIENTID, 
        		KEY_NAME,
        		KEY_SURNAME,
        		KEY_MOBILE}, 
                null, 
                null, 
                null, 
                null, 
                null);
    
    }
   //retrievs the agents availble
    public Cursor getAllAgents() 
    {
        return db.query(DATABASE_AGENTSDetailsTABLE , new String[] {
        		KEY_AGENTID, 
        		KEY_NAME,
        		KEY_SURNAME,
        		KEY_MOBILE,
        		KEY_AGENTPASSKEY}, 
                null, 
                null, 
                null, 
                null,
                null,
                null);
    
    }
    //querying TransactionsTable
    public Cursor getAllTransactionsRecords() {
    
        return db.query(DATABASE_TRANSACTIONS, new String[] {
        		KEY_TRANSID, 
        		KEY_TYPE,
        		KEY_DATETIME,
                KEY_AMOUNT}, 
                null, 
                null, 
                null, 
                null, 
                null);
       
    }
    //in use in the historyActivity.
    public Cursor getClientTransactions() {
    	return db.query(DATABASE_TRANSACTIONS, new String[] {
    			KEY_TRANSID,
    			KEY_NAME,
    			KEY_TYPE,
    			KEY_DATETIME,
    			KEY_AMOUNT
    	},null, null, null, null, null);
    }
    //update agentdeposittransactions
  //in use in the historyActivity.
   
    
    public Cursor getClientTransaction (long rowid) throws SQLException {
    	 Cursor mCursor = db.query(true, DATABASE_TRANSACTIONS, new String[] {
    	
    			KEY_TRANSID ,
    			KEY_NAME,
    			KEY_TYPE,
    			KEY_DATETIME,
    			KEY_AMOUNT
    			},
    			KEY_TRANSID + "=" + rowid,
    			null, null, null, null, null);
    	 if (mCursor != null) {
             mCursor.moveToFirst();
         }
         return mCursor;
        
     }
 
    //---retrieves a particular client---
    public Cursor getClient(long rowid) throws SQLException 
    {
        Cursor mCursor = db.query(true, DATABASE_CLIENTSTable, new String[] {
                		KEY_CLIENTID,
                		KEY_NAME, 
                		KEY_SURNAME,
                		KEY_MOBILE
                		}, 
                		KEY_CLIENTID + "=" + rowid, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
       
    }
    public Cursor getAgent(long rowid) throws SQLException 
    {
        Cursor mCursor = db.query(true, DATABASE_AGENTSDetailsTABLE, new String[] {
                		KEY_AGENTID,
                		KEY_NAME, 
                		KEY_SURNAME,
                		KEY_AGENTPASSKEY,
                		KEY_MOBILE
                		
                		}, 
                		KEY_AGENTID + "=" + rowid, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
       
    }
    
    //gettin the agents history
    public Cursor getAgentTransactions() {
    	return db.query(DATABASE_AGENTSTable, new String[] {
    			KEY_AGENTID,
    			KEY_SURNAME,
    			KEY_MOBILE,
    			KEY_DATETIME,
    			KEY_CASH
    	},null, null, null, null, null);
   
    }
 
    //---updates a client's details---
    public boolean updateClient(long rowid, String name, 
    String surname, String mobile) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_SURNAME, surname);
        args.put(KEY_MOBILE, mobile);
        
        return db.update(DATABASE_CLIENTSTable, args, 
                        KEY_CLIENTID + "=" + rowid, null) > 0;
    }
    // update agents details
    public boolean updateAgent(long rowid, String name, String surname, String mobile, String passkey) 
    	    {
    	        ContentValues args = new ContentValues();
    	        args.put(KEY_NAME, name);
    	        args.put(KEY_SURNAME, surname);
    	        args.put(KEY_MOBILE, mobile);
    	        args.put(KEY_AGENTPASSKEY, passkey);
    	        
    	        return db.update(DATABASE_AGENTSDetailsTABLE, args, 
    	                        KEY_AGENTID + "=" + rowid, null) > 0;
    	    }
    public boolean updateTransactions(long _id, long transId, String Type,
    	    String DateTime1, long Amount) 
    	    {
    	        ContentValues args = new ContentValues();
    	        args.put(KEY_TRANSID, transId);
    	        args.put(KEY_TYPE, Type);
    	        args.put(KEY_DATETIME, DateTime1);
    	        args.put (KEY_AMOUNT, Amount);
    	        return db.update(DATABASE_TRANSACTIONS, args, 
    	                        KEY_CLIENTID + "=" + _id, null) > 0;
    	    }
    //inUse beware wen modifying
  public boolean updateDepositandWithdrawTransactions(String name,  String Type, String DateTime1, long Amount)
  {	
	  ContentValues args = new ContentValues();
	  args.put(KEY_NAME, name);
	  args.put(KEY_TYPE,Type);
	  args.put(KEY_DATETIME, DateTime1);
	  args.put(KEY_AMOUNT, Amount);
	 return db.update(DATABASE_TRANSACTIONS, args, KEY_NAME + "=" + name, null) > 0;
  }
  
  // already used in Bank section for agent deposit and withdraw.
  public boolean updateAgentTransactions(String surname, String mobile, String DateTime1, long cash)
  {
	  ContentValues args = new ContentValues();
	  args.put(KEY_SURNAME, surname);
	  args.put(KEY_MOBILE, mobile);
	  args.put(KEY_DATETIME, DateTime1);
	  args.put(KEY_CASH, cash);
	  return db.update(DATABASE_AGENTSTable, args, KEY_SURNAME + "=" + surname, null) > 0;
  }
		//updating the history transactions made.
	public boolean updateHistoryTransactions(String name, long Amount, String DateTime1)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, name);
		args.put(KEY_AMOUNT, Amount);
		args.put(KEY_DATETIME, DateTime1);
		return db.update(DATABASE_TRANSACTIONS, args, KEY_NAME + "=" + name, null) > 0;
	}
	public boolean updateHistoryTransactionsBilaDate (String name, long Amount)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, name);
		args.put(KEY_AMOUNT, Amount);
		return db.update(DATABASE_TRANSACTIONS, args, KEY_NAME + "=" + name, null) >0;
	}
	
	

   
}
