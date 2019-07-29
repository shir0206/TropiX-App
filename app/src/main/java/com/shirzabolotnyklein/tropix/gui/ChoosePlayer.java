package com.shirzabolotnyklein.tropix.gui;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.Player;
import com.shirzabolotnyklein.tropix.utils.Constants;
import com.shirzabolotnyklein.tropix.utils.GameLogic;

import java.util.ArrayList;

public class ChoosePlayer extends AppCompatActivity {

    private Button btn_choosePlayer;
    private static final String TAG = "ChoosePlayerActivity";

    private ArrayList<Integer> allPlayers = new ArrayList<>();
    private ArrayList<Integer> allId = new ArrayList<>();
    private ArrayList<String> allStatus = new ArrayList<>();

    private ImageView img_myChoice;
    private ImageView img_rivalChoice;

    private Context context;
    protected static Activity choosePlayerActivity;

    private Constants instance = Constants.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_choose_player);

        context= ApplicationContextProvider.getContext();
        choosePlayerActivity = this;

        img_myChoice = findViewById(R.id.img_myChoice);
        img_rivalChoice = findViewById(R.id.img_rivalChoice);

        initImageBitmapsForAllPlayers();

        btn_choosePlayer = (Button) findViewById(R.id.btn_choose_player);

        btn_choosePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Set all user choices game details (board, user player and user rival)
                GameLogic.getGameLogic().setGame();

                // If Players are valid
                if (isValid()) {


                    Intent intent = new Intent(context, ChoosePlayerPopUpAgainstRival.class);

                    //Intent intent = new Intent(ChoosePlayer.this, ChoosePlayerPopUpAgainstRival.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);

                    ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);
                    //finish();
                }
            }
        });
    }

    /**
     * Check if the user choicce of Rival & My Players is valid
     * @return True if valid
     */
    private boolean isValid() {

        // Get the chosen players ID
        int my = GameLogic.getGameLogic().getMy();
        int rival = GameLogic.getGameLogic().getRival();

        // If no Players were chosen, return false
        if (my <= 0 || rival <= 0) {
            String noPlayer = "לא בחרת טרופיXים!";
            Toast.makeText(ChoosePlayer.this, noPlayer, Toast.LENGTH_SHORT).show();
            return false;
        }

        // If same Players were chosen, return false
        else if (my == rival) {
            String samePlayer = "בחרת שני טרופיXים זהים!";
            Toast.makeText(ChoosePlayer.this, samePlayer, Toast.LENGTH_SHORT).show();
            return false;
        }

        // If valid Players were chosen, return true
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
    private void initImageBitmapsForAllPlayers() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps for rival players.");

        // Add picture of all players from DB to all rival Players
        for (Player p : instance.getAllPlayers()) {
            allId.add(p.getId());
            allPlayers.add(p.getPicture());
            allStatus.add(p.getIsLocked().getStatus().toString());
        }

        initRecyclerViewForAllRivalPlayers();
        initRecyclerViewForAllMyPlayers();
    }

    /**
     * Get Rival Players images and add them to the RecyclerView
     */
    private void initRecyclerViewForAllRivalPlayers() {

        String playerType = "RIVAL";
        RecyclerView recyclerView = findViewById(R.id.rv_chooseRival);
        ChoosePlayerRecyclerViewAdapter adapter =
                new ChoosePlayerRecyclerViewAdapter(this, playerType, allId, allPlayers, allStatus);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

    /**
     * Get My Players images and add them to the RecyclerView
     */
    private void initRecyclerViewForAllMyPlayers() {

        String playerType = "MY";
        RecyclerView recyclerView = findViewById(R.id.rv_chooseMy);
        ChoosePlayerRecyclerViewAdapter adapter =
                new ChoosePlayerRecyclerViewAdapter(this, playerType, allId, allPlayers, allStatus);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

    /**
     * Update My Player picture
     */
    protected void updateMyPlayersPic(){

        int myId = GameLogic.getGameLogic().getMy();

        img_myChoice.setImageResource(Constants.getInstance().getPlayer(myId).getPicture());
    }

    /**
     * Update Rival Player picture
     */
    protected void updateRivalPlayersPic(){

        int rivalId =  GameLogic.getGameLogic().getRival();

        img_rivalChoice.setImageResource(Constants.getInstance().getPlayer(rivalId).getPicture());
    }

}
