package com.example.blindwallsgallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;
    private List<String> imageUrls;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        mLinearLayout=findViewById(R.id.linear);

        Intent parentIntent=getIntent();

        if(parentIntent!=null){
            if(parentIntent.hasExtra("imageUrls")){
                ArrayList<String> strings=parentIntent.getStringArrayListExtra("imageUrls");
                insertPhotos(strings);
            }
        }
    }

    public void insertPhotos(ArrayList<String> urls){
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
