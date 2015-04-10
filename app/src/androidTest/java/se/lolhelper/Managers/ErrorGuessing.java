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

        testAddChampion(1);
        testAddItem(2);

        pChampionsManager = new ChampionsManager();
        pItemsManager     = new ItemsManager();

        testGetItemDescription(3);
        testGetChampionIcon(4);
        testGetItemIcon(5);
    }

    void testAddChampion(int _iTestNumber){
        _iTestNumber = _iTestNumber - 1 ;

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

    void testAddItem(int _iTestNumber){
        _iTestNumber = _iTestNumber - 1 ;

        int iIndex          = 124;
        String sName        = "Abyssal ;Scepter";
        String sDescription = "Test";

        boolean bReturnValue = pItemsManager.pItems.addItem(iIndex, sName, sDescription, null);
        if(bReturnValue)
            bTestsPassed[_iTestNumber] = false;
        else // If addChampions() returns false
            bTestsPassed[_iTestNumber] = true;

        assertEquals("EG_02.0", true, bTestsPassed[_iTestNumber]);
    }

    void testGetItemDescription(int _iTestNumber){
        _iTestNumber = _iTestNumber - 1 ;

        String sName        = "Abyssal ;%Scepter";
        String sDescription = "Test";

        String sReturnValue = pItemsManager.pItems.getItemByName(sName).getDescription();
        if(sReturnValue.equals("Not Found"))
            bTestsPassed[_iTestNumber] = true;
        else // If addChampions() returns false
            bTestsPassed[_iTestNumber] = false;

        assertEquals("EG_03.0", true, bTestsPassed[_iTestNumber]);
    }

    void testGetChampionIcon(int _iTestNumber){
        _iTestNumber = _iTestNumber - 1 ;

        String sName        = "Aa%trox";

        String sReturnValue = pChampionsManager.pChampions.getChampionByName(sName).getIcon();
        if(sReturnValue.equals("Not Found"))
            bTestsPassed[_iTestNumber] = true;
        else // If addChampions() returns false
            bTestsPassed[_iTestNumber] = false;

        assertEquals("EG_04.0", true, bTestsPassed[_iTestNumber]);
    }

    void testGetItemIcon(int _iTestNumber){
        _iTestNumber = _iTestNumber - 1 ;

        String sName        = "Abyssal ;%Scepter";

        String sReturnValue = pItemsManager.pItems.getItemByName(sName).getIcon();
        if(sReturnValue.equals("Not Found"))
            bTestsPassed[_iTestNumber] = true;
        else // If addChampions() returns false
            bTestsPassed[_iTestNumber] = false;

        assertEquals("EG_05.0", true, bTestsPassed[_iTestNumber]);
    }
}
