package com.shirzabolotnyklein.tropix.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.LockStatus;
import com.shirzabolotnyklein.tropix.model.Player;
import com.shirzabolotnyklein.tropix.utils.Constants;

import java.util.ArrayList;

public class ChoosePlayer extends AppCompatActivity {

    private Button btn_choosePlayer;
    private static final String TAG = "ChoosePlayerActivity";

    private ArrayList<Integer> allRivalPlayers = new ArrayList<>();
    private ArrayList<Integer> allMyPlayers = new ArrayList<>();
    private ArrayList<String> allStatus = new ArrayList<>();

    Constants instance = Constants.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_choose_player);

        Log.d(TAG, "onCreate: Started");

        initImageBitmapsForRivalPlayers();
        initImageBitmapsForMyPlayers();

        btn_choosePlayer = (Button) findViewById(R.id.btn_choose_player);

        btn_choosePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoosePlayer.this, Game3x3.class));
            }
        });
    }

    /**
     * Initialize images of players (allRivalPlayers ArrayList) from DB (allPlayers List in the DB)
     */
    private void initImageBitmapsForRivalPlayers() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps for rival players.");

        // Add picture of all players from DB to all rival Players
        for (Player p : instance.getAllPlayers()) {
            allRivalPlayers.add(p.getPicture());
            allStatus.add(p.getIsLocked().getStatus().toString());
        }

        initRecyclerViewForRivalPlayers();
    }

    /**
     * Get the players images (allRivalPlayers ArrayList) and add them to the RecyclerView
     */
    private void initRecyclerViewForRivalPlayers() {
        Log.d(TAG, "initRecyclerView: init recyclerview for rival players.");
        RecyclerView recyclerView = findViewById(R.id.rv_chooseRival);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, allRivalPlayers, allStatus);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

    /**
     * Initialize images of players (allMyPlayers ArrayList) from DB (allPlayers List in the DB)
     */
    private void initImageBitmapsForMyPlayers() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps for my players.");

        // Add picture of all players from DB to all rival Players
        for (Player p : instance.getAllPlayers()) {
            allMyPlayers.add(p.getPicture());
        }

        initRecyclerViewForMyPlayers();
    }

    /**
     * Get the players images (allMyPlayers ArrayList) and add them to the RecyclerView
     */
    private void initRecyclerViewForMyPlayers() {
        Log.d(TAG, "initRecyclerView: init recyclerview for My players.");
        RecyclerView recyclerView = findViewById(R.id.rv_chooseMy);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, allMyPlayers, allStatus);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

}
