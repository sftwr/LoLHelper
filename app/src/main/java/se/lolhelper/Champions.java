package se.lolhelper;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Champions extends Activity
        implements NavigationDrawerFragmentChampion.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragmentChampion mNavigationDrawerFragmentChampion;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    TextView championMainText;
    private AppState myState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);

        mNavigationDrawerFragmentChampion = (NavigationDrawerFragmentChampion)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragmentChampion.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        championMainText = (TextView) findViewById(R.id.championMainTextID);
        myState = (AppState) getApplicationContext();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) { // TODO: Titles must be loaded from ChampionsManager
        //updates the titlebar and the championMainText TextView
//        switch (number) {
//            case 1:
//                mTitle = getString(R.string.title_champions);
//                setChampionMainTextFromFile("champions_home");
//                break;
//            case 2:
//                mTitle = getString(R.string.title_champions) + " - " + getString(R.string.champion_graves);
//                break;
//            case 3:
//                mTitle = getString(R.string.title_champions) + " - " + getString(R.string.champion_talon);
//                break;
//            case 4:
//                mTitle = getString(R.string.title_champions) + " - " + getString(R.string.champion_twisted_fate);
//                break;
//        }
        number--; //sections start with 1, champion IDs start with 0
        mTitle = myState.pChampionsData.getChampionName(number);
        setChampionMainText(number);
    }

    public void setChampionMainTextFromFile(String name){
        //get the raw resource text file using the name parameter
        //clear the championMainText and then append the contents of the file to the field.
        InputStream is = getResources().openRawResource(getResources().getIdentifier(name, "raw", getPackageName()));
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        try {
            clearMainText();
            while((line = br.readLine()) != null){
                championMainText.append(line);
                championMainText.append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setChampionMainText(int _iIndex){
        clearMainText();
        championMainText.append(myState.pChampionsData.getChampionDescription(_iIndex));
    }

    public void clearMainText(){
        //clears the championMainText
        championMainText.setText("");
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragmentChampion.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            //getMenuInflater().inflate(R.menu.champions, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_champions, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Champions) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
