package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.Lock;
import com.shirzabolotnyklein.tropix.utils.StoreLogic;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.getIntent;
import static android.support.v4.content.ContextCompat.startActivity;

public class StoreRecyclerViewAdapter extends RecyclerView.Adapter<StoreRecyclerViewAdapter.ViewHolder> {

    private Context context;
    //Vibrator vibrator =  (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    private ArrayList<Integer> allPlayers; // ArrayList of all players image addresses
    private ArrayList<Integer> allPrices; // ArrayList of all players prices
    private ArrayList<String> allStatus; // ArrayList of all players status
    private ArrayList<Integer> allId; // ArrayList of all players id

    Lock open;
    Lock close;

    public StoreRecyclerViewAdapter(Context context, ArrayList<Integer> allPlayers,
                                    ArrayList<Integer> allPrices, ArrayList<String> allStatus,
                                    ArrayList<Integer> allId, Lock open, Lock close) {
        this.context = context;

        this.allPlayers = allPlayers;
        this.allPrices = allPrices;
        this.allStatus = allStatus;
        this.allId = allId;

        this.open = open;
        this.close = close;
    }

    @NonNull
    @Override
    public StoreRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_store_item_list, parent, false);
        StoreRecyclerViewAdapter.ViewHolder holder = new StoreRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final StoreRecyclerViewAdapter.ViewHolder holder, final int position) {

        //notifyDataSetChanged();


        // Get the image of the Player item
        Glide.with(context)
                .asBitmap()
                .load(allPlayers.get(position))
                .into(holder.playerInStore);

        // Get the price of the Player of the item
        holder.playerPrice.setText(allPrices.get(position).toString());

        // Get the status of the Player of the item
        holder.status = allStatus.get(position);

        // Get the ID of the Player of the item
        holder.playerId = allId.get(position);


        // If Player status is "CLOSED", get the closed lock image of the item
        if (allStatus.get(position).equals("CLOSE")) {
            Glide.with(context)
                    .asBitmap()
                    .load(close.getStatusPic())
                    .into(holder.playerLock);

            // The user can purchase the item
            holder.layoutStore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int price = Integer.valueOf(holder.playerPrice.getText().toString());
                    boolean canPurchase = StoreLogic.getStoreLogic().canPurchase(price);

                    // If can purchase (have enough coins), purchase and update list
                    if (canPurchase) {

                        // Update player ID for purchase in Store Logic
                        int purchasePlayer = holder.playerId;
                        StoreLogic.getStoreLogic().setPurchasePlayer(purchasePlayer);

                        ApplicationContextProvider.setContext(context);

                        Intent intent = new Intent(context, StorePopUpPurchase.class);

                        context.startActivity(intent);

                        // Update list
                        if (context instanceof Store) {
                            notifyItemChanged(position);
                            ((Store) context).initImageBitmapsForPurchasedPlayer(purchasePlayer);
                            ((Store) context).initCoins();
                        }
                    }

                    // If cannot purchase (not have enough coins), return message
                    else {
                        String purchaseFailed = "אין לך מספיק מטבעות :(";
                        Toast.makeText(context, purchaseFailed, Toast.LENGTH_SHORT).show();

                        ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);
                    }
                }


            });
        }

        // If Player status is "OPEN", get the open lock image of the item
        else {
            Glide.with(context)
                    .asBitmap()
                    .load(open.getStatusPic())
                    .into(holder.playerLock);

            // The user cannot purchase the item
            holder.layoutStore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String alreadyAvailable = "אני כבר פעיל :)";
                    Toast.makeText(context, alreadyAvailable, Toast.LENGTH_SHORT).show();

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

        ImageView playerInStore; // Image of the Player
        TextView playerPrice; // Price of the Player
        ImageView playerLock; // Image of the lock status of the Player

        RelativeLayout layoutStore;
        String status; // The status of the Player
        int playerId; // The Player ID

        public ViewHolder(View itemView) {
            super(itemView);
            playerInStore = itemView.findViewById(R.id.img_player_in_store);
            playerPrice = itemView.findViewById(R.id.tv_price);
            playerLock = itemView.findViewById(R.id.img_lock);
            layoutStore = itemView.findViewById(R.id.rl_store);
        }
    }
}
