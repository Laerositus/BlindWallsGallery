package com.example.blindwallsgallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WallsAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;
    private WallsAdapter mWallsAdapter;
    private Toast mToast;


    private static final int temp_mNumOfItems=23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView= findViewById(R.id.rv_main_rv);

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mWallsAdapter=new WallsAdapter(temp_mNumOfItems,this);
        mRecyclerView.setAdapter(mWallsAdapter);
    }

    @Override
    public void onItemClick(int index) {
        if(mToast!=null){
            mToast.cancel();
        }

        String message="Item #"+index+" selected.";
        mToast=Toast.makeText(this, message, Toast.LENGTH_LONG);

        mToast.show();
    }
}
