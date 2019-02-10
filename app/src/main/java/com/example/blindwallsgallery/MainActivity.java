package com.example.blindwallsgallery;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
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

import java.util.List;

public class MainActivity extends AppCompatActivity implements WallsAdapter.ItemClickListener {

    private static final String TAG=MainActivity.class.getSimpleName();

    private final String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewState;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private static WallsAdapter mWallsAdapter;

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

        mRecyclerView.setItemViewCacheSize(30);
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
        Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume was called");

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }


}