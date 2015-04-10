package se.lolhelper.Managers;

import android.test.AndroidTestCase;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import se.lolhelper.Managers.ChampionsManager;
import se.lolhelper.Managers.ItemsManager;
import se.lolhelper.DataLists.ChampionsList;
import se.lolhelper.Databases.DatabaseManager;

public class ErrorGuessing extends AndroidTestCase {
    ChampionsManager pChampionsManager;
    ItemsManager     pItemsManager;
    boolean[]        bTestsPassed;

    public ErrorGuessing(){
        pChampionsManager = new ChampionsManager();
        pItemsManager     = new ItemsManager();
        bTestsPassed      = new boolean[5];
    }

    public void testAddChampion() throws Exception {
        int _iTestNumber = 1 - 1 ;

        int iIndex          = 124;
        String sName        = "Aat\"rox";
        String sDescription = "Test";

        boolean bReturnValue = pChampionsManager.pChampions.addChampion(iIndex, sName, sDescription, null);
        if(bReturnValue)
            bTestsPassed[_iTestNumber] = false;
        else // If addChampions() returns false
            bTestsPassed[_iTestNumber] = true;

        assertEquals("EG_01.0", true, bTestsPassed[_iTestNumber]);
    }

    public void testAddItem() throws Exception {
        int _iTestNumber = 2 - 1 ;

        int iIndex          = 124;
        String sName        = "Abyssal ;Scepter";
        String sDescription = "Test";

        boolean bReturnValue = pItemsManager.pItems.addItem(iIndex, sName, sDescription, null);
        if(bReturnValue)
            bTestsPassed[_iTestNumber] = false;
        else // If addItem() returns false
            bTestsPassed[_iTestNumber] = true;

        assertEquals("EG_02.0", true, bTestsPassed[_iTestNumber]);
    }

    public void testGetItemDescription() throws Exception {
        int _iTestNumber = 3 - 1 ;

        String sName        = "Abyssal ;%Scepter";

        String sReturnValue = pItemsManager.getItemDescription(sName);
        if(sReturnValue.equals("Not Found"))
            bTestsPassed[_iTestNumber] = true;
        else // If getDescription() is not equal to "Not Found"
            bTestsPassed[_iTestNumber] = false;

        assertEquals("EG_03.0", true, bTestsPassed[_iTestNumber]);
    }

    public void testGetChampionIcon() throws Exception {
        int _iTestNumber = 4 - 1 ;

        String sName        = "Aa%trox";

        String sReturnValue = pChampionsManager.getChampionIcon(sName);
        if(sReturnValue.equals("Not Found"))
            bTestsPassed[_iTestNumber] = true;
        else // If getIcon() is not equal to "Not Found"
            bTestsPassed[_iTestNumber] = false;

        assertEquals("EG_04.0", true, bTestsPassed[_iTestNumber]);
    }

    public void testGetItemIcon() throws Exception {
        int _iTestNumber = 5 - 1 ;

        String sName        = "Abyssal ;%Scepter";

        String sReturnValue = pItemsManager.getItemIcon(sName);
        if(sReturnValue.equals("Not Found"))
            bTestsPassed[_iTestNumber] = true;
        else // If getIcon() is not equal to "Not Found"
            bTestsPassed[_iTestNumber] = false;

        assertEquals("EG_05.0", true, bTestsPassed[_iTestNumber]);
    }
}
