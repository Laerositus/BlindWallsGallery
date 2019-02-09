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

import org.w3c.dom.Text;

import java.util.List;

public class WallsAdapter extends RecyclerView.Adapter<WallsAdapter.WallsAdapterViewHolder> {

    private ItemClickListener mOnClickListener;
    private String[] mMurals;

    @NonNull
    @Override
    public WallsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context=viewGroup.getContext();
        int layoutIdForListItem=R.layout.list_murals;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParent=false;

        View view=inflater.inflate(layoutIdForListItem,viewGroup,shouldAttachToParent);
        return new WallsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallsAdapterViewHolder wallsAdapterViewHolder, int i) {
        String muralToPlace=mMurals[i];
        wallsAdapterViewHolder.mListMurals.setText(muralToPlace);
    }

    @Override
    public int getItemCount() {
        if(mMurals==null) return 0;
        return mMurals.length;
    }

    void setMuralData(String[] murals){
        mMurals=murals;
        notifyDataSetChanged();
    }

    public class WallsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mListMurals;

        WallsAdapterViewHolder(View itemView) {
            super(itemView);

            mListMurals=itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            int clickPos=getAdapterPosition();
//            mOnClickListener.onItemClick(clickPos);

            Log.d("TAG", "onClick was called");
        }

        public void bind(String text){
            mListMurals.setText(text);
        }
    }

    public interface ItemClickListener{
        void onItemClick(int index);
        void setText(String text);
        void setAmount(int amount);
    }
}
