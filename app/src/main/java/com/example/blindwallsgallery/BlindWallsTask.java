package com.example.blindwallsgallery;

import android.content.ClipData;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.blindwallsgallery.utilities.BlindWallsJsonUtils;
import com.example.blindwallsgallery.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class BlindWallsTask extends AsyncTask<String, Void, String[]> {

    private static final String TAG = BlindWallsTask.class.getSimpleName();
    private static final String mBlindWallsApi = "https://api.blindwalls.gallery/apiv2/murals";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        mRecyclerView=new RecyclerView().findViewById(R.id.rv_main_rv);
    }

    @Override
    protected String[] doInBackground(String... params) {
        Log.d(TAG, "doInBackground was called");

        if(params.length==0){
            return null;
        }

        String[] respons=null;
        URL requestURL= NetworkUtils.buildUrl();

        try {
            String jsonResponse=NetworkUtils.getResponseFromHttpUrl(requestURL);
            Log.d(TAG, jsonResponse);

            respons= BlindWallsJsonUtils.makeMuralFromApi(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respons;
    }

    @Override
    protected void onPostExecute(String[] s) {
        Log.d(TAG,"onPostExecute() was called.");
        Log.d(TAG,"Response: "+ Arrays.toString(s));

        WallsAdapter mWallsAdapter= MainActivity.getmWallsAdapter();

        if(s!=null){
            mWallsAdapter.setMuralData(s);
        }

    }


}
