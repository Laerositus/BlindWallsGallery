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


public class BlindWallsTask extends AsyncTask<String, Void, List<Mural>> {

    private static final String TAG = BlindWallsTask.class.getSimpleName();
    private static final String mBlindWallsApi = "https://api.blindwalls.gallery/apiv2/murals";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Mural> doInBackground(String... params) {
        Log.d(TAG, "doInBackground was called");

        List<Mural> response=null;
        URL requestURL= NetworkUtils.buildUrl();

        try {
            String jsonResponse=NetworkUtils.getResponseFromHttpUrl(requestURL);
            Log.d(TAG, jsonResponse);

            response= BlindWallsJsonUtils.makeMuralFromApi(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(List<Mural> murals) {
        Log.d(TAG,"onPostExecute() was called.");
        Log.d(TAG,"Response: "+ murals);

        WallsAdapter mWallsAdapter= MainActivity.getmWallsAdapter();

        if(murals!=null){
            mWallsAdapter.setMuralData(murals);
        }

    }


}
