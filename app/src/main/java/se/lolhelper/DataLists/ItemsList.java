package se.lolhelper.DataLists;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemsList{ // Linked list of all items available

    List<Item> pItemsList;

    public ItemsList(){
        pItemsList = new LinkedList();
    }

    public int getSize(){
        return(pItemsList.size());
    }

    public boolean isAlphaNumeric(String _sInput){
        Pattern hPattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
        Matcher hMatcher = hPattern.matcher(_sInput);
        return(hMatcher.matches());
    }

    private boolean isNumeric(String _sInput){
        if(_sInput == null)
            return false;
        else {
            Pattern hPattern = Pattern.compile("^[0-9]*$");
            Matcher hMatcher = hPattern.matcher(_sInput);
            return (hMatcher.matches());
        }
    }

    // Checks to make sure certain symbols we do not want are not in the given string _sInput
    private boolean containsNoSymbols(String _sInput){
        if(_sInput == null)
            return true;
        else {
            Pattern hPattern = Pattern.compile("[~*]");
            Matcher hMatcher = hPattern.matcher(_sInput);
            if(hMatcher.find())
                return false;
            else
                return true;
        }
    }

    private boolean checkBoundaries(int _iItemId){
        if(_iItemId > 0 && _iItemId <= 2147483646)
            return true;
        else
            return false;
    }

    public boolean addItem(int _iItemId, String _sItemName, String _sItemDescription, String _sIcon){
        Item pTemporaryItem = new Item(_iItemId, _sItemName, _sItemDescription, _sIcon);
        if(isAlphaNumeric(_sItemName) && checkBoundaries(_iItemId) &&  !isNumeric(_sIcon) && containsNoSymbols(_sItemDescription))
            return(pItemsList.add(pTemporaryItem));
        else
            return false;
    }

    public Item getItemByName(String _sItemName){
        if(this.getSize() > 0){
            return(getItemByName(0, _sItemName));
        }
        else return null;
    }

    public Item getItemByName(int _iIndex, String _sItemName){
        if(_iIndex < this.getSize()){
            if(getItem(_iIndex).getName().equals(_sItemName)){
                return(getItem(_iIndex));
            }
            else return(getItemByName(++_iIndex, _sItemName));
        }
        else return null;
    }


    public Item getItem(int _iIndex){
        if(this.getSize() > 0) {
            Item pTemporaryItem = pItemsList.get(_iIndex);
            return (pTemporaryItem);
        }
        else return null;
    }

    // Removes a Item from the list and returns that Item
    public Item delItem(int _iIndex){
        if(this.getSize() > 0){
            Item pTemporaryItem = getItem(_iIndex);
            pItemsList.remove(_iIndex);
            return(pTemporaryItem);
        }
        else return null;
    }

    public class Item {
        private int iItemId;
        private String sItemName;
        private String sItemDescription;
        private String sItemIcon;

        public Item(){
            this(-1, null, null, null);
        }

        public Item(int _iItemId, String _sItemName, String _sItemDescription, String _sItemIcon){
            setId(_iItemId);
            setName(_sItemName);
            setDescription(_sItemDescription);
            setIcon(_sItemIcon);
        }

        public void setId(int _iItemId){
            this.iItemId = _iItemId;
            return;
        }

        public void setName(String _sItemName){
            this.sItemName = _sItemName;
            return;
        }

        public void setDescription(String _sItemDescription){
            this.sItemDescription = _sItemDescription;
            return;
        }

        public void setIcon(String _sItemIcon){
            this.sItemIcon = _sItemIcon;
            return;
        }

        public int getId(){
            return(this.iItemId);
        }

        public String getName(){
            return(this.sItemName);
        }

        public String getDescription(){
            return(this.sItemDescription);
        }

        public String getIcon(){
            return(this.sItemIcon);
        }
    }
}
