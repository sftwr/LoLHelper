package se.lolhelper.Managers;

import android.test.AndroidTestCase;

import se.lolhelper.DataLists.ChampionsList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import se.lolhelper.Databases.DatabaseManager;
/**
 * Created by vv on 4/9/2015.
 */
public class EquivalencePartitioning extends AndroidTestCase {
    ChampionsManager myChampionsManager = new ChampionsManager();
    ChampionsList myChampionsList = new ChampionsList();
    ItemsManager myItemsManager = new ItemsManager();
    Input[] mainInput = {new Input(1, "TestChampion", "TestDescription", null), new Input(-1, "TestChampion", "TestDescription", null), new Input(1, "Test&%Champion", "TestDescription", null), new Input(1, "TestChampion", "Test*Description", null), new Input(1, "TestChampion", "TestDescription", "89")};
    boolean[] passed = new boolean[5];
    String result;
    boolean resultBool;
    boolean finalResult;

    private void resetMembers(){
        Arrays.fill(passed, false);
        resultBool = false;
        result = null;
        finalResult = true;
    }

    private void isAllTrue(){
        for (int i = 0; i < 5; i++){
            if (!passed[i]){
                finalResult = false;
            }
        }
    }

    private void printPassed(){
        for (int i = 0; i < 5; i++) {
            if (passed[i]) {
                System.out.println(i + " true");
            } else {
                System.out.println(i + " false");
            }
        }
    }

    public void testAddChampion() throws Exception{
        boolean[] expectedOutput = {true, false, false, false, false};
        resetMembers();
        for (int i = 0; i < 5; i++){
            resultBool = myChampionsList.addChampion(mainInput[i].id, mainInput[i].name, mainInput[i].description, mainInput[i].icon);
            if (resultBool == expectedOutput[i]){
                passed[i] = true;
            } else {
                passed[i] = false;
            }
        }

        isAllTrue();

        printPassed();

        assertEquals("EP_01.0", true, finalResult);

    }

    public class Input{
        public int id;
        public String name;
        public String description;
        public String icon;

        public Input(int _id, String _name, String _description, String _icon){
            id = _id;
            description = _description;
            name = _name;
            icon = _icon;
        }
    }
}
