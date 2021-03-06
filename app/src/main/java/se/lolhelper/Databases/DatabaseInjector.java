package se.lolhelper.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// Injects database in the Resources folder into the appropriate Android folder
// Such that the database can be accessed through the Android API

public class DatabaseInjector extends SQLiteOpenHelper{
    private static String sDatabasePath = "/data/data/se.lolhelper/databases/";
    private static String sDatabaseName = "data_champions.db";
    private static String sRawDatabase = "data_champions";
    private final Context hContext;

    public DatabaseInjector(Context _hContext){
        super(_hContext, sDatabaseName, null, 1);
        this.hContext = _hContext;
    }

    private boolean databaseExists(String _sDatabase){ // Checks whether the database already exists
        SQLiteDatabase hTemporary = null;
        try{
            hTemporary = SQLiteDatabase.openDatabase(_sDatabase, null, SQLiteDatabase.OPEN_READONLY);
            if(hTemporary != null){
                hTemporary.close();
                return true;
            }
        }
        catch(SQLiteException eError){
            eError.printStackTrace();
        }
        return false;
    }

    private void copyDatabase(String _sDatabase) throws IOException{ // Copies the contents of the resource into the created databse
        InputStream hInputStream = hContext.getResources().openRawResource(hContext.getResources().getIdentifier(sRawDatabase, "raw", hContext.getPackageName()));
        OutputStream hOutputStream = new FileOutputStream(_sDatabase);

        int iLength;
        byte[] bBuffer = new byte[1024];

        while((iLength = hInputStream.read(bBuffer)) > 0){
            hOutputStream.write(bBuffer, 0, iLength);
        }

        hOutputStream.flush();
        hOutputStream.close();
        hInputStream.close();

        return;
    }

    public void injectDatabase(){
        injectDatabase(sDatabasePath + sDatabaseName);
        return;
    }

    private void injectDatabase(String _sDatabase){
        if(databaseExists(_sDatabase)){
            return;
        }
        else{
            this.getReadableDatabase(); // Creates the database
            try{
                copyDatabase(_sDatabase);
            }
            catch(IOException eError){
                eError.printStackTrace();
                throw new Error("Unable to copy database");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase _hDatabase){ }

    @Override
    public void onUpgrade(SQLiteDatabase _hDatabase, int iOldVersion, int iNewVersion){ }
}
