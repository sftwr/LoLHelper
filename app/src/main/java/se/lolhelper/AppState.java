package se.lolhelper;
import android.app.Application;
import android.content.Context;

import se.lolhelper.Databases.DatabaseInjector;
import se.lolhelper.Managers.ChampionsManager;
import se.lolhelper.Managers.ItemsManager;

/**
 * by extending Application I can store objects/variables in it
 * which can then be accessed by activities using:
 * Application application = (Application)AppState.getContext(); //used for non activities
 * AppState myState = (AppState)application; //^
 *
 * or
 *
 * Appstate myState = (AppState) getApplicationContext(); //used for activities
 */
public class AppState extends Application {
    //members
    private static Context mContext;
    ChampionsManager pChampionsData;
    ItemsManager pItemsData;


    public AppState(){
        pChampionsData = null;
        pItemsData = null;
        this.mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }

    public void setChampionsData(ChampionsManager _pChampionsData){
        pChampionsData = _pChampionsData;
    }

    public void setItemsData(ItemsManager _pItemsData){
        pItemsData = _pItemsData;
    }

//    public void setContext(Context _hContext){
//        this.mContext = _hContext;
//        return;
//    }
}
