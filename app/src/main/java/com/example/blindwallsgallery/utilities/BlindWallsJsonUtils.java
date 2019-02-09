package com.example.blindwallsgallery.utilities;

import android.util.Log;

import com.example.blindwallsgallery.data.Mural;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BlindWallsJsonUtils {
    private static final String TAG="DEBUG";

    public static List<Mural> makeMuralFromApi(String response)throws JSONException {
        Log.w(TAG,"makeMuralFromApi was called" );

//        final int OWM_ID="id";
//        final int OWM_
//        final int OWM_
//        final int OWM_

//        final double OWM_

//        final String OWM_AUTHOR_ID="authorID";
//        final String OWM_NUMBER_ON_MAP="numberOnMap";
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_

        List<Mural>murals=new ArrayList<>();

        JSONArray results=new JSONArray(response);

        for(int n=0;n<results.length();n++) {
            JSONObject mural = results.getJSONObject(n);
            int id = mural.getInt("id");
            int authorID = mural.getInt("authorID");
            int numberOnMap = mural.getInt("numberOnMap");
            int year = mural.getInt("year");
            String rating=mural.getString("rating");
            String videoUrl=mural.getString("url");
            String date = mural.getString("date");
            String address = mural.getString("address");
            String photographer = mural.getString("photographer");
            String videoAuthor = mural.getString("videoAuthor");
            String author = mural.getString("author");
            JSONObject title = mural.getJSONObject("title");
            String titleEN = title.getString("en");
            String titleNL = title.getString("nl");
            JSONObject material = mural.getJSONObject("material");
            String materialEN = material.getString("en");
            String materialNL = material.getString("nl");
            JSONObject category = mural.getJSONObject("category");
            String categoryEN = category.getString("en");
            String categoryNL = category.getString("nl");
            JSONArray images = mural.getJSONArray("images");
            List<String> imageUrls = new ArrayList<>();
            for (int i = 0; i < images.length(); i++) {
                String tempUrl=images.getJSONObject(i).getString("url");
                String url = "https://api.blindwalls.gallery/" + tempUrl.substring(0, tempUrl.length()-4)+".jpg";
                imageUrls.add(url);
            }

            JSONObject description = mural.getJSONObject("description");
            String descrEN = description.getString("en");
            String descrNL = description.getString("nl");

//            Scanner sc=new Scanner(descrEN);
//
//            sc.useDelimiter("\\n \\n");
//
//            String descMural=sc.next();
//            sc.useDelimiter("}");
//            String descAuthor=sc.next();
//
//            String descMuralEN=descMural;
//            String descAuthorEN=descAuthor;
//
//            sc.close();
//            sc=new Scanner(descrNL);
//
//            sc.useDelimiter("\\n \\n");
//
//            descMural=sc.next();
//            sc.useDelimiter("\"}");
//            descAuthor=sc.next();
//
//            String descMuralNL=descMural;
//            String descAuthorNL=descAuthor;
//
//            sc.close();



            Mural muralObject=new Mural(id,date,authorID,address,numberOnMap,videoUrl,year,photographer,videoAuthor,author,rating,titleEN,titleNL,descrEN,descrEN,descrNL,descrNL,materialEN,materialNL,categoryEN,categoryNL,imageUrls);
            Log.d(TAG,mural.toString());
            murals.add(muralObject);

        }

        return murals;
    }
}
