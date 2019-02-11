package com.example.blindwallsgallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.blindwallsgallery.data.Mural;
import com.example.blindwallsgallery.utilities.BlindWallsJsonUtils;
import com.example.blindwallsgallery.utilities.BlindWallsTask;

import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements WallsAdapter.ItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final String LIST_STATE_KEY = "list_state_key";
    private Parcelable savedRecyclerLayoutState;
    private static List<Mural> muralList;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private static WallsAdapter mWallsAdapter;
    private Toolbar toolbar;
    private static String language="en";
    private static String api;

    SharedPreferences sharedPreferences;

    /**
     * Standard method to create the main view.
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=findViewById(R.id.rv_main_rv);

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        setupSharedPreferences();
        updateLanguage();

        mWallsAdapter=new WallsAdapter(this);
        mRecyclerView.setAdapter(mWallsAdapter);

        mRecyclerView.setItemViewCacheSize(30);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        toolbar=findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);


        if(savedInstanceState != null)
        {
            Log.i(TAG, "onCreate: Bundle found");
            savedRecyclerLayoutState = savedInstanceState.getParcelable(LIST_STATE_KEY);
            Objects.requireNonNull(mRecyclerView.getLayoutManager()).onRestoreInstanceState(savedRecyclerLayoutState);
            Log.i(TAG, "onCreate: Murals retrieved from cache");
            loadCachedMuralData();
        }
        else {
            Log.i(TAG, "onCreate: Murals refreshed");
            loadMuralData();
            showLoadingToast();
        }
    }

    /**
     * Method to show the toast.
     */
    public void showLoadingToast() {
        Log.d(TAG, "showLoadingToast: called");
        String toastStr = null;
        if (language.equals("nl")) {
            toastStr = "Murals opgehaald";
        } else if (language.equals("en")) {
            toastStr = "Murals refreshed";
        }

        Toast toast = Toast.makeText(getApplicationContext(),
                toastStr,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Returns the api used in sharedPreferences to be used in the application.
     * @return String
     */
    public static String getApi() {
        Log.d(TAG, "getApi: called");
        return api;
    }

    /**
     * Method so setup the shared preferences and set the settings to the rest of the application
     */
    public void setupSharedPreferences(){
        Log.d(TAG, "setupSharedPreferences: called");
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        setApi(sharedPreferences.getString("api","https://api.blindwalls.gallery/apiv2/murals" ));
    }

    /**
     * Retrieves update from sharedPreferences
     */
    public void updateLanguage() {
        Log.d(TAG, "updateLanguage: called");
        setLanguage(sharedPreferences.getString("language_setting", "en"));
    }

    /**
     * Sets the api given by the sharedPreferences from setupSharedPreferences
     * @param api String
     */
    public void setApi(String api){
        Log.d(TAG, "setApi: called");
        if(api!=null){
            MainActivity.api =api;
        }
    }

    /**
     * Sets the language selected in the settings from sharedPreferences
     * @param language String
     */
    public void setLanguage(String language){
        Log.d(TAG, "setLanguage: called");
        if((language.equals("en")||language.equals("nl"))){
            MainActivity.language=language;
            Log.i(TAG, "setLanguage: Language= "+MainActivity.language);
        }
    }


    /**
     * Caches the murals to be saved locally so turning the phone or losing connection while using the app doesn't crash it.
     * @param cacheMurals List<Mural>
     */
    public static void setCache(List<Mural> cacheMurals) {
        Log.d(TAG, "setCache: called");
        muralList = cacheMurals;
        Log.i(TAG, "setCache: "+muralList.size()+" murals cached");
    }

    /**
     * Returns the language from sharedPreferences
     * @return String
     */
    public static String getLanguage() {
        Log.d(TAG, "getLanguage: called");
        return language;
    }

    /**
     * Method overridden from ClickHandler set up in WallsAdapter to give an intent and go to DetailActivity.
     * Also gives the mural that was selected to the activity so the right mural will be shown.
     * Also shares the language attribute to show the details sin the preferred language.
     * @param mural Mural
     */
    @Override
    public void onItemClick(Mural mural) {
        Log.d(TAG, "onItemClick: called");
        setupSharedPreferences();
        String muralString= BlindWallsJsonUtils.makeJsonFromMural(mural);
        Context context=this;
        Class destination=DetailActivity.class;
        Intent detailIntent=new Intent(context,destination);
        detailIntent.putExtra("mural",muralString);
        detailIntent.putExtra("language",MainActivity.language);
        startActivity(detailIntent);
    }

    /**
     * Overridden method to inflate the menu when the options menu is created.
     * @param menu Menu
     * @return Boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: called");
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.blindwalls_menu,menu);
        if (menu!=null){
            Log.i(TAG, "onCreateOptionsMenu: there is a menu");
            //setMenuLanguage(this.menu);
        }

        return true;
    }

    /**
     * Sets the language of the menu items
     * @param menu Menu
     */
    public void setMenuLanguage(Menu menu){
        Log.d(TAG, "setMenuLanguage: called");
        if(language.equals("en")){
            menu.getItem(0).setTitle(R.string.settings_string_en);
            menu.getItem(1).setTitle(R.string.donate);
        }
        else if(language.equals("nl")){
            menu.getItem(0).setTitle(R.string.settings_string_nl);
            menu.getItem(1).setTitle(R.string.donate_nl);
        }
    }

    /**
     * Called when optionsMenu is opened
     * @param menu Menu
     * @return true
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        Log.d(TAG, "onPrepareOptionsMenu: called");
        setMenuLanguage(menu);
        return true;
    }

    /**
     * Describes what happens when a option in the menu is selected.
     * If "Settings" was clicked
     * @param item MenuItem
     * @return Boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: called");
        int id=item.getItemId();
        if(id==R.id.action_settings){
            Intent startSettingsActivity=new Intent(this,SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;

        }else if(id==R.id.action_donate) {
            Intent startDonateActivity = new Intent(Intent.ACTION_VIEW);
            startDonateActivity.setData(Uri.parse("https://streamlabs.com/laerositus"));
            startActivity(startDonateActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to load the Muraldata into the main view
     */
    public void loadMuralData(){
        Log.d(TAG, "loadMuralData: called");
        mRecyclerView.setVisibility(View.VISIBLE);
        new BlindWallsTask().execute();
    }


    /**
     * Gets the data from the cached Murals
     */
    public void loadCachedMuralData() {
        Log.d(TAG, "loadCachedMuralData: called");
        mRecyclerView.setVisibility(View.VISIBLE);
        mWallsAdapter.setMuralData(muralList);
    }

  /**
     * Method to get the used Wallsadapter
     * @return Wallsadapter
     */
    public static WallsAdapter getmWallsAdapter() {
        Log.d(TAG, "getmWallsAdapter: called");
        return mWallsAdapter;
    }

    /**
     * Saves the state of the Activity
     * @param outState Bundle
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: called");
        outState.putParcelable(LIST_STATE_KEY, Objects.requireNonNull(mRecyclerView.getLayoutManager()).onSaveInstanceState());
    }

    /**
     * Called when lifecycle reaches onResume. Updates language.
     */
    @Override
    public void onResume() {
        Log.d(TAG, "onResume: called");
        super.onResume();
        updateLanguage();
    }

}