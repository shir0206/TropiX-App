package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.Lock;
import com.shirzabolotnyklein.tropix.model.Player;
import com.shirzabolotnyklein.tropix.utils.Constants;

import java.util.ArrayList;

public class Store extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tv_coinsSum;
    private Constants instance = Constants.getInstance();

    private Context context;

    private ArrayList<Integer> allPlayers = new ArrayList<>(); // ArrayList of all players image addresses
    private ArrayList<Integer> allPrices = new ArrayList<>();  // ArrayList of all players prices
    private ArrayList<String> allStatus = new ArrayList<>(); // ArrayList of all players status
    private ArrayList<Integer> allId = new ArrayList<>(); // ArrayList of all players ID

    private Lock open;
    private Lock close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_store);

        initImageBitmapsForAllPlayers();
        initCoins();

    }

    /**
     * Initialize images of players (allPlayers ArrayList) from DB (allPlayers List in the DB)
     */
    protected void initImageBitmapsForAllPlayers() {

        // Add picture of all players from DB to all Players
        for (Player p : instance.getAllPlayers()) {

            // Add for each element in allPlayers ArrayList the player image addresse
            allPlayers.add(p.getId() - 1, p.getPicture());

            // Add for each element in allPrices ArrayList the player price
            allPrices.add(p.getId() - 1, p.getPrice());

            // Add for each element in allStatus ArrayList the player lock status
            allStatus.add(p.getId() - 1, p.getIsLocked().getStatus().toString());

            // Add for each element in allId ArrayList the player ID
            allId.add(p.getId() - 1, p.getId());

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
    protected void initRecyclerViewForAllPlayers() {
        recyclerView = findViewById(R.id.rv_store);
        StoreRecyclerViewAdapter adapter = new StoreRecyclerViewAdapter(this, allPlayers, allPrices, allStatus, allId, open, close);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

    protected void initCoins() {

        tv_coinsSum = findViewById(R.id.tv_coinsSum);

        int totalCoins = Constants.getInstance().getTotalCoins();

        tv_coinsSum.setText(Integer.toString(totalCoins));
    }


    /**
     * Initialize images of purchased player (allPlayers ArrayList) from DB (allPlayers List in the DB)
     */
    protected void initImageBitmapsForPurchasedPlayer(int purchasePlayer) {

        // Add picture of all players from DB to all Players
        Player p = Constants.getInstance().getPlayer(purchasePlayer);

        // Add for each element in allPlayers ArrayList the player image addresse
        allPlayers.set(p.getId() - 1, p.getPicture());

        // Add for each element in allPrices ArrayList the player price
        allPrices.set(p.getId() - 1, p.getPrice());

        // Add for each element in allStatus ArrayList the player lock status
        allStatus.set(p.getId() - 1, p.getIsLocked().getStatus().toString());

        // Add for each element in allId ArrayList the player ID
        allId.set(p.getId() - 1, p.getId());

        initImageBitmapsForLocks();
        initRecyclerViewForAllPlayers();
    }

}
