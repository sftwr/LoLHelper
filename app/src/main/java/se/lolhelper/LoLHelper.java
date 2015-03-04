package se.lolhelper;

import android.app.Activity;

import android.app.ActionBar;
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

import se.lolhelper.Databases.DatabaseInjector;

public class LoLHelper extends Activity
        implements NavigationDrawerFragmentLoLHelper.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragmentLoLHelper mNavigationDrawerFragmentLoLHelper;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseInjector hDatabase = new DatabaseInjector(this);
        hDatabase.injectDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lolhelper);

        mNavigationDrawerFragmentLoLHelper = (NavigationDrawerFragmentLoLHelper)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragmentLoLHelper.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //set up the mainText

        mainText = (TextView) findViewById(R.id.mainTextID);
    }

    public void setMainText(String name){
        InputStream is = getResources().openRawResource(getResources().getIdentifier(name, "raw", getPackageName()));
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        try {
            clearMainText();
            while((line = br.readLine()) != null){
                mainText.append(line);
                mainText.append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void clearMainText(){
        mainText.setText("");
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

    public void onSectionAttached(int number) {
        //updates the titlebar and the mainText TextView
        switch (number) {
            case 1:
                mTitle = getString(R.string.app_name);
                setMainText("home_lolhelper");
                break;
            case 2:
                mTitle = getString(R.string.app_name);
                break;
            case 3:
                mTitle = getString(R.string.app_name);
                //setMainText("home_items");
                break;
            case 4:
                mTitle = getString(R.string.app_name) + " - " + getString(R.string.title_topics);
                setMainText("home_topics");
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragmentLoLHelper.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.lo_lhelper, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_lolhelper, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((LoLHelper) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
