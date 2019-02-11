package com.example.blindwallsgallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for displaying photos after selected from the Details screen
 */
public class PhotoActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;
    private ArrayList<String> imageUrls;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private static PhotosAdapter mPhotosAdapter;
    private static final String TAG = PhotoActivity.class.getSimpleName();

    /**
     * main method to create the screen
     * @param savedInstanceState Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");

        setContentView(R.layout.activity_photo_view);

        mRecyclerView=findViewById(R.id.rv_photo_rv);

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        Intent parentIntent=getIntent();
        getIntentExtras(parentIntent);

        mPhotosAdapter=new PhotosAdapter(imageUrls);
        mRecyclerView.setAdapter(mPhotosAdapter);
        mRecyclerView.setItemViewCacheSize(30);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        showPhotoToast();
    }

    /**
     * Gets the contents of the Intent
     * @param parentIntent Intent
     */
    public void getIntentExtras(Intent parentIntent){
        Log.d(TAG, "getIntentExtras: called");
        if(parentIntent!=null){
            if(parentIntent.hasExtra("imageUrls")){
                imageUrls=parentIntent.getStringArrayListExtra("imageUrls");
                Log.d(TAG, imageUrls.toString());
            }
        }
    }

    /**
     * Shows the toast that displays the amount of photo's available
     */
    public void showPhotoToast(){
        Log.d(TAG, "showPhotoToast: called");
        String language=MainActivity.getLanguage();

        String toastStr;
        if (language.equals("nl")) {
            toastStr = "Foto's: " + imageUrls.size();
        }
        else {
            toastStr = "Foto's: " + imageUrls.size();
        }

        Toast toast = Toast.makeText(getApplicationContext(),
                toastStr,
                Toast.LENGTH_SHORT);
        toast.show();
    }

}
