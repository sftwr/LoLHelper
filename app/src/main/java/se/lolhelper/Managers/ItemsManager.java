package se.lolhelper.Managers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import se.lolhelper.DataLists.ItemsList;
import se.lolhelper.Databases.DatabaseManager;

// Items Manager class must be the class used to interface between all interface code and the
// ItemsList. ItemsList should not be invoked directly as to avoid an increase in coupling

public class ItemsManager {
    ItemsList pItems;

    public ItemsManager(){
        pItems = new ItemsList();
        populateItemsList();
    }

    private void populateItemsList(){
        // This is where we load all Champion data_champions into the DataLists

        DatabaseManager hDatabaseManager = new DatabaseManager();
        hDatabaseManager.openDatabase();

        SQLiteDatabase hDatabase = hDatabaseManager.getDatabase();
        Cursor hCursor = hDatabase.rawQuery("SELECT * FROM Items", null);

        if(hCursor.getCount() > 0) {
            while (hCursor.moveToNext())
                pItems.addItem(hCursor.getInt(hCursor.getColumnIndex("Id")), hCursor.getString(hCursor.getColumnIndex("ItemName")), hCursor.getString(hCursor.getColumnIndex("ItemDescription")), null);
        }

        hDatabaseManager.closeDatabase();
        hCursor.close();

        return;
    }

    private boolean isItemValid(ItemsList.Item _pItem){
        if(_pItem != null)
            return true;
        else return false;
    }

    public String getItemName(int _iIndex){
        ItemsList.Item pTemporaryItem = pItems.getItem(_iIndex);
        if(isItemValid(pTemporaryItem)){
            return(pTemporaryItem.getName());
        }
        return("Not Found");
    }

    public String getItemDescription(int _iIndex){
        ItemsList.Item pTemporaryItem = pItems.getItem(_iIndex);
        if(isItemValid(pTemporaryItem)){
            return(pTemporaryItem.getDescription());
        }
        return("Not Found");
    }

    public String getItemDescription(String _sItemName){
        ItemsList.Item pTemporaryItem = pItems.getItemByName(_sItemName);
        if(isItemValid(pTemporaryItem)){
            return(pTemporaryItem.getDescription());
        }
        return("Not Found");
    }

    public String getItemIcon(int _iIndex){
        ItemsList.Item pTemporaryItem = pItems.getItem(_iIndex);
        if(isItemValid(pTemporaryItem)){
            return(pTemporaryItem.getIcon());
        }
        return("Not Found"); // <-- Should be a generic icon
    }

    public String getItemIcon(String _sItemName){
        ItemsList.Item pTemporaryItem = pItems.getItemByName(_sItemName);
        if(isItemValid(pTemporaryItem)){
            return(pTemporaryItem.getIcon());
        }
        return("Not Found"); // <-- Should be a generic icon
    }

    public String[] getItemNames(){
        int iCounter = 0;
        List<String> sItemNames = new ArrayList();
        while(pItems.getSize() > iCounter){
            sItemNames.add(pItems.getItem(iCounter).getName());
            iCounter++;
        }
        return(sItemNames.toArray(new String[sItemNames.size()]));
    }
}
