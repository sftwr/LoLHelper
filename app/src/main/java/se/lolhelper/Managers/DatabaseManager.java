package se.lolhelper.Managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseManager extends SQLiteOpenHelper{
    private static String sDatabasePath = "/data/data/se.lolhelper/databases/";
    private static String sDatabaseName = "data_champions";
    private static String sRawDatabase = "data.db";
    private SQLiteDatabase hDatabase;
    private final Context hContext;

    public DatabaseManager(Context _hContext){
        super(_hContext, sDatabaseName, null, 1);
        this.hContext = _hContext;
    }

    private boolean databaseExists(String _sDatabase){
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

    private void copyDatabase(String _sDatabase) throws IOException{
        InputStream hInputStream = hContext.getAssets().open(sRawDatabase);
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

    private void createDatabase(String _sDatabase){
        if(databaseExists(_sDatabase)){
            return;
        }
        else{
            //SQLiteDatabase hTemporary = SQLiteDatabase.openDatabase(sDatabasePath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //hTemporary.close();
            this.getReadableDatabase();
            try{
                copyDatabase(_sDatabase);
            }
            catch(IOException eError){
                eError.printStackTrace();
                throw new Error("Unable to copy database");
            }
        }
    }

    public boolean openDatabase(){
        String sDatabase = sDatabasePath + sDatabaseName;
        if(!databaseExists(sDatabase))
            createDatabase(sDatabase);

        hDatabase = SQLiteDatabase.openDatabase(sDatabase, null, SQLiteDatabase.OPEN_READONLY);
        if(hDatabase != null) return true;
        else return false;
    }

    @Override
    public void onCreate(SQLiteDatabase _hDatabase){ }

    @Override
    public void onUpgrade(SQLiteDatabase _hDatabase, int iOldVersion, int iNewVersion){ }
}
