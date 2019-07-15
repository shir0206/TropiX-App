package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.utils.GameControl;

public class ChooseBoard extends AppCompatActivity {

    private Button btn_chooseBoard3x3;
    private Button btn_chooseBoard4x4;
    private Button btn_chooseBoard5x5;
    private Button btn_chooseBoard6x6;
    private Context context;
    //Vibrator vibrator =  (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_choose_board);

        btn_chooseBoard3x3 = (Button) findViewById(R.id.btn_board_3x3);
        btn_chooseBoard4x4 = (Button) findViewById(R.id.btn_board_4x4);
        btn_chooseBoard5x5 = (Button) findViewById(R.id.btn_board_5x5);
        btn_chooseBoard6x6 = (Button) findViewById(R.id.btn_board_6x6);

        btn_chooseBoard3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //vibrator.vibrate(50);
                GameControl.getGameControl().setBoard(3);

                startActivity(new Intent(ChooseBoard.this, ChoosePlayer.class));
                //launchActivity();
            }
        });

    }
}
