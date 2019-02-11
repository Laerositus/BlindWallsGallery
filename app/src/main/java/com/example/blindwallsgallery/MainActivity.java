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

    private static final String TAG = MainActivity.class.getSimpleName();

    private final String LIST_STATE_KEY = "list_state_key";
    private Parcelable savedRecyclerLayoutState;
    private static Bundle mLayoutManager;
    private static List<Mural> muralList;


    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private static WallsAdapter mWallsAdapter;
    private Toolbar toolbar;
    private static String language="en";
    private static String api;


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

        mRecyclerView.setItemViewCacheSize(30);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        toolbar=findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);


        if(savedInstanceState != null)
        {
            Log.i(TAG, "onCreate: bundle found");
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

    public void showLoadingToast(){
        String toastStr=null;
        if (language.equals("nl")) {
            toastStr = "Murals opgehaald";
        }
        else if(language.equals("en")){
            toastStr = "Murals refreshed";
        }

        Toast toast = Toast.makeText(getApplicationContext(),
                toastStr,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public static String getApi() {
        return api;
    }

    public void setupSharedPreferences(){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        setApi(sharedPreferences.getString("api","https://api.blindwalls.gallery/apiv2/murals" ));
        setLanguage(sharedPreferences.getString("language_setting", "en"));
    }

    public void setApi(String api){
        if(api!=null){
            MainActivity.api =api;
        }
    }

    public void setLanguage(String language){
        if((language.equals("en")||language.equals("nl"))){
            MainActivity.language=language;
        }
    }

    public static void setCache(List<Mural> cacheMurals) {
        muralList = cacheMurals;
        Log.i(TAG, "setCache: "+muralList.size()+" murals cached");
    }

    public static String getLanguage() {
        return language;
    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.blindwalls_menu,menu);
        return true;
    }

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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(List<String> imageUrls) { }

    public void loadMuralData(){
        mRecyclerView.setVisibility(View.VISIBLE);
        new BlindWallsTask().execute();
    }

    public void loadCachedMuralData() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mWallsAdapter.setMuralData(muralList);
    }

    public static WallsAdapter getmWallsAdapter() {
        return mWallsAdapter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: called");
        outState.putParcelable(LIST_STATE_KEY, Objects.requireNonNull(mRecyclerView.getLayoutManager()).onSaveInstanceState());
    }
}