package se.lolhelper.Managers;

import android.test.AndroidTestCase;

import se.lolhelper.DataLists.ItemsList;

public class SupplementItemsList extends AndroidTestCase{

    public ItemsList pItemsList;

    protected void setUp(){
        pItemsList = new ItemsList();
        pItemsList.addItem(1, "TestItem", null, null);
    }

    public boolean checkAll(boolean _pBoolArray[]){
        for(int iCount = 0; iCount < _pBoolArray.length; iCount++){
            if(_pBoolArray[iCount] == false)
                return false;
        }
        return true;
    }

    public void testDelItem(){
        boolean pTestPass[] = new boolean[2];
        ItemsList.Item pItem;

        pItem = pItemsList.delItem(0);
        if(pItem.getName().equals("TestItem"))
            pTestPass[0] = true;

        pItem = pItemsList.delItem(0);
        if(pItem == null)
            pTestPass[1] = true;

        assertEquals("LC_01", true, checkAll(pTestPass));
    }

    public void testGetItem(){
        boolean pTestPass[] = new boolean[2];
        ItemsList.Item pItem;

        pItemsList.delItem(0);
        pItem = pItemsList.getItemByName("TestItem");
        if(pItem == null)
            pTestPass[0] = true;

        pItem = pItemsList.getItem(0);
        if(pItem == null)
            pTestPass[1] = true;

        assertEquals("LC_02", true, checkAll(pTestPass));
    }

    public void testItemClass(){
        boolean pTestPass[] = new boolean[2];
        ItemsList.Item pItem;

        pItemsList.addItem();
        pItem = pItemsList.delItem(1);
        if(pItem.getId() == -1)
            pTestPass[0] = true;

        if(pItem.getIcon() == null)
            pTestPass[1] = true;

        assertEquals("LC_03", true, checkAll(pTestPass));
    }
}
