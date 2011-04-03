package com.android.ideos;
// A helper-class to represent the database.
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 

//SQLiteOpenHelper class—is an Android helper class for database creation and version management
 public class ClientsData extends SQLiteOpenHelper {
	 //defining the constants 
 private static final String DATABASE_NAME = "clients.db" ;
 private static final int DATABASE_VERSION = 1;
 public static final String TABLE_NAME = "clients" ;

//Columns in the Clients database 
public static final String _ID = "_id";
public static final String FirstName = "firstname";
public static final String SecondName = "secondname";
//the uri and authority needed for the content provider
public static final String AUTHORITY = "com.android.ideos" ;
 

 
 //the constructor
 public ClientsData(Context ctx) 
 {
 super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
 }
 
//onCreate() method creates a new database if the required database is not present
 @Override
 public void onCreate(SQLiteDatabase db) {
 db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+ _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FirstName+" TEXT NOT NULL) "+SecondName+" TEXT NOT NULL;" );
 }

 //onUpgrade() method is called when the database needs to be upgraded
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	 
 db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
 onCreate(db);
 }
 }

 
 
 
 
 
 
 
 
 
 
 
 
 
 