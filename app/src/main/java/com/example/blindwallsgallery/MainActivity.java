package com.example.blindwallsgallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements WallsAdapter.ItemClickListener, BlindWallsTask.MuralListener {

    private RecyclerView mRecyclerView;
    private WallsAdapter mWallsAdapter;
    private TextView mTitle;
    private Toast mToast;
    private String TAG = MainActivity.class.getSimpleName();

    private static final int temp_mNumOfItems=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView= findViewById(R.id.rv_main_rv);
        mTitle=findViewById(R.id.tv_title);

        mRecyclerView.setHasFixedSize(true);

        BlindWallsTask blindWallsTask = new BlindWallsTask(this);
        blindWallsTask.execute();

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

    public void onMuralListenerAvailable(List<Mural> list) {
        Log.d(TAG, list.toString());
    }


}
