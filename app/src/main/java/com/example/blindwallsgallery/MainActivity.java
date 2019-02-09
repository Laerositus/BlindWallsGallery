package com.example.blindwallsgallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WallsAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;
    private static WallsAdapter mWallsAdapter;

    private static final int temp_mNumOfItems=76;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=findViewById(R.id.rv_main_rv);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        mWallsAdapter=new WallsAdapter();
        mRecyclerView.setAdapter(mWallsAdapter);

        loadMuralData();
    }

    public void loadMuralData(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(int index) {}

    @Override
    public void setText(String text){}

    @Override
    public void setAmount(int amount){}

    public static WallsAdapter getmWallsAdapter() {
        return mWallsAdapter;
    }
}
