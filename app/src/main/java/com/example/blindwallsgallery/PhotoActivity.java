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

public class PhotoActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;
    private ArrayList<String> imageUrls;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private static PhotosAdapter mPhotosAdapter;
    private String language = "en";

    private String TAG = PhotoActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate was called");

        setContentView(R.layout.activity_photo_view);

        mRecyclerView=findViewById(R.id.rv_photo_rv);

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        Intent parentIntent=getIntent();
        Log.d(TAG, parentIntent.toString());



        if(parentIntent!=null){
            if(parentIntent.hasExtra("imageUrls")){
                imageUrls=parentIntent.getStringArrayListExtra("imageUrls");
                Log.d(TAG, imageUrls.toString());
            }
        }

        mPhotosAdapter=new PhotosAdapter(imageUrls);
        mRecyclerView.setAdapter(mPhotosAdapter);

        mRecyclerView.setItemViewCacheSize(30);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        String toastStr = null;
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
