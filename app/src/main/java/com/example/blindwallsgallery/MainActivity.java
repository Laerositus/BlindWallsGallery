package com.example.blindwallsgallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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


    private static final String TAG=MainActivity.class.getSimpleName();

    private final String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewState;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private static WallsAdapter mWallsAdapter;
    private Toolbar toolbar;
    private static String language="en";
    private static String api;

    /**
     * Standard method to create the main view.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate was called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=findViewById(R.id.rv_main_rv);

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        setupSharedPreferences();

        mWallsAdapter=new WallsAdapter(this);
        mRecyclerView.setAdapter(mWallsAdapter);

        loadMuralData();
        showLoadingToast();

        mRecyclerView.setItemViewCacheSize(30);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        toolbar=findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);
    }

    /**
     * Method to show the toast.
     */
    public void showLoadingToast() {
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
        return api;
    }

    /**
     * Method so setup the shared preferences and set the settings to the rest of the application
     */
    public void setupSharedPreferences(){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        setApi(sharedPreferences.getString("api","https://api.blindwalls.gallery/apiv2/murals" ));
        setLanguage(sharedPreferences.getString("language_setting", "en"));
    }

    /**
     * Sets the api given by the sharedPreferences from setupSharedPreferences
     * @param api String
     */
    public void setApi(String api){
        if(api!=null){
            MainActivity.api =api;
        }
    }

    /**
     * Sets the language selected in the settings from sharedPreferences
     * @param language String
     */
    public void setLanguage(String language){
        if((language.equals("en")||language.equals("nl"))){
            MainActivity.language=language;
        }
    }

    /**
     * Returns the language from sharedPreferences
     * @return String
     */
    public static String getLanguage() {
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.blindwalls_menu,menu);
        setMenuLanguage(menu);
        return true;
    }

    /**
     * Sets the language of the menu items
     * @param menu
     */
    public void setMenuLanguage(Menu menu){
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
     * Describes what happens when a option in the menu is selected.
     * If "Settings" was clicked
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
     *
     */
    public void loadMuralData(){
        mRecyclerView.setVisibility(View.VISIBLE);
        new BlindWallsTask().execute();
    }

    /**
     *
     * @return
     */
    public static WallsAdapter getmWallsAdapter() {
        return mWallsAdapter;
    }

    /**
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause was called");

        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = Objects.requireNonNull(mRecyclerView.getLayoutManager()).onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume was called");

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            Objects.requireNonNull(mRecyclerView.getLayoutManager()).onRestoreInstanceState(listState);
        }
    }
}