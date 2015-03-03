package se.lolhelper.DataManager;

import java.util.LinkedList;
import java.util.List;

public class ChampionsList{

    List pChampionsList;

    public ChampionsList(){
        pChampionsList = new LinkedList();
    }

    public int getSize(){
        return(pChampionsList.size());
    }

    public boolean addChampion(int _iChampionId, String _sChampionName, String _sChampionDescription){
        Champion pTemporaryChampion = new Champion(_iChampionId, _sChampionName, _sChampionDescription);
        return(pChampionsList.add(pTemporaryChampion));
    }

    public Champion getChampionByName(String _sChampionName){
        if(this.getSize() > 0){
            return(getChampionByName(0, _sChampionName));
        }
        else return null;
    }

    private Champion getChampionByName(int _iIndex, String _sChampionName){
        if(_iIndex < this.getSize()){
            if(getChampion(_iIndex).getName().equals(_sChampionName)){
                return(getChampion(_iIndex));
            }
            else return(getChampionByName(++_iIndex, _sChampionName));
        }
        else return null;
    }


    public Champion getChampion(int _iIndex){
        if(this.getSize() > 0) {
            Champion pTemporaryChampion = (Champion) pChampionsList.get(_iIndex);
            return (pTemporaryChampion);
        }
        else return null;
    }

    // Removes a champion from the list and returns that champion
    public Champion delChampion(int _iIndex){
        if(this.getSize() > 0){
            Champion pTemporaryChampion = getChampion(_iIndex);
            pChampionsList.remove(_iIndex);
            return(pTemporaryChampion);
        }
        else return null;
    }

    public class Champion {
        private int iChampionId;
        private String sChampionName;
        private String sChampionDescription;

        public Champion(){
            this(-1, null, null);
        }

        public Champion(int _iChampionId, String _sChampionName, String _sChampionDescription){
            setId(_iChampionId);
            setName(_sChampionName);
            setDescription(_sChampionDescription);
        }

        public void setId(int _iChampionId){
            this.iChampionId = _iChampionId;
            return;
        }

        public void setName(String _sChampionName){
            this.sChampionName = _sChampionName;
            return;
        }

        public void setDescription(String _sChampionDescription){
            this.sChampionDescription = _sChampionDescription;
            return;
        }

        public int getId(){
            return(this.iChampionId);
        }

        public String getName(){
            return(this.sChampionName);
        }

        public String getDescription(){
            return(this.sChampionDescription);
        }
    }
}
