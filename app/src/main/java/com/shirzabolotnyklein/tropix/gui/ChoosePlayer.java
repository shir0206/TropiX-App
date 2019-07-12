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

import java.util.ArrayList;

public class ChoosePlayer extends AppCompatActivity {

    private Button btn_choosePlayer;
    private static final String TAG = "ChoosePlayerActivity";

    private ArrayList<Integer> allRivalPlayers = new ArrayList<>();
    private ArrayList<Integer> allMyPlayers = new ArrayList<>();

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
                //launchActivity();
            }
        });

    }

    private void initImageBitmapsForRivalPlayers() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps for rival players.");

        allRivalPlayers.add(R.drawable.t001_palm);
        allRivalPlayers.add(R.drawable.t002_iced_tea);
        allRivalPlayers.add(R.drawable.t003_sunglasses);
        allRivalPlayers.add(R.drawable.t004_starfish);
        allRivalPlayers.add(R.drawable.t005_banana);
        allRivalPlayers.add(R.drawable.t006_beach_ball);
        allRivalPlayers.add(R.drawable.t007_ice_cream);
        allRivalPlayers.add(R.drawable.t008_tiki);
        allRivalPlayers.add(R.drawable.t009_dolphin);
        allRivalPlayers.add(R.drawable.t010_lemon);
        allRivalPlayers.add(R.drawable.t011_flamingo);
        allRivalPlayers.add(R.drawable.t012_shack);
        allRivalPlayers.add(R.drawable.t013_sun_cream);
        allRivalPlayers.add(R.drawable.t014_flower);
        allRivalPlayers.add(R.drawable.t015_cactus);
        allRivalPlayers.add(R.drawable.t016_volcano);
        allRivalPlayers.add(R.drawable.t017_bucket);
        allRivalPlayers.add(R.drawable.t018_beach);
        allRivalPlayers.add(R.drawable.t019_cherries);
        allRivalPlayers.add(R.drawable.t020_sunset);
        allRivalPlayers.add(R.drawable.t021_yatch);
        allRivalPlayers.add(R.drawable.t022_pamela);
        allRivalPlayers.add(R.drawable.t023_flower);
        allRivalPlayers.add(R.drawable.t024_hammock);
        allRivalPlayers.add(R.drawable.t025_slippers);
        allRivalPlayers.add(R.drawable.t026_palm_tree);
        allRivalPlayers.add(R.drawable.t027_coconut);
        allRivalPlayers.add(R.drawable.t028_sun);
        allRivalPlayers.add(R.drawable.t029_macaw);
        allRivalPlayers.add(R.drawable.t030_necklace);
        allRivalPlayers.add(R.drawable.t031_pineapple);
        allRivalPlayers.add(R.drawable.t032_shell);
        allRivalPlayers.add(R.drawable.t033_watermelon);
        allRivalPlayers.add(R.drawable.t034_ice_cream);
        allRivalPlayers.add(R.drawable.t035_leaf);
        allRivalPlayers.add(R.drawable.t036_toucan);
        allRivalPlayers.add(R.drawable.t037_flower);
        allRivalPlayers.add(R.drawable.t038_popsicle);
        allRivalPlayers.add(R.drawable.t039_flower);
        allRivalPlayers.add(R.drawable.t040_mango);
        allRivalPlayers.add(R.drawable.t041_cocktail);
        allRivalPlayers.add(R.drawable.t042_surfboard);
        allRivalPlayers.add(R.drawable.t043_shell);
        allRivalPlayers.add(R.drawable.t044_jellyfish);
        allRivalPlayers.add(R.drawable.t045_wave);
        allRivalPlayers.add(R.drawable.t046_crab);
        allRivalPlayers.add(R.drawable.t047_clown_fish);
        allRivalPlayers.add(R.drawable.t048_lifesaver);
        allRivalPlayers.add(R.drawable.t049_shirt);
        allRivalPlayers.add(R.drawable.t050_compass);

        initRecyclerViewForRivalPlayers();
    }

    private void initRecyclerViewForRivalPlayers() {
        Log.d(TAG, "initRecyclerView: init recyclerview for rival players.");
        RecyclerView recyclerView = findViewById(R.id.rv_chooseRival);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, allRivalPlayers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

    private void initImageBitmapsForMyPlayers() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps for my players.");

        allMyPlayers.add(R.drawable.t001_palm);
        allMyPlayers.add(R.drawable.t002_iced_tea);
        allMyPlayers.add(R.drawable.t003_sunglasses);
        allMyPlayers.add(R.drawable.t004_starfish);
        allMyPlayers.add(R.drawable.t005_banana);
        allMyPlayers.add(R.drawable.t006_beach_ball);
        allMyPlayers.add(R.drawable.t007_ice_cream);
        allMyPlayers.add(R.drawable.t008_tiki);
        allMyPlayers.add(R.drawable.t009_dolphin);
        allMyPlayers.add(R.drawable.t010_lemon);
        allMyPlayers.add(R.drawable.t011_flamingo);
        allMyPlayers.add(R.drawable.t012_shack);
        allMyPlayers.add(R.drawable.t013_sun_cream);
        allMyPlayers.add(R.drawable.t014_flower);
        allMyPlayers.add(R.drawable.t015_cactus);
        allMyPlayers.add(R.drawable.t016_volcano);
        allMyPlayers.add(R.drawable.t017_bucket);
        allMyPlayers.add(R.drawable.t018_beach);
        allMyPlayers.add(R.drawable.t019_cherries);
        allMyPlayers.add(R.drawable.t020_sunset);
        allMyPlayers.add(R.drawable.t021_yatch);
        allMyPlayers.add(R.drawable.t022_pamela);
        allMyPlayers.add(R.drawable.t023_flower);
        allMyPlayers.add(R.drawable.t024_hammock);
        allMyPlayers.add(R.drawable.t025_slippers);
        allMyPlayers.add(R.drawable.t026_palm_tree);
        allMyPlayers.add(R.drawable.t027_coconut);
        allMyPlayers.add(R.drawable.t028_sun);
        allMyPlayers.add(R.drawable.t029_macaw);
        allMyPlayers.add(R.drawable.t030_necklace);
        allMyPlayers.add(R.drawable.t031_pineapple);
        allMyPlayers.add(R.drawable.t032_shell);
        allMyPlayers.add(R.drawable.t033_watermelon);
        allMyPlayers.add(R.drawable.t034_ice_cream);
        allMyPlayers.add(R.drawable.t035_leaf);
        allMyPlayers.add(R.drawable.t036_toucan);
        allMyPlayers.add(R.drawable.t037_flower);
        allMyPlayers.add(R.drawable.t038_popsicle);
        allMyPlayers.add(R.drawable.t039_flower);
        allMyPlayers.add(R.drawable.t040_mango);
        allMyPlayers.add(R.drawable.t041_cocktail);
        allMyPlayers.add(R.drawable.t042_surfboard);
        allMyPlayers.add(R.drawable.t043_shell);
        allMyPlayers.add(R.drawable.t044_jellyfish);
        allMyPlayers.add(R.drawable.t045_wave);
        allMyPlayers.add(R.drawable.t046_crab);
        allMyPlayers.add(R.drawable.t047_clown_fish);
        allMyPlayers.add(R.drawable.t048_lifesaver);
        allMyPlayers.add(R.drawable.t049_shirt);
        allMyPlayers.add(R.drawable.t050_compass);

        initRecyclerViewForMyPlayers();
    }

    private void initRecyclerViewForMyPlayers() {
        Log.d(TAG, "initRecyclerView: init recyclerview for My players.");
        RecyclerView recyclerView = findViewById(R.id.rv_chooseMy);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, allMyPlayers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

}
