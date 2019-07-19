package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private Context context;
    //Vibrator vibrator =  (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    private Button btn_chooseBoard;
    private Button btn_store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_main);

        Constants instance = Constants.getInstance();

        instance.getAllPlayers();
        instance.getAllBoards();

        btn_chooseBoard = (Button) findViewById(R.id.btn_startGame);
        btn_store = (Button) findViewById(R.id.btn_store);

        btn_chooseBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChooseBoard.class));
                ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);
            }
        });

        btn_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Store.class));
                ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);
            }
        });

    }
}
