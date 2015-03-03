package se.lolhelper;

/*
Items Manager class must be the class used to interface between all interface code and the
ItemsList. ItemsList should not be invoked directly as to avoid an increase in coupling
*/

import java.util.ArrayList;
import java.util.List;

import se.lolhelper.DataLists.ItemsList;

public class ManagerItems {
    ItemsList pItems;

    public ManagerItems(){
        pItems = new ItemsList();
        populateItemsList();
    }

    private void populateItemsList(){

        // This is where we load all Item data into the DataLists

        pItems.addItem(1, "Syndra", "Born with immense magical potential, Syndra loves nothing more than exercising the incredible power at her command. With each passing day, her mastery of magical force grows more potent and devastating. Refusing any notion of balance or restraint, Syndra wants only to retain control of her power, even if it means annihilating the authorities that seek to stop her.", null);
        pItems.addItem(2, "Elise", "Elise's entrancing beauty and grace conceal the pitiless, black heart of a deadly predator. With ruthless cunning, she lures the unsuspecting with promises of favor from the spider god. Having exchanged her humanity to become something far more sinister, Elise sacrifices the innocent to maintain her power and seemingly eternal youth. No one can fathom how many have been caught in her web, slain to feed her insatiable hunger.", null);
        pItems.addItem(3, "Ahri", "Unlike other foxes that roamed the woods of southern Ionia, Ahri had always felt a strange connection to the magical world around her; a connection that was somehow incomplete. Deep inside, she felt the skin she had been born into was an ill fit for her and dreamt of one day becoming human. Her goal seemed forever out of reach, until she happened upon the wake of a human battle.", null);
        pItems.addItem(4, "Amumu", "Amumu is a diminutive, animated cadaver who wanders the world, trying to discover his true identity. He rose from an ancient Shuriman tomb bound in corpse wrappings with no knowledge of his past, consumed with an uncontrollable sadness.", null);
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
