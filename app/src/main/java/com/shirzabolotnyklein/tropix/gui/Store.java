package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.Lock;
import com.shirzabolotnyklein.tropix.model.Player;
import com.shirzabolotnyklein.tropix.utils.Constants;

import java.util.ArrayList;

public class Store extends AppCompatActivity {

    Constants instance = Constants.getInstance();

    private Context context;
    //Vibrator vibrator =  (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    private ArrayList<Integer> allPlayers = new ArrayList<>(); // ArrayList of all players image addresses
    private ArrayList<Integer> allPrices = new ArrayList<>();  // ArrayList of all players prices
    private ArrayList<String> allStatus = new ArrayList<>(); // ArrayList of all players status

    Lock open;
    Lock close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_store);

        initImageBitmapsForAllPlayers();
    }

    /**
     * Initialize images of players (allPlayers ArrayList) from DB (allPlayers List in the DB)
     */
    private void initImageBitmapsForAllPlayers() {

        // Add picture of all players from DB to all Players
        for (Player p : instance.getAllPlayers()) {

            // Add for each element in allPlayers ArrayList the player image addresse
            allPlayers.add(p.getPicture());

            // Add for each element in allPrices ArrayList the player price
            allPrices.add(p.getPrice());

            // Add for each element in allStatus ArrayList the player lock status
            allStatus.add(p.getIsLocked().getStatus().toString());
        }

        initImageBitmapsForLocks();
        initRecyclerViewForAllPlayers();
    }

    /**
     * Initialize lock status from DB (Lock objects in the DB)
     */
    private void initImageBitmapsForLocks() {
        open = instance.getOpen();
        close = instance.getClose();
    }

    /**
     * Get the players images (allPlayers ArrayList) and add them to the RecyclerView
     */
    private void initRecyclerViewForAllPlayers() {
        RecyclerView recyclerView = findViewById(R.id.rv_store);
        StoreRecyclerViewAdapter adapter = new StoreRecyclerViewAdapter(this, allPlayers, allPrices, allStatus, open, close);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }
}
