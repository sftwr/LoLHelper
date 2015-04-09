package se.lolhelper.Managers;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import se.lolhelper.DataLists.ChampionsList;
import se.lolhelper.Databases.DatabaseManager;
import android.test.AndroidTestCase;

public class ChampionsManagerTest extends AndroidTestCase {

    public void testGetChampionName() throws Exception {

        ChampionsManager myChampionsManager = new ChampionsManager();
        int[] testInput = {-1, 0, 1, 121, 122, 123};

        String[] expectedOutput = {"Not Found", "aatrox", "ahri", "zilean", "zyra", "Not Found"};
        boolean[] passed = new boolean[6];
        Arrays.fill(passed, false);

        String result;


        for (int i = 0; i < 6; i++){
            try {
                result = myChampionsManager.getChampionName(testInput[i]);
                if (result.equals(expectedOutput[i])){
                    passed[i] = true;
                }
            } catch (IndexOutOfBoundsException iooe){
                if (i == 0 || i == 5){  //Expected to be out of bounds
                    passed[i] = true;
                } else {                //Expected to NOT be out of bounds
                    passed[i] = false;
                }
            }
        }

/*        for (int i = 0; i < 6; i++){
            if (passed[i]){
                System.out.println(i + " true");
            }
            else {
                System.out.println(i + " false");
            }
        }*/

        boolean finalResult = true;

        for (int i = 0; i < 6; i++){
            if (!passed[i]){
                finalResult = false;
            }
        }

        assertEquals("BVA_01.0", true, finalResult);

    }
}