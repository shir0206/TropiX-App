package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.utils.GameLogic;

import java.util.ArrayList;

public class ChoosePlayerRecyclerViewAdapter extends RecyclerView.Adapter<ChoosePlayerRecyclerViewAdapter.ViewHolder> {

    private Context context;

    private ArrayList<Integer> allId; // ArrayList of all players IDs
    private ArrayList<Integer> allPlayers; // ArrayList of all players image addresses
    private ArrayList<String> allStatus;  // ArrayList of all players status
private String playerType;

    public ChoosePlayerRecyclerViewAdapter(Context context, String playerType, ArrayList<Integer> allId,
                                             ArrayList<Integer> allPlayers, ArrayList<String> allStatus) {
        this.context = context;
        this.allId = allId;
        this.allPlayers = allPlayers;
        this.allStatus = allStatus;
        this.playerType = playerType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_choose_player_item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        // Get the image of the Player item
        Glide.with(context)
                .asBitmap()
                .load(allPlayers.get(position))
                .into(holder.playerToChoose);

        // Get the status of the Player of the item
        holder.status = allStatus.get(position);


        // Get the ID of the Player of the item
        holder.playerId = allId.get(position);


        // If Player status is "CLOSED", color the Player in B&W
        if (allStatus.get(position).equals("CLOSE")) {

            // Set item disable (not clickable)
            holder.layoutPlayer.setEnabled(false);

            // Get the color matrix and set it to 0=B&W
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);

            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            holder.playerToChoose.setColorFilter(filter);

        }

        // If Player status is "OPEN", color the Player in RGB
        else if (allStatus.get(position).equals("OPEN")) {

            // Set item enable (clickable)
            holder.layoutPlayer.setEnabled(true);

            // Get the color matrix and don't change the color
            ColorMatrix matrix = new ColorMatrix();

            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            holder.playerToChoose.setColorFilter(filter);

            // User can choose to play with a Player in status "OPEN"
            holder.layoutPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(playerType.equals("MY")) {

                        // Set the user choice of "My Player" of the game
                        GameLogic.getGameLogic().setMy(holder.playerId);

                        // Update chosen picture
                        if (context instanceof ChoosePlayer) {
                            ((ChoosePlayer) context).updateMyPlayersPic();
                        }

                    }

                    else if(playerType.equals("RIVAL")) {

                        // Set the user choice of "My Player" of the game
                        GameLogic.getGameLogic().setRival(holder.playerId);

                        // Update chosen picture
                        if (context instanceof ChoosePlayer) {
                            ((ChoosePlayer) context).updateRivalPlayersPic();
                        }

                    }

                    ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);


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

        int playerId; //ID of the Player
        ImageView playerToChoose; //Image of the Player

        RelativeLayout layoutPlayer;
        String status;  //The status of the Player

        public ViewHolder(View itemView) {
            super(itemView);
            playerToChoose = itemView.findViewById(R.id.img_player);
            layoutPlayer = itemView.findViewById(R.id.rl_player);

        }
    }
}
