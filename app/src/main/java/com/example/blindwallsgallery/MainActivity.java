package com.example.blindwallsgallery;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blindwallsgallery.utilities.BlindWallsJsonUtils;
import com.example.blindwallsgallery.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

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

        mWallsAdapter=new WallsAdapter();
        mRecyclerView.setAdapter(mWallsAdapter);

        loadMuralData();

        mRecyclerView.setItemViewCacheSize(25);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    public void loadMuralData(){
        mRecyclerView.setVisibility(View.VISIBLE);
        new BlindWallsTask().execute();
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

//    public class BlindWallsTask extends AsyncTask<String, Void, String[]> {
//
//
//        private static final String mBlindWallsApi = "https://api.blindwalls.gallery/apiv2/murals";
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected List<Mural> doInBackground(String... params) {
//            Log.e(TAG, "doInBackground was called");
//
//            Log.e(TAG, Arrays.toString(params));
//
////            if(params.length==0){
////                return null;
////            }
//
//            String[] respons=null;
//            URL requestURL= NetworkUtils.buildUrl();
//
//            try {
//                String jsonResponse=NetworkUtils.getResponseFromHttpUrl(requestURL);
////                Log.d(TAG, jsonResponse);
//
//                respons= BlindWallsJsonUtils.makeMuralFromApi(jsonResponse);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return respons;
//        }
//
//        @Override
//        protected void onPostExecute(List<Mural> murals) {
//            Log.w(TAG,"onPostExecute() was called.");
////            Log.d(TAG,"Response: "+ Arrays.toString(s));
//
//            WallsAdapter mWallsAdapter= MainActivity.getmWallsAdapter();
//
//            if(murals!=null){
//                mWallsAdapter.setMuralData(murals);
//            }
//
//        }
//
//
//    }

}
