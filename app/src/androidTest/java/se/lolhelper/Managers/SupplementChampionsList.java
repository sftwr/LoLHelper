package se.lolhelper.Managers;

import android.test.AndroidTestCase;

import se.lolhelper.DataLists.ChampionsList;

public class SupplementChampionsList extends AndroidTestCase{

    public ChampionsList pChampionsList;

    protected void setUp(){
        pChampionsList = new ChampionsList();
        pChampionsList.addChampion(1, "TestChampion", null, null);
    }

    public boolean checkAll(boolean _pBoolArray[]){
        for(int iCount = 0; iCount < _pBoolArray.length; iCount++){
            if(_pBoolArray[iCount] == false)
                return false;
        }
        return true;
    }

    public void testDelChampion(){
        boolean pTestPass[] = new boolean[2];
        ChampionsList.Champion pChampion;

        pChampion = pChampionsList.delChampion(0);
        if(pChampion.getName().equals("TestChampion"))
            pTestPass[0] = true;

        pChampion = pChampionsList.delChampion(0);
        if(pChampion == null)
            pTestPass[1] = true;

        assertEquals("LC_01", true, checkAll(pTestPass));
    }

    public void testGetChampion(){
        boolean pTestPass[] = new boolean[2];
        ChampionsList.Champion pChampion;

        pChampionsList.delChampion(0);
        pChampion = pChampionsList.getChampionByName("TestChampion");
        if(pChampion == null)
            pTestPass[0] = true;

        pChampion = pChampionsList.getChampion(0);
        if(pChampion == null)
            pTestPass[1] = true;

        assertEquals("LC_02", true, checkAll(pTestPass));
    }

    public void testChampionClass(){
        boolean pTestPass[] = new boolean[2];
        ChampionsList.Champion pChampion;

        pChampionsList.addChampion();
        pChampion = pChampionsList.delChampion(1);
        if(pChampion.getId() == -1)
            pTestPass[0] = true;

        if(pChampion.getIcon() == null)
            pTestPass[1] = true;

        assertEquals("LC_03", true, checkAll(pTestPass));
    }
}