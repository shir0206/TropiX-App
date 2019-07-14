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

        Glide.with(mContext)
                .asBitmap()
                .load(allPlayers.get(position))
                .into(holder.image);

        holder.status = mStatus.get(position);

        // If Player status is "CLOSED", color in BW
        if (mStatus.get(position).equals("CLOSE")) {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);

            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            holder.image.setColorFilter(filter);

            if (position == 3) {
                holder.image.setMinimumHeight(1);
                holder.image.setMaxHeight(1);;

            }
        }


        // If Player status is "OPEN", color in RGB
        else {
            ColorMatrix matrix = new ColorMatrix();

            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            holder.image.setColorFilter(filter);

            // User can choose to play with a Player in status "OPEN"
            holder.layoutPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "תודה שבחרת בי (:", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    /**
     * @return How many items are in the list
     */
    @Override
    public int getItemCount() {
        return allPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        RelativeLayout layoutPlayer;
        String status;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_player);
            layoutPlayer = itemView.findViewById(R.id.rl_player);

        }
    }
}
