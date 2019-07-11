package com.shirzabolotnyklein.tropix.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.shirzabolotnyklein.tropix.R;

public class ChooseBoard extends AppCompatActivity {

    private Button btn_chooseBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_choose_board);

        btn_chooseBoard = (Button) findViewById(R.id.btn_board_3x3);

        btn_chooseBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseBoard.this, ChoosePlayer.class));
                //launchActivity();
            }
        });


    }
}
