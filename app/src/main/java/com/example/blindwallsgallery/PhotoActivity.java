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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;
    private List<String> imageUrls;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private static PhotosAdapter mPhotosAdapter;

    private String TAG = PhotoActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate was called");

        setContentView(R.layout.activity_photo_view);

        mRecyclerView=findViewById(R.id.rv_photo_rv);

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<String> mPhotos = new ArrayList<>();
        //TODO Implement a way for transferring this list from parent intent
        mPhotos.add("https://api.blindwalls.gallery/static/1_1.jpg");
        mPhotos.add("https://api.blindwalls.gallery/static/1_2.jpg");
        mPhotos.add("https://api.blindwalls.gallery/static/1_3.jpg");
        mPhotos.add("https://api.blindwalls.gallery/static/1_4.jpg");
        mPhotosAdapter=new PhotosAdapter(mPhotos);
        mRecyclerView.setAdapter(mPhotosAdapter);

        mRecyclerView.setItemViewCacheSize(30);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        Intent parentIntent=getIntent();
        Log.d(TAG, parentIntent.toString());

        if(parentIntent!=null){
            if(parentIntent.hasExtra("imageUrls")){
                ArrayList<String> strings=parentIntent.getStringArrayListExtra("imageUrls");
                Log.d(TAG, strings.toString());
            }
        }
    }

    public void insertPhotos(ArrayList<String> urls){
        Log.d(TAG, "insertPhotos was called");
        for(int i=0;i<urls.size();i++) {
            ImageView iv=new ImageView(this);
            iv.setId(i);

            iv.setPadding(2,2 ,2 ,2 );
            iv.setScaleType(ImageView.ScaleType.FIT_XY);

            Uri image= Uri.parse(urls.get(i));
            Picasso.get().load(image).into(iv);
        }
    }
}
