package se.lolhelper.Databases;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

// Utilizes the Android API to access a database located in the applications
// /data/data/databases folder.


public class DatabaseManager {
    private static String sDatabasePath = "/data/data/se.lolhelper/databases/";
    private static String sDatabaseName = "data_champions.db";
    private SQLiteDatabase hDatabase;

    public boolean openDatabase(){
        String sDatabase = sDatabasePath + sDatabaseName;
        try {
            hDatabase = SQLiteDatabase.openDatabase(sDatabase, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch(SQLiteException eError){
            eError.printStackTrace();
            throw new Error("Unable to open database");
        }
        if(hDatabase != null) return true;
        else return false;
    }

    public boolean closeDatabase(){ // Closes the database
        if(hDatabase.isOpen()){
            hDatabase.close();
            return true;
        }
        else return true;
    }

    public SQLiteDatabase getDatabase(){ // Returns the opened database object
        if(hDatabase.isOpen()){
            return hDatabase;
        }
        else return null;
    }

}
