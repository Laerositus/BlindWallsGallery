package com.example.blindwallsgallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private WallsAdapter mWallsAdapter;
    private WallsAdapter.ItemClickListener listener;

    private static final int temp_mNumOfItems=23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=(RecyclerView) findViewById(R.id.rv_main_rv);

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mWallsAdapter=new WallsAdapter(temp_mNumOfItems,listener);
        mRecyclerView.setAdapter(mWallsAdapter);
    }
}
