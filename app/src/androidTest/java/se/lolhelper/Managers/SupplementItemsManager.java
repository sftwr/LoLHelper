package se.lolhelper.Managers;

import android.test.AndroidTestCase;

import se.lolhelper.DataLists.ItemsList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import se.lolhelper.Managers.ChampionsManager;
import se.lolhelper.Managers.ItemsManager;
import se.lolhelper.DataLists.ChampionsList;
import se.lolhelper.Databases.DatabaseManager;


public class SupplementItemsManager extends AndroidTestCase {
    public ItemsManager pItemsManager;

    protected void setUp(){
        pItemsManager = new ItemsManager();
    }

    public boolean checkAll(boolean _pBoolArray[]){
        for(int iCount = 0; iCount < _pBoolArray.length; iCount++){
            if(_pBoolArray[iCount] == false)
                return false;
        }
        return true;
    }

    public void testGetName() throws Exception {
        boolean pTestPass = false;

        while(pItemsManager.pItems.delItem(0) != null){
            // Do nothing, just clear the list
        }
        String sItemName = pItemsManager.getItemName(0);

        if(sItemName.equals("Not Found"))
            pTestPass = true;

        assertEquals("LC_SIM_01", true, pTestPass);
    }

    public void testGetDescription() throws Exception {
        boolean pTestPass[] = new boolean[2];

        if(pItemsManager.getItemDescription("Abyssal Scepter").equals("+70 Ability Power\n" +
                "+50 Magic Resist\n" +
                "\n" +
                "UNIQUE Aura: Reduces the Magic Resist of nearby enemies by 20.")){
            pTestPass[0] = true;
        }


        while(pItemsManager.pItems.delItem(0) != null){
            // Do nothing, just clear the list
        }
        String sItemDescription = pItemsManager.getItemDescription(0);

        if(sItemDescription.equals("Not Found"))
            pTestPass[1] = true;

        assertEquals("LC_SIM_02", true, checkAll(pTestPass));
    }

    public void testGetItemIcon() throws Exception {
        boolean pTestPass[] = new boolean[3];
        String pString[] = pItemsManager.getItemNames();

        String sItemIcon = pItemsManager.getItemIcon(pString[0]);
        if(sItemIcon == null)
            pTestPass[0] = true;

        sItemIcon = pItemsManager.getItemIcon(0);
        if(sItemIcon == null)
            pTestPass[1] = true;

        while(pItemsManager.pItems.delItem(0) != null){
            // Do nothing, just clear the list
        }

        sItemIcon = pItemsManager.getItemIcon(0);
        if(sItemIcon.equals("Not Found"))
            pTestPass[2] = true;

        assertEquals("LC_SIM_03", true, checkAll(pTestPass));
    }
}
