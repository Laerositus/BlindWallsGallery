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

    public static Mural makeMuralFromJson(String json) throws JSONException{
        Mural mural;

        Log.w(TAG, json);
        JSONObject result=new JSONObject(json);

        int id=result.getInt("id");
        String address=result.getString("address");
        int numberOnMap=result.getInt("numberOnMap");
        String photographer=result.getString("photographer");

        JSONObject title=result.getJSONObject("title");
        String titleEN=title.getString("en");
        String titleNL=title.getString("nl");

        JSONObject description=result.getJSONObject("description");
        String descEN=description.getString("en");
        String descNL=description.getString("nl");

        JSONObject material=result.getJSONObject("material");
        String materialEN=material.getString("en");
        String materialNL=material.getString("nl");

        JSONArray images = result.getJSONArray("images");
        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < images.length(); i++) {
            String tempUrl=images.getJSONObject(i).getString("file");
            String url = "https://api.blindwalls.gallery/" + tempUrl.substring(0, tempUrl.length()-4)+".jpg";
            imageUrls.add(url);
        }
        mural=new Mural(id,address,numberOnMap,photographer,titleEN,titleNL,descEN,descNL,materialEN,materialNL,imageUrls);
        return mural;
    }

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
                String tempUrl=images.getJSONObject(i).getString("file");
                String url = "https://api.blindwalls.gallery/" + tempUrl;
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



            Mural muralObject=new Mural(id,date,authorID,address,numberOnMap,videoUrl,year,photographer,videoAuthor,author,rating,titleEN,titleNL,descrEN,descrNL,materialEN,materialNL,categoryEN,categoryNL,imageUrls);
            Log.d(TAG,mural.toString());
            murals.add(muralObject);

        }

        return murals;
    }

    public static String makeJsonFromMural(Mural mural){
        int id=mural.getId();
        String idS="\"id\":"+id+",";

        String titleEN=mural.getTitleEN();
        String titleNL=mural.getTitleNL();

        String title="\"title\":{";
        String titleEnS="\"en\":\""+titleEN+"\",";
        String titleNlS="\"nl\":\""+titleNL+"\"},";

        String materialEN=mural.getMaterialEN();
        String materialNL=mural.getMaterialNL();
        String material="\"material\":{";
        String materialEnS="\"en\":\""+materialEN+"\",";
        String materialNlS="\"nl\":\""+materialNL+"\"},";

        String address=mural.getAddress();
        String addressS="\"address\":\""+address+"\",";

        int numberOnMap=mural.getNumberOnMap();
        String numberOnMapS="\"numberOnMap\":"+numberOnMap+",";

        String photographer=mural.getPhotographer();
        String photographerS="\"photographer\":\""+photographer+"\",";

        String descEN=mural.getDescEN();
        String descNL=mural.getDescNL();

        String description="\"description\":{";
        String descEnS="\"en\":\""+descEN+"\",";
        String descNlS="\"nl\":\""+descNL+"\"},";

        List<String> imageUrls=mural.getImageUrls();

        String images="\"images\":[";
        String muralJson="{"+idS+addressS+numberOnMapS+photographerS+title+titleEnS+titleNlS+description+descEnS+descNlS+material+materialEnS+materialNlS+images;

        for(int i=0;i<imageUrls.size();i++) {
            String url=imageUrls.get(i);
            String file = "{\"file\":\""+url.substring(31,url.length())+"\"},";
            muralJson=muralJson+file;
        }
        muralJson=muralJson.substring(0,muralJson.length()-1)+"]}";

        return muralJson;
    }
}
