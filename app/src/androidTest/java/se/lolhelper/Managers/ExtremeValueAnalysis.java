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

public class ExtremeValueAnalysis extends AndroidTestCase {
    ChampionsManager pChampionsManager;
    ItemsManager     pItemsManager;
    boolean[]        bTestsPassed;

    public ExtremeValueAnalysis(){
        pChampionsManager   = new ChampionsManager();
        pItemsManager       = new ItemsManager();
        bTestsPassed        = new boolean[5];

        testAddChampion(1);
        testAddItem(2);

        pChampionsManager   = new ChampionsManager();
        pItemsManager       = new ItemsManager();

        testGetChampionName(3);
        testGetItemName(4);
        testDelChampion(5);
    }

    public void testAddItem(int _iTestNumber){
        _iTestNumber        = _iTestNumber - 1;

        int iIndex         = 2147483647;
        String sName        = "Test";
        String sDescription = "Test";

        boolean bReturnValue = pItemsManager.pItems.addItem(iIndex, sName, sDescription, null);
        if(bReturnValue)
            bTestsPassed[_iTestNumber] = true;
        else
            bTestsPassed[_iTestNumber] = false;

        assertEquals("EVA_01.0", true, bTestsPassed[_iTestNumber]);
    }

    public void testAddChampion(int _iTestNumber){
        _iTestNumber        = _iTestNumber - 1;

        int iIndex         = 2147483647;
        String sName        = "Test";
        String sDescription = "Test";

        boolean bReturnValue = pChampionsManager.pChampions.addChampion(iIndex, sName, sDescription, null);
        if(bReturnValue)
            bTestsPassed[_iTestNumber] = true;
        else
            bTestsPassed[_iTestNumber] = false;

        assertEquals("EVA_02.0", true, bTestsPassed[_iTestNumber]);
    }

    // Test EVA03.0
    public void testGetChampionName(int _iTestNumber){
        _iTestNumber    = _iTestNumber - 1;
        int iIndex      = 2147483647;

        try {
            pChampionsManager.getChampionName(2147483647);
        }
        catch(IndexOutOfBoundsException eError){
            bTestsPassed[_iTestNumber] = true;
        }

        assertEquals("EVA_03.0", true, bTestsPassed[_iTestNumber]);
    }

    // Test EVA04.0
    public void testGetItemName(int _iTestNumber){
        _iTestNumber    = _iTestNumber - 1;
        int iIndex      = 2147483647;

        try {
            pItemsManager.getItemName(iIndex);
        }
        catch(IndexOutOfBoundsException eError){
            bTestsPassed[_iTestNumber] = true;
        }
        assertEquals("EVA_04.0", true, bTestsPassed[_iTestNumber]);
    }

    // Test EVA05.0
    public void testDelChampion(int _iTestNumber){
        _iTestNumber    = _iTestNumber - 1;
        int iIndex      = 2147483647;

        try {
            pChampionsManager.pChampions.delChampion(iIndex);
        }
        catch(IndexOutOfBoundsException eError){
            bTestsPassed[_iTestNumber] = true;
        }
        assertEquals("EVA_05.0", true, bTestsPassed[_iTestNumber]);
    }
}
