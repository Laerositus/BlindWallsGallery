package com.example.blindwallsgallery.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.blindwallsgallery.BlindWallsTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = BlindWallsTask.class.getSimpleName();

    private static final String mBlindWallsApi = "https://api.blindwalls.gallery/apiv2/murals";

    private static final String format="json";

    private static final String FORMAT_PARAM="mode";

    public static URL buildUrl() {
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

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
