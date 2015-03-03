package se.lolhelper;

import java.util.ArrayList;
import java.util.List;

import se.lolhelper.DataManager.ChampionsList;

public class ManagerChampions {
    ChampionsList pChampions;

    public ManagerChampions(){
        pChampions = new ChampionsList();
        populateChampionsList();
    }

    private void populateChampionsList(){
        pChampions.addChampion(1, "Syndra", "Born with immense magical potential, Syndra loves nothing more than exercising the incredible power at her command. With each passing day, her mastery of magical force grows more potent and devastating. Refusing any notion of balance or restraint, Syndra wants only to retain control of her power, even if it means annihilating the authorities that seek to stop her.", null);
        pChampions.addChampion(2, "Elise", "Elise's entrancing beauty and grace conceal the pitiless, black heart of a deadly predator. With ruthless cunning, she lures the unsuspecting with promises of favor from the spider god. Having exchanged her humanity to become something far more sinister, Elise sacrifices the innocent to maintain her power and seemingly eternal youth. No one can fathom how many have been caught in her web, slain to feed her insatiable hunger.", null);
        pChampions.addChampion(3, "Ahri", "Unlike other foxes that roamed the woods of southern Ionia, Ahri had always felt a strange connection to the magical world around her; a connection that was somehow incomplete. Deep inside, she felt the skin she had been born into was an ill fit for her and dreamt of one day becoming human. Her goal seemed forever out of reach, until she happened upon the wake of a human battle.", null);
        pChampions.addChampion(4, "Amumu", "Amumu is a diminutive, animated cadaver who wanders the world, trying to discover his true identity. He rose from an ancient Shuriman tomb bound in corpse wrappings with no knowledge of his past, consumed with an uncontrollable sadness.", null);
        return;
    }

    public String[] getChampionNames(){
        int iCounter = 0;
        List<String> sChampionNames = new ArrayList();
        while(pChampions.getSize() > iCounter){
            sChampionNames.add(pChampions.getChampion(iCounter).getName());
            iCounter++;
        }
        return(sChampionNames.toArray(new String[sChampionNames.size()]));
    }
}
