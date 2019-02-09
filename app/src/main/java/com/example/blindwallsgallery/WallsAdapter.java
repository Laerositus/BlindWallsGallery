package com.example.blindwallsgallery;

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


import com.squareup.picasso.Picasso;

import java.util.List;

public class WallsAdapter extends RecyclerView.Adapter<WallsAdapter.WallsAdapterViewHolder> {

    private static final String TAG="DEBUG";
    private ItemClickListener mOnClickListener;
    private List<Mural> mMurals;

    class WallsAdapterViewHolder extends RecyclerView.ViewHolder{

        private final TextView mListMurals;
        private final ImageView mMuralImage;

        WallsAdapterViewHolder(View itemView) {
            super(itemView);
            mListMurals = itemView.findViewById(R.id.tv_title);
            mMuralImage=itemView.findViewById(R.id.img_author_image);
        }
    }

    @NonNull
    @Override
    public WallsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.w(TAG, "onCreateViewHolder was called");
        Context context=viewGroup.getContext();
        int layoutIdForListItem=R.layout.list_murals;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParent=false;

        View view=inflater.inflate(layoutIdForListItem,viewGroup,shouldAttachToParent);
        return new WallsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallsAdapterViewHolder wallsAdapterViewHolder, int i) {
        Log.e(TAG,"onBindViewHolder was called");
        Mural muralToPlace=mMurals.get(i);
        wallsAdapterViewHolder.mListMurals.setText(muralToPlace.getTitleEN());
        Uri firstImage=Uri.parse(muralToPlace.getImageUrls().get(0));
        Log.w(TAG, "Uri: "+firstImage);

        Picasso.get().load(firstImage).into(wallsAdapterViewHolder.mMuralImage);
    }

    @Override
    public int getItemCount() {
        if(mMurals==null) return 0;
        return mMurals.size();
    }

    void setMuralData(List<Mural> murals){
        mMurals=murals;
        notifyDataSetChanged();
    }

    public interface ItemClickListener{
        void onItemClick(int index);
        void setText(String text);
        void setAmount(int amount);
    }
}
