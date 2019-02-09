package com.example.blindwallsgallery;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.blindwallsgallery.data.Mural;
import com.example.blindwallsgallery.utilities.BlindWallsJsonUtils;
import com.example.blindwallsgallery.utilities.BlindWallsTask;

public class MainActivity extends AppCompatActivity implements WallsAdapter.ItemClickListener {

    private static final String TAG="DEBUG";
    private RecyclerView mRecyclerView;
    private static WallsAdapter mWallsAdapter;

    private static final int temp_mNumOfItems=76;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate was called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=findViewById(R.id.rv_main_rv);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        mWallsAdapter=new WallsAdapter(this);
        mRecyclerView.setAdapter(mWallsAdapter);

        loadMuralData();

        mRecyclerView.setItemViewCacheSize(25);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    @Override
    public void onItemClick(Mural mural) {
        String muralString= BlindWallsJsonUtils.makeJsonFromMural(mural);
        Context context=this;
        Class destination=DetailActivity.class;
        Intent detailIntent=new Intent(context,destination);
        detailIntent.putExtra("mural",muralString);
        startActivity(detailIntent);
    }

    public void loadMuralData(){
        mRecyclerView.setVisibility(View.VISIBLE);
        new BlindWallsTask().execute();
    }

    public static WallsAdapter getmWallsAdapter() {
        return mWallsAdapter;
    }

}