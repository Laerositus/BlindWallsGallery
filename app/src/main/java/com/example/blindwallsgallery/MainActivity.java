package com.example.blindwallsgallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
    private String language;

    private static final int temp_mNumOfItems=76;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate was called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=findViewById(R.id.rv_main_rv);

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        mWallsAdapter=new WallsAdapter(this);
        mRecyclerView.setAdapter(mWallsAdapter);

        loadMuralData();

        String toastStr = null;
        if (language.equals("nl")) {
            toastStr = "Murals opgehaald";
        }
        else {
            toastStr = "Murals refreshed";
        }

        Toast toast = Toast.makeText(getApplicationContext(),
                toastStr,
                Toast.LENGTH_SHORT);
        toast.show();

        mRecyclerView.setItemViewCacheSize(30);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        toolbar=findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);

        setupSharedPreferences();
    }

    public void setupSharedPreferences(){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        setLanguage(sharedPreferences.getString("language_setting", "en"));
    }

    public void setLanguage(String language){
        if((!language.equals("en")||!language.equals("nl"))&&language!=null){
            this.language=language;
        }
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public void onItemClick(Mural mural) {
        String muralString= BlindWallsJsonUtils.makeJsonFromMural(mural);
        Context context=this;
        Class destination=DetailActivity.class;
        Intent detailIntent=new Intent(context,destination);
        detailIntent.putExtra("mural",muralString);
        detailIntent.putExtra("language",this.language);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(List<String> imageUrls) { }

    public void loadMuralData(){
        mRecyclerView.setVisibility(View.VISIBLE);
        new BlindWallsTask().execute();
    }

    public static WallsAdapter getmWallsAdapter() {
        return mWallsAdapter;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause was called");

        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = Objects.requireNonNull(mRecyclerView.getLayoutManager()).onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
    }

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