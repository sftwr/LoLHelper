package se.lolhelper.Managers;

import android.test.AndroidTestCase;

import se.lolhelper.DataLists.ChampionsList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import se.lolhelper.Managers.ChampionsManager;
import se.lolhelper.Managers.ChampionsManager;
import se.lolhelper.DataLists.ChampionsList;
import se.lolhelper.Databases.DatabaseManager;


public class SupplementChampionsManager extends AndroidTestCase {
    public ChampionsManager pChampionsManager;

    protected void setUp(){
        pChampionsManager = new ChampionsManager();
    }

    public boolean checkAll(boolean _pBoolArray[]){
        for(int iCount = 0; iCount < _pBoolArray.length; iCount++){
            if(_pBoolArray[iCount] == false)
                return false;
        }
        return true;
    }

    public void testGetName() throws Exception {
        boolean pTestPass = false;

        while(pChampionsManager.pChampions.delChampion(0) != null){
            // Do nothing, just clear the list
        }
        String sChampionName = pChampionsManager.getChampionName(0);

        if(sChampionName.equals("Not Found"))
            pTestPass = true;

        assertEquals("LC_SCM_01", true, pTestPass);
    }

    public void testGetDescription() throws Exception {
        boolean pTestPass[] = new boolean[3];

        if(pChampionsManager.getChampionDescription("Aatrox").equals("Aatrox is a legendary warrior, one of only five that remain of an ancient race known as the Darkin. He wields his massive blade with grace and poise, slicing through legions in a style that is hypnotic to behold. With each foe felled, Aatroxs seemingly living blade drinks in their blood, empowering him and fueling his brutal, elegant campaign of slaughter.The earliest tale of Aatrox is as old as recorded history. It tells of a war between two great factions remembered only as the Protectorate and the Magelords. Over time, the Magelords won a series of crushing victories, leaving them on the brink of obliterating their sworn enemy forever. On the day of their final confrontation, the Protectorate army found themselves outnumbered, exhausted, and poorly equipped. They braced for inevitable defeat.Just when all hope seemed lost, Aatrox appeared among the ranks of the Protectorate. With but a few words, he urged the soldiers to fight to the last before throwing himself into battle. His presence inspired the desperate warriors. At first, they could only watch in awe as this unknown hero cleaved through their enemies, his body and blade moving in unison as if one being. Soon, the warriors found themselves imbued with a potent thirst for battle. They followed Aatrox into the fray, each fighting with the furious strength of ten until they had won a most unlikely victory.Aatrox vanished after that battle, but the Protectorate armys newfound fury did not. Their surprising triumph led to many more until they were able to finally return home victorious. Their countrymen hailed them as heroes, but though they had saved their entire civilization from destruction, darkness lingered in the mind of each warrior. Something within them had changed. Over time, their memories of battle faded, only to be replaced with a grim revelation: their acts of heroism were, in fact, brutal atrocities committed by their own hands.Tales like these appear among the myths of many cultures. If they are all to be believed, Aatroxs presence has changed the course of some of the most important wars in history. Though these stories remember him as a savior in dark times, Aatroxs true legacy may be a world filled with conflict and strife.Some fight for honor, some fight for glory. It matters only that you fight.-- Aatrox")){
            pTestPass[0] = true;
        }

        while(pChampionsManager.pChampions.delChampion(0) != null){
            // Do nothing, just clear the list
        }

        String sChampionDescription = pChampionsManager.getChampionDescription(0);
        if(sChampionDescription.equals("Not Found"))
            pTestPass[1] = true;

        sChampionDescription = pChampionsManager.getChampionDescription("Aatrox");
        if(sChampionDescription.equals("Not Found"))
            pTestPass[2] = true;

        assertEquals("LC_SCM_02", true, checkAll(pTestPass));
    }

    public void testGetChampionIcon() throws Exception {
        boolean pTestPass[] = new boolean[3];
        String pString[] = pChampionsManager.getChampionNames();

        String sChampionIcon = pChampionsManager.getChampionIcon(pString[0]);
        if(sChampionIcon == null)
            pTestPass[0] = true;

        sChampionIcon = pChampionsManager.getChampionIcon(0);
        if(sChampionIcon == null)
            pTestPass[1] = true;

        while(pChampionsManager.pChampions.delChampion(0) != null){
            // Do nothing, just clear the list
        }

        sChampionIcon = pChampionsManager.getChampionIcon(0);
        if(sChampionIcon.equals("Not Found"))
            pTestPass[2] = true;

        assertEquals("LC_SCM_03", true, checkAll(pTestPass));
    }
}
