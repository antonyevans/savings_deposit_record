package com.android.ideos;
//this class will take care of the different constants that will be used in the project. 
import android.net.Uri;
import android.provider.BaseColumns;
public interface Constants extends BaseColumns {
public static final String TABLE_NAME = "clients" ;
// Columns in the Clients database
public static final String TITLE = "title";
public static final String AUTHORITY = "com.android.ideos" ;
public static final Uri CONTENT_URI = Uri.parse("content://"
+ AUTHORITY + "/" + TABLE_NAME);
}