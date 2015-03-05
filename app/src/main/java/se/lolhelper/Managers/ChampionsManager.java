package se.lolhelper.Managers;

/*
Champions Manager class must be the class used to interface between all interface code and the
ChampionsList. ChampionsList should not be invoked directly as to avoid an increase in coupling
*/

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import se.lolhelper.AppState;
import se.lolhelper.DataLists.ChampionsList;
import se.lolhelper.Databases.DatabaseManager;

public class ChampionsManager {
    ChampionsList pChampions;

    public ChampionsManager(){
        pChampions = new ChampionsList();
        populateChampionsList();
    }

    private void populateChampionsList(){
        // This is where we load all Champion data_champions into the DataLists

        DatabaseManager hDatabaseManager = new DatabaseManager();
        hDatabaseManager.openDatabase();

        SQLiteDatabase hDatabase = hDatabaseManager.getDatabase();
        Cursor hCursor = hDatabase.rawQuery("SELECT * FROM Champions", null);

        if(hCursor.getCount() > 0) {
            while (hCursor.moveToNext())
                pChampions.addChampion(hCursor.getInt(hCursor.getColumnIndex("Id")), hCursor.getString(hCursor.getColumnIndex("ChampionName")), hCursor.getString(hCursor.getColumnIndex("ChampionDescription")), null);
        }

        hDatabaseManager.closeDatabase();
        hCursor.close();

        return;
    }

    private boolean isChampionValid(ChampionsList.Champion _pChampion){
        if(_pChampion != null)
            return true;
        else return false;
    }

    public String getChampionName(int _iIndex){
        ChampionsList.Champion pTemporaryChampion = pChampions.getChampion(_iIndex);
        if(isChampionValid(pTemporaryChampion)){
            return(pTemporaryChampion.getName());
        }
        return("Not Found");
    }

    public String getChampionDescription(int _iIndex){
        ChampionsList.Champion pTemporaryChampion = pChampions.getChampion(_iIndex);
        if(isChampionValid(pTemporaryChampion)){
            return(pTemporaryChampion.getDescription());
        }
        return("Not Found");
    }

    public String getChampionDescription(String _sChampionName){
        ChampionsList.Champion pTemporaryChampion = pChampions.getChampionByName(_sChampionName);
        if(isChampionValid(pTemporaryChampion)){
            return(pTemporaryChampion.getDescription());
        }
        return("Not Found");
    }

    public String getChampionIcon(int _iIndex){
        ChampionsList.Champion pTemporaryChampion = pChampions.getChampion(_iIndex);
        if(isChampionValid(pTemporaryChampion)){
            return(pTemporaryChampion.getIcon());
        }
        return("Not Found"); // <-- Should be a generic icon
    }

    public String getChampionIcon(String _sChampionName){
        ChampionsList.Champion pTemporaryChampion = pChampions.getChampionByName(_sChampionName);
        if(isChampionValid(pTemporaryChampion)){
            return(pTemporaryChampion.getIcon());
        }
        return("Not Found"); // <-- Should be a generic icon
    }

    public String[] getChampionNames(){
        int iCounter = 0;
        List<String> sChampionNames = new ArrayList();
        while(pChampions.getSize() > iCounter){
            sChampionNames.add(pChampions.getChampion(iCounter).getName());
            iCounter++;
        }
        return(sChampionNames.toArray(new String[sChampionNames.size()]));
    }
}
