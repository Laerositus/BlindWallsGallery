package com.example.blindwallsgallery.utilities;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blindwallsgallery.R;
import com.example.blindwallsgallery.data.Mural;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WallsAdapter extends RecyclerView.Adapter<WallsAdapter.WallsAdapterViewHolder> {

    private static final String TAG="DEBUG";
    private final ItemClickListener mOnClickListener;
    private List<Mural> mMurals;
    private static String activity;

    public WallsAdapter(ItemClickListener mOnClickListener){
        this.mOnClickListener=mOnClickListener;
    }

    class WallsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mListMurals;
        private final ImageView mMuralImage;



        WallsAdapterViewHolder(View itemView) {
            super(itemView);
            mListMurals = itemView.findViewById(R.id.tv_title);
            mMuralImage=itemView.findViewById(R.id.img_author_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPos=getAdapterPosition();
            Mural muralToPlace=mMurals.get(adapterPos);
            mOnClickListener.onItemClick(muralToPlace.getId());
        }
    }

    @NonNull
    @Override
    public WallsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.w(TAG, "onCreateViewHolder was called");
        Log.w(TAG, ""+i);

        View view=null;
        int layoutId;

        switch (activity){
            case "main":Context context=viewGroup.getContext();
                layoutId=R.layout.list_murals;
                LayoutInflater inflater=LayoutInflater.from(context);
                boolean shouldAttachToParent=false;
                view=inflater.inflate(layoutId,viewGroup,shouldAttachToParent);
                break;
            case "detail":

                break;
            case "photo":

                break;
        }
        return new WallsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallsAdapterViewHolder wallsAdapterViewHolder, int i) {
        Log.e(TAG,"onBindViewHolder was called");
        Mural muralToPlace=mMurals.get(i);
        wallsAdapterViewHolder.mListMurals.setText(muralToPlace.getTitleEN());
        Uri firstImage=Uri.parse(muralToPlace.getImageUrls().get(0));

        Log.w(TAG, "Uri: "+firstImage+" at title "+muralToPlace.getTitleEN());
        Picasso.get().load(firstImage).into(wallsAdapterViewHolder.mMuralImage);


    }

    @Override
    public int getItemCount() {
        if(mMurals==null) return 0;
        return mMurals.size();
    }

    public void setMuralData(List<Mural> murals){
        mMurals=murals;
        notifyDataSetChanged();
    }

    public interface ItemClickListener{
        void onItemClick(int muralId);
    }
}
