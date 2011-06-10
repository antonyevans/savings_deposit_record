package com.android.RadicalFinanceMobiAppl;
/**package com.android.ideos;
//this class will take care of the different constants that will be used in the project. will use FirstName in place of name/client
import android.net.Uri;
import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
	public static final String TABLE_NAME = "clients" ;

	// Columns in the Clients database which will be imported to the ClientsData class
	public static final String _ID = "_id";
	public static final String FirstName = "firstname";
	public static final String SecondName = "secondname";
	//the uri and authority needed for the content provider
	public static final String AUTHORITY = "com.android.ideos" ;
public static final Uri CONTENT_URI = Uri.parse("content://"
+ AUTHORITY + "/" + TABLE_NAME);
}
**/
