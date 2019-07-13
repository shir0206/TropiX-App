package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shirzabolotnyklein.tropix.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Integer> allPlayers = new ArrayList<>();
    private ArrayList<String> mStatus = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<Integer> mImages, ArrayList<String> mStatus) {
        this.allPlayers = mImages;
        this.mStatus = mStatus;
        this.mContext = mContext;
    }

    @NonNull //maybe should be deleted
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_players_item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindHolder: called ");

        Glide.with(mContext)
                .asBitmap()
                .load(allPlayers.get(position))
                .into(holder.image);

        holder.status = mStatus.get(position);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG,"onClick: clicked on: " + mImageNames.get(position));

                Toast.makeText(mContext, "תודה שבחרת בי (:", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() { //How many items are in the list
        return allPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        RelativeLayout parentLayout;
        String status;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            if (status.equals("CLOSE")) {
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);

                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                image.setColorFilter(filter);

            }
        }
    }
}
