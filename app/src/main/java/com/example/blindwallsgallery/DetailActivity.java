package com.example.blindwallsgallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView mDetailImgMural;
    private TextView mDetailTitle;
    private TextView mDetailMuralInfo;
    private TextView mDetailPhotographer;
    private TextView mDetailMuralDescription;
    private String mMuralString;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDetailImgMural=findViewById(R.id.img_detail_author_image);
        mDetailTitle=findViewById(R.id.tv_title_detail);
        mDetailMuralInfo=findViewById(R.id.tv_mural_info);
        mDetailPhotographer=findViewById(R.id.tv_photographer);
        mDetailMuralDescription=findViewById(R.id.tv_mural_description);

        Intent parentIntent=getIntent();

        if(parentIntent!=null){
            if(parentIntent.hasExtra("mural")){
                mMuralString=parentIntent.getStringExtra("mural");
            }
        }
    }

//    public setDetails(int muralId){
//        Picasso.get().load().into(mDetailImgMural);
//    }

}
