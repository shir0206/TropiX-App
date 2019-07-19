package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class StoreRecyclerViewAdapter extends RecyclerView.Adapter<StoreRecyclerViewAdapter.ViewHolder> {

    private Context context;
    //Vibrator vibrator =  (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    private ArrayList<Integer> allPlayers; // ArrayList of all players image addresses
    private ArrayList<Integer> allPrices; // ArrayList of all players prices
    private ArrayList<String> allStatus; // ArrayList of all players status

    Lock open;
    Lock close;

    public StoreRecyclerViewAdapter(Context context, ArrayList<Integer> allPlayers,
                                    ArrayList<Integer> allPrices, ArrayList<String> allStatus,
                                    Lock open, Lock close) {
        this.context = context;

        this.allPlayers = allPlayers;
        this.allPrices = allPrices;
        this.allStatus = allStatus;

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

        // Get the image of the Player item
        Glide.with(context)
                .asBitmap()
                .load(allPlayers.get(position))
                .into(holder.playerInStore);

        // Get the price of the Player of the item
        holder.playerPrice.setText(allPrices.get(position).toString());

        // Get the status of the Player of the item
        holder.status = allStatus.get(position);

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

                    if (canPurchase) {

                        String purchaseSuccessfully = "תודה שקנית אותי :)";
                        Toast.makeText(context, purchaseSuccessfully, Toast.LENGTH_SHORT).show();

                        StoreLogic.getStoreLogic().initRecyclerView(true);

                    }
                    else {
                        String purchaseFailed = "אין לך מספיק מטבעות :(";

                        Toast.makeText(context, purchaseFailed, Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(context, "אני כבר פעיל :)", Toast.LENGTH_SHORT).show();
                    //vibrator.vibrate(50);

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

        ImageView playerInStore; //Image of the Player
        TextView playerPrice; //Price of the Player
        ImageView playerLock; //Image of the lock status of the Player

        RelativeLayout layoutStore;
        String status; //The status of the Player

        public ViewHolder(View itemView) {
            super(itemView);
            playerInStore = itemView.findViewById(R.id.img_player_in_store);
            playerPrice = itemView.findViewById(R.id.tv_price);
            playerLock = itemView.findViewById(R.id.img_lock);
            layoutStore = itemView.findViewById(R.id.rl_store);
        }
    }
}
