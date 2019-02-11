package com.example.blindwallsgallery.utilities;

import android.os.AsyncTask;
import android.util.Log;

import com.example.blindwallsgallery.MainActivity;
import com.example.blindwallsgallery.WallsAdapter;
import com.example.blindwallsgallery.data.Mural;

import java.net.URL;
import java.util.List;

/**
 * Class to handle the Asynchronous compatability
 */
public class BlindWallsTask extends AsyncTask<String, Void, List<Mural>> {

    private static final String TAG = BlindWallsTask.class.getSimpleName();

    /**
     * What to do at start.
     */
    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: called");
        super.onPreExecute();
    }

    /**
     * Gets the data and requsets the response
     * @param params Unused Strings
     * @return List of murals from the HTTP response
     */
    @Override
    protected List<Mural> doInBackground(String... params) {
        Log.d(TAG, "doInBackground: called");

        List<Mural> response=null;
        URL requestURL= NetworkUtils.buildUrl();

        try {
            String jsonResponse=NetworkUtils.getResponseFromHttpUrl(requestURL);


            response= BlindWallsJsonUtils.makeMuralFromApi(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * Show what to do at end of 2nd thread
     * @param murals Murals to be placed in the main activity
     */
    @Override
    protected void onPostExecute(List<Mural> murals) {
        Log.d(TAG, "onPostExecute: called");

        WallsAdapter mWallsAdapter= MainActivity.getmWallsAdapter();

        if(murals!=null){
            mWallsAdapter.setMuralData(murals);
            MainActivity.setCache(murals);
        }

    }
}
