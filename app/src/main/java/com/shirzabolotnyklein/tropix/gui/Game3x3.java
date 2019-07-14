package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;

import com.shirzabolotnyklein.tropix.R;

public class Game3x3 extends AppCompatActivity {

    private Context context;
    //Vibrator vibrator =  (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_game_3x3);
        //vibrator.vibrate(50);
    }
}