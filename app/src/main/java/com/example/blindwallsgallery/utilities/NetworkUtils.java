package com.example.blindwallsgallery.utilities;

import android.util.Log;
import android.net.Uri;
import com.example.blindwallsgallery.MainActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Class to handle the networking
 */
class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //Default API to use
    private static String mBlindWallsApi="https://api.blindwalls.gallery/apiv2/murals";
    private static final String format="json";
    private static final String FORMAT_PARAM="mode";

    /**
     * Build the URL for the HTTP request
     * @return URL
     */
    static URL buildUrl() {
        Log.e("DEBUG", "buildUrl was called");

        mBlindWallsApi=MainActivity.getApi();

        Uri builtUri = Uri.parse(mBlindWallsApi).buildUpon()
                .appendQueryParameter(FORMAT_PARAM, format)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * Gets the response from the HTTP request
     * @param url URL from buildUrl
     * @return String of JSON of Murals
     * @throws IOException Exception
     */
    static String getResponseFromHttpUrl(URL url) throws IOException {
        Log.e("DEBUG","getResponseFromHttpUrl was called");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Content-Type","application/json");
        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();

        } finally {
            urlConnection.disconnect();
        }
    }
}
