package se.lolhelper.Managers;

import android.test.AndroidTestCase;

import se.lolhelper.DataLists.ChampionsList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import se.lolhelper.DataLists.ItemsList;
import se.lolhelper.Databases.DatabaseManager;
/**
 * Created by vv on 4/9/2015.
 */
public class EquivalencePartitioning extends AndroidTestCase {
    ChampionsManager myChampionsManager = new ChampionsManager();
    ChampionsList myChampionsList = new ChampionsList();
    ItemsManager myItemsManager = new ItemsManager();
    ItemsList myItemsList = new ItemsList();
    Input[] mainInput = {new Input(1, "TestChampion", "TestDescription", null), new Input(-1, "TestChampion", "TestDescription", null), new Input(1, "Test&%Champion", "TestDescription", null), new Input(1, "TestChampion", "Test*Description", null), new Input(1, "TestChampion", "TestDescription", "89")};
    Input[] secondInput = {new Input(1, "TestItem", "TestDescription", null), new Input(-1, "TestItem", "TestDescription", null), new Input(1, "Test&%Item", "TestDescription", null), new Input(1, "TestItem", "Test*Description", null), new Input(1, "TestItem", "TestDescription", "89")};
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
        //EP_01.0
        //mainInput
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
        System.out.println("ep1.0here");
        printPassed();

        assertEquals("EP_01.0", true, finalResult);

    }

    public void testAddItem() throws Exception{
        //EP_02.0
        //secondInput

        boolean[] expectedOutput = {true, false, false, false, false};
        resetMembers();
        for (int i = 0; i < 5; i++){
            resultBool = myItemsList.addItem(secondInput[i].id, secondInput[i].name, secondInput[i].description, secondInput[i].icon);
            if (resultBool == expectedOutput[i]){
                passed[i] = true;
            } else {
                passed[i] = false;
            }
        }

        isAllTrue();

        printPassed();

        assertEquals("EP_02.0", true, finalResult);

    }

    public void testGetChampionByName() throws Exception{
        //EP_03.0
        //(uses seperate input)

        resetMembers();

        ChampionsList.Champion tempChamp_1 = myChampionsManager.getChampionsList().getChampionByName(0, "Aatrox");
        ChampionsList.Champion tempChamp_2 = myChampionsManager.getChampionsList().getChampionByName(1, "Aatrox");
        ChampionsList.Champion tempChamp_3 = myChampionsManager.getChampionsList().getChampionByName(0, "1000");

        //assume successful then set resultBool to false on failure
        resultBool = true;
        if (tempChamp_1.getName().equals("Aatrox")){
            //still true;
        } else {
            System.out.println("failed at 1");
            System.out.println(tempChamp_1.getId() + tempChamp_1.getName());
            resultBool = false;
        }

        if (tempChamp_2 == null){
            //still true;
        } else {
            System.out.println("failed at 2");
            resultBool = false;
        }

        if (tempChamp_3 == null){
            //still true;
        } else {
            System.out.println("failed at 3");
            resultBool = false;
        }

        assertEquals("EP_03.0", true, resultBool);
    }

    public void testGetItemByName() throws Exception{
        //EP_04.0
        //(uses seperate input)

        resetMembers();

        ItemsList.Item tempItem_1 = myItemsManager.getItemsList().getItemByName(0, "Abyssal Scepter");
        ItemsList.Item tempItem_2 = myItemsManager.getItemsList().getItemByName(1, "Abyssal Scepter");
        ItemsList.Item tempItem_3 = myItemsManager.getItemsList().getItemByName(0, "1000");

        //assume successful then set resultBool to false on failure
        resultBool = true;
        if (tempItem_1.getName().equals("Abyssal Scepter")){
            //still true;
        } else {
            System.out.println("failed at 1");
            System.out.println(tempItem_1.getId() + tempItem_1.getName());
            resultBool = false;
        }

        if (tempItem_2 == null){
            //still true;
        } else {
            System.out.println("failed at 2");
            resultBool = false;
        }

        if (tempItem_3 == null){
            //still true;
        } else {
            System.out.println("failed at 3");
            resultBool = false;
        }

        assertEquals("EP_04.0", true, resultBool);
    }

    public void testGetChampionIcon() throws Exception{
        //EP_05.0
        //(seperate input)

        int[] theInput = {0, -1};
        String[] expectedOutput = {null, "Should be an Exception"};
        boolean[] passed2 = {false, false};

        resetMembers();

        if (expectedOutput[0] == myChampionsManager.getChampionIcon(0)){
            passed2[0] = true;
        }
        try {
            if (expectedOutput[1] == myChampionsManager.getChampionIcon(-1)) {
                passed2[1] = false;
            }
        } catch (IndexOutOfBoundsException iooe){
            passed2[1] = true;
        }

        if (passed[0] && passed[1]) finalResult = true;

        assertEquals("EP_05.0", true, finalResult);

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
