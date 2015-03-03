package se.lolhelper.DataManager;

import java.util.LinkedList;
import java.util.List;

public class ItemsList{

    List pItemsList;

    public ItemsList(){
        pItemsList = new LinkedList();
    }

    public int getSize(){
        return(pItemsList.size());
    }

    public boolean addItem(int _iItemId, String _sItemName, String _sItemDescription){
        Item pTemporaryItem = new Item(_iItemId, _sItemName, _sItemDescription);
        return(pItemsList.add(pTemporaryItem));
    }

    public Item getItemByName(String _sItemName){
        if(this.getSize() > 0){
            return(getItemByName(0, _sItemName));
        }
        else return null;
    }

    private Item getItemByName(int _iIndex, String _sItemName){
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
            Item pTemporaryItem = (Item) pItemsList.get(_iIndex);
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

        public Item(){
            this(-1, null, null);
        }

        public Item(int _iItemId, String _sItemName, String _sItemDescription){
            setId(_iItemId);
            setName(_sItemName);
            setDescription(_sItemDescription);
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

        public int getId(){
            return(this.iItemId);
        }

        public String getName(){
            return(this.sItemName);
        }

        public String getDescription(){
            return(this.sItemDescription);
        }
    }
}
