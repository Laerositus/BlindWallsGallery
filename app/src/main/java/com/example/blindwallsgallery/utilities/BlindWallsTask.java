package com.example.blindwallsgallery.utilities;

import android.os.AsyncTask;
import android.util.Log;

import com.example.blindwallsgallery.MainActivity;
import com.example.blindwallsgallery.data.Mural;

import java.net.URL;
import java.util.List;


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
