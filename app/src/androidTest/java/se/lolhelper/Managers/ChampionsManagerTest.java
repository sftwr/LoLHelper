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

    ChampionsManager myChampionsManager = new ChampionsManager();
    int[] testInput = {-1, 0, 1, 121, 122, 123};
    int[] testInput2 = {-1, 0, 1, 3, 4, 5};
    boolean[] passed = new boolean[6];
    String result;
    boolean finalResult;

    private void resetMembers(){
        Arrays.fill(passed, false);
        result = null;
        finalResult = true;
    }

    private void isAllTrue(){
        for (int i = 0; i < 6; i++){
            if (!passed[i]){
                finalResult = false;
            }
        }
    }

    private void printPassed(){
        for (int i = 0; i < 6; i++) {
            if (passed[i]) {
                System.out.println(i + " true");
            } else {
                System.out.println(i + " false");
            }
        }
    }

    private String fixString(String myString, String removeThis){
        myString = myString.replace(removeThis, "");
        return myString;
    }

    private String[] fixString(String[] myStrings){
        for (int i = 0; i < myStrings.length; i++){
            myStrings[i] = myStrings[i].replace("'", "");
            myStrings[i] = myStrings[i].replace(" ", "");

        }
        return myStrings;
    }



    public void testGetChampionName() throws Exception {
        //BVA_01.0
        //testInput

        String[] expectedOutput = {"Not Found", "aatrox", "ahri", "zilean", "zyra", "Not Found"};
        resetMembers();


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

/*      for (int i = 0; i < 6; i++){
            if (passed[i]){
                System.out.println(i + " true");
            }
            else {
                System.out.println(i + " false");
            }
        }*/



/*        for (int i = 0; i < 6; i++){
            if (!passed[i]){
                finalResult = false;
            }
        }*/

        isAllTrue();

        assertEquals("BVA_01.0", true, finalResult);

    }

    public void testGetChampionDescription() throws Exception {
        //BVA_02.0
        //testInput

        String aatroxDescrip = "Aatrox is a legendary warrior, one of only five that remain of an ancient race known as the Darkin. He wields his massive blade with grace and poise, slicing through legions in a style that is hypnotic to behold. With each foe felled, Aatrox's seemingly living blade drinks in their blood, empowering him and fueling his brutal, elegant campaign of slaughter. The earliest tale of Aatrox is as old as recorded history. It tells of a war between two great factions remembered only as the Protectorate and the Magelords. Over time, the Magelords won a series of crushing victories, leaving them on the brink of obliterating their sworn enemy forever. On the day of their final confrontation, the Protectorate army found themselves outnumbered, exhausted, and poorly equipped. They braced for inevitable defeat. Just when all hope seemed lost, Aatrox appeared among the ranks of the Protectorate. With but a few words, he urged the soldiers to fight to the last before throwing himself into battle. His presence inspired the desperate warriors. At first, they could only watch in awe as this unknown hero cleaved through their enemies, his body and blade moving in unison as if one being. Soon, the warriors found themselves imbued with a potent thirst for battle. They followed Aatrox into the fray, each fighting with the furious strength of ten until they had won a most unlikely victory. Aatrox vanished after that battle, but the Protectorate army's newfound fury did not. Their surprising triumph led to many more until they were able to finally return home victorious. Their countrymen hailed them as heroes, but though they had saved their entire civilization from destruction, darkness lingered in the mind of each warrior. Something within them had changed. Over time, their memories of battle faded, only to be replaced with a grim revelation: their acts of heroism were, in fact, brutal atrocities committed by their own hands. Tales like these appear among the myths of many cultures. If they are all to be believed, Aatrox's presence has changed the course of some of the most important wars in history. Though these stories remember him as a savior in dark times, Aatrox's true legacy may be a world filled with conflict and strife.''Some fight for honor, some fight for glory. It matters only that you fight.''-- Aatrox";
        String ahriDescrip = "Unlike other foxes that roamed the woods of southern Ionia, Ahri had always felt a strange connection to the magical world around her; a connection that was somehow incomplete. Deep inside, she felt the skin she had been born into was an ill fit for her and dreamt of one day becoming human. Her goal seemed forever out of reach, until she happened upon the wake of a human battle. It was a grisly scene, the land obscured by the forms of wounded and dying soldiers. She felt drawn to one: a robed man encircled by a waning field of magic, his life quickly slipping away. She approached him and something deep inside of her triggered, reaching out to the man in a way she couldn't understand. His life essence poured into her, carried on invisible strands of magic. The sensation was intoxicating and overwhelming. As her reverie faded, she was delighted to discover that she had changed. Her sleek white fur had receded and her body was long and lithe - the shape of the humans who lay scattered about her. However, though she appeared human, she knew that in truth the transformation was incomplete. A cunning creature, she adapted herself to the customs of human society and used her profound gift of beauty to attract unsuspecting men. She could consume their life essences when they were under the spell of her seductive charms. Feeding on their desires brought her closer to her dream, but as she took more lives, a strange sense of regret began to well within her. She had reservations about actions which never troubled her as a fox. She realized that she could not overcome the pangs of her evolving morality. In search of a solution, Ahri found the Institute of War, home of the most gifted mages on Runeterra. They offered her a chance to attain her humanity without further harm through service in the League of Legends.''Mercy is a human luxury... and responsibility.''-- Ahri";
        String zileanDescrip = "In the wastelands of Urtistan, there was once a great city. It perished long ago in a terrible Rune War, like most of the lands below the Great Barrier. Nevertheless, one man survived: a sorcerer named Zilean. Being obsessed with time, it was only fitting that he dwelled in the city's Clock Tower. As the havoc of the war neared his home, Zilean experimented with powerful temporal magic to divine all possible futures, hoping to discover a peaceful solution. But Zilean's enchantments affected his perception of the passage of time, and he was in a contemplative stasis when Urtistan was set upon by an entire phalanx of dark summoner-knights of unknown affiliation. By the time he realized his error, Urtistan was nothing more than smoldering debris. The summoners who were responsible for its destruction had wisely left the Clock Tower unharmed, both to avoid drawing Zilean's attention and to torment him for his oversight. Zilean barely had time to grieve the momentous loss before he learned that his dangerous research had a cruel side effect: chrono-displasia. This mystical disease granted him immortality, but detached his consciousness from its anchor in the present time. He now mentally drifts through time, from any point he has already lived to the present, unable to impact the events which unfold. The most torturous aspect of this curse is that Zilean sometimes experiences Urtistan as it once was and the rest of the time resides in its lonely ruins. Only the powerful summoning magic employed by members of the League of Legends has been able to treat this condition, and Zilean has joined in hopes of finding a cure, and thereafter a way to save his people.''There is no greater grief than for a loss that is yet to come.''-- Zilean";
        String zyraDescrip = "Longing to take control of her fate, the ancient, dying plant Zyra transferred her consciousness into a human body for a second chance at life. Centuries ago, she and her kind dominated the Kumungu Jungle, using thorns and vines to consume any animal that set foot in their territory. As the years passed, the animal population steadily died off. Food became increasingly scarce, and Zyra could only stand by helplessly as the last of her kin withered away. She thought she would perish alone, until the appearance of an unwary sorceress presented her with an opportunity for salvation. It was the first time in years Zyra had sensed a creature wander so close. Hunger drew her to the sorceress, but some other, deeper instinct compelled her. She enveloped the woman in thorns with ease, but as she savored this final meal, foreign memories invaded her thoughts. She saw great jungles of metal and stone where humans and animals thrived. Potent magic surged through her vines, and she devised an elegant but risky plan to survive. Using the woman's memories, Zyra poured her newfound magic into the creation of a human-shaped vessel. She didn't know what sort of world awaited her, but she had nothing left to lose. When Zyra opened her eyes, she was overwhelmed by the raw power ready at her fingertips. It wasn't until she noticed the shriveled remains of the plant she once was that she realized how vulnerable she had become. If this body died, there would be no network of vines to retreat through, no roots to regrow her... but she felt truly alive. She beheld the world for the first time as animals did,and a dark smile crept across her lips. She was reborn, and there was so much now within her grasp.''Closer to the flower, closer to the thorns.''--Zyra";

        String[] expectedOutput = {"Not Found", aatroxDescrip, ahriDescrip, zileanDescrip, zyraDescrip, "Not Found"};
        resetMembers();

        fixString(expectedOutput);

        for (int i = 0; i < 6; i++){
            try {
                result = myChampionsManager.getChampionDescription(testInput[i]);

                result = result.replace("'", "");
                result = result.replace(" ", "");
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

        isAllTrue();

        printPassed();
        System.out.println(aatroxDescrip);

            assertEquals("BVA_02.0", true, finalResult);
    }
}