package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.Player;
import com.shirzabolotnyklein.tropix.utils.Constants;
import com.shirzabolotnyklein.tropix.utils.GameControl;

import java.util.ArrayList;

public class ChoosePlayer extends AppCompatActivity {

    //Vibrator vibrator =  (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    private Button btn_choosePlayer;
    private static final String TAG = "ChoosePlayerActivity";

    private ArrayList<Integer> allRivalPlayers = new ArrayList<>();
    private ArrayList<Integer> allRivalId = new ArrayList<>();
    private ArrayList<String> allRivalStatus = new ArrayList<>();

    private ArrayList<Integer> allMyPlayers = new ArrayList<>();
    private ArrayList<Integer> allMyId = new ArrayList<>();
    private ArrayList<String> allMyStatus = new ArrayList<>();


    Constants instance = Constants.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_choose_player);

        initImageBitmapsForRivalPlayers();
        initImageBitmapsForMyPlayers();

        btn_choosePlayer = (Button) findViewById(R.id.btn_choose_player);

        btn_choosePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //vibrator.vibrate(50);

                // Set all user choices game details (board, user player and user rival)
                GameControl.getGameControl().setGame();

                // If Players are valid
                if (isValid()) {

                    // Switch case which board should be opened
                    switch (GameControl.getGameControl().getGame().getBoard().getSize()) {
                        case 3:
                            startActivity(new Intent(ChoosePlayer.this, Game3x3.class));
                            ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);
                            finish();
                        case 4:
                            //startActivity(new Intent(ChoosePlayer.this, Game4x4.class));
                        case 5:
                            //startActivity(new Intent(ChoosePlayer.this, Game5x5.class));
                        case 6:
                            //startActivity(new Intent(ChoosePlayer.this, Game6x6.class));
                    }
                }
            }
        });
    }

    private boolean isValid() {

        int my = GameControl.getGameControl().getMy();
        int rival = GameControl.getGameControl().getRival();

        if (my <= 0 || rival <= 0) {
            String noPlayer = "לא בחרת טרופיXים!";
            Toast.makeText(ChoosePlayer.this, noPlayer, Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (my == rival) {
            String samePlayer = "בחרת שני טרופיXים זהים!";
            Toast.makeText(ChoosePlayer.this, samePlayer, Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (my > 0 && rival > 0) {
            String choosePlayer = "נבחרו הטרופיXים :)";
            Toast.makeText(ChoosePlayer.this, choosePlayer, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    /**
     * Initialize images of players (allRivalPlayers ArrayList) from DB (allPlayers List in the DB)
     */
    private void initImageBitmapsForRivalPlayers() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps for rival players.");

        // Add picture of all players from DB to all rival Players
        for (Player p : instance.getAllPlayers()) {
            allRivalId.add(p.getId());
            allRivalPlayers.add(p.getPicture());
            allRivalStatus.add(p.getIsLocked().getStatus().toString());
        }

        initRecyclerViewForRivalPlayers();
    }

    /**
     * Get the players images (allRivalPlayers ArrayList) and add them to the RecyclerView
     */
    private void initRecyclerViewForRivalPlayers() {
        RecyclerView recyclerView = findViewById(R.id.rv_chooseRival);
        ChooseRivalPlayerRecyclerViewAdapter adapter =
                new ChooseRivalPlayerRecyclerViewAdapter(this, allRivalId, allRivalPlayers, allRivalStatus);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

    /**
     * Initialize images of players (allMyPlayers ArrayList) from DB (allPlayers List in the DB)
     */
    private void initImageBitmapsForMyPlayers() {
        // Add picture of all players from DB to all rival Players
        for (Player p : instance.getAllPlayers()) {
            allMyId.add(p.getId());
            allMyPlayers.add(p.getPicture());
            allMyStatus.add(p.getIsLocked().getStatus().toString());
        }

        initRecyclerViewForMyPlayers();
    }

    /**
     * Get the players images (allMyPlayers ArrayList) and add them to the RecyclerView
     */
    private void initRecyclerViewForMyPlayers() {
        RecyclerView recyclerView = findViewById(R.id.rv_chooseMy);
        ChooseMyPlayerRecyclerViewAdapter adapter =
                new ChooseMyPlayerRecyclerViewAdapter(this, allMyId, allMyPlayers, allMyStatus);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

}
