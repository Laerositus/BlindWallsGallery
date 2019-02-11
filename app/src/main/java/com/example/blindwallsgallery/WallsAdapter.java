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

import com.example.blindwallsgallery.data.Mural;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter class for the recylerView of the main screen
 */
public class WallsAdapter extends RecyclerView.Adapter<WallsAdapter.WallsAdapterViewHolder> {

    private static final String TAG=WallsAdapter.class.getSimpleName();
    private final ItemClickListener mOnClickListener;
    private List<Mural> mMurals;

    /**
     * Constructor for the adapter
     * @param mOnClickListener ItemClickListener
     */
    WallsAdapter(ItemClickListener mOnClickListener){
        this.mOnClickListener=mOnClickListener;
    }

    /**
     * Class for the viewHolder for the adapter
     */
    class WallsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mListMurals;
        private final ImageView mMuralImage;

        /**
         * Constructor for the viewholder
         * @param itemView itemView
         */
        WallsAdapterViewHolder(View itemView) {
            super(itemView);
            mListMurals = itemView.findViewById(R.id.tv_title);
            mMuralImage=itemView.findViewById(R.id.img_author_image);

            itemView.setOnClickListener(this);
        }

        /**
         * Overridden method for the OnClickListener
         * @param v View
         */
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: called");
            int adapterPos=getAdapterPosition();
            Mural muralToPlace=mMurals.get(adapterPos);
            mOnClickListener.onItemClick(muralToPlace);
        }
    }

    /**
     * Method for creating the viewHolder
     * @param viewGroup Viewgroup
     * @param i index
     * @return WallsAdapterViewHolder
     */
    @NonNull
    @Override
    public WallsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: called");
        Context context=viewGroup.getContext();
        int layoutId=R.layout.list_murals;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParent=false;
        View view=inflater.inflate(layoutId,viewGroup,shouldAttachToParent);
        return new WallsAdapterViewHolder(view);
    }

    /**
     * Binds the viewholder to the RecyclerView
     * @param wallsAdapterViewHolder WallsAdapterViewHolder
     * @param i index
     */
    @Override
    public void onBindViewHolder(@NonNull WallsAdapterViewHolder wallsAdapterViewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        Mural muralToPlace=mMurals.get(i);
        wallsAdapterViewHolder.mListMurals.setText(muralToPlace.getTitleEN());
        Uri firstImage=Uri.parse(muralToPlace.getImageUrls().get(0));

        Log.i(TAG, "Uri: "+firstImage+" at title "+muralToPlace.getTitleEN());
        Picasso.get().load(firstImage).into(wallsAdapterViewHolder.mMuralImage);
    }

    /**
     * Returns the amount of items of the view
     * @return Int
     */
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: called");
        if(mMurals==null) return 0;
        return mMurals.size();
    }

    /**
     * Sets the data of the mural
     * @param murals List
     */
    public void setMuralData(List<Mural> murals){
        Log.i(TAG, "setMuralData: called");
        mMurals=murals;
        notifyDataSetChanged();
    }

    /**
     * Interface for a custom listener
     */
    public interface ItemClickListener{
        void onItemClick(Mural mural);
    }
}
