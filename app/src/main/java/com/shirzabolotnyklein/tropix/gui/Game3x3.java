package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.utils.GameControl;

public class Game3x3 extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_rival;
    private ImageView img_my;

    private ImageButton btn_board_3x3_cell_0x0;
    private ImageButton btn_board_3x3_cell_0x1;
    private ImageButton btn_board_3x3_cell_0x2;
    private ImageButton btn_board_3x3_cell_1x0;
    private ImageButton btn_board_3x3_cell_1x1;
    private ImageButton btn_board_3x3_cell_1x2;
    private ImageButton btn_board_3x3_cell_2x0;
    private ImageButton btn_board_3x3_cell_2x1;
    private ImageButton btn_board_3x3_cell_2x2;

    private ImageButton buttons[][] = new ImageButton[3][3];

    private Context context;
    //Vibrator vibrator =  (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_game_3x3);

        initPlayers();

        // Get board size
        int size = GameControl.getGameControl().getGame().getBoard().getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                // Example: for button ID: btn_board_3x3_cell_0x0
                String buttonID = "btn_board_" + size + "x" + size + "_cell_" + i + "x" + j;

                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());

                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        //btn_board_3x3_cell_0x2.setImageResource(GameControl.getGameControl().getWhoseTurn().getPicture());

    }

    private void initBoardButtons() {

        btn_board_3x3_cell_0x0 = findViewById(R.id.btn_board_3x3_cell_0x0);
        btn_board_3x3_cell_0x1 = findViewById(R.id.btn_board_3x3_cell_0x1);
        btn_board_3x3_cell_0x2 = findViewById(R.id.btn_board_3x3_cell_0x2);
        btn_board_3x3_cell_1x0 = findViewById(R.id.btn_board_3x3_cell_1x0);
        btn_board_3x3_cell_1x1 = findViewById(R.id.btn_board_3x3_cell_1x1);
        btn_board_3x3_cell_1x2 = findViewById(R.id.btn_board_3x3_cell_1x2);
        btn_board_3x3_cell_2x0 = findViewById(R.id.btn_board_3x3_cell_2x0);
        btn_board_3x3_cell_2x1 = findViewById(R.id.btn_board_3x3_cell_2x1);
        btn_board_3x3_cell_2x2 = findViewById(R.id.btn_board_3x3_cell_2x2);

    }

    /**
     * Update the User "my Player" & "Rival Player" on the screen
     */
    private void initPlayers() {

        img_rival = findViewById(R.id.img_rival);
        img_rival.setImageResource(GameControl.getGameControl().getGame().getRival().getPicture());

        img_my = findViewById(R.id.img_my);
        img_my.setImageResource(GameControl.getGameControl().getGame().getMy().getPicture());
    }


    @Override
    public void onClick(View view) {

    }

//
//        initBoardButtons();
//        //vibrator.vibrate(50);
//
//        btn_board_3x3_cell_0x0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_board_3x3_cell_0x0.setImageResource(GameControl.getGameControl().getWhoseTurn().getPicture());
//                //vibrator.vibrate(50);
//            }
//        });
//        btn_board_3x3_cell_0x1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_board_3x3_cell_0x1.setImageResource(GameControl.getGameControl().getWhoseTurn().getPicture());
//                //vibrator.vibrate(50);
//            }
//        });
//        btn_board_3x3_cell_0x2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_board_3x3_cell_0x2.setImageResource(GameControl.getGameControl().getWhoseTurn().getPicture());
//                //vibrator.vibrate(50);
//            }
//        });
//        btn_board_3x3_cell_1x0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_board_3x3_cell_1x0.setImageResource(GameControl.getGameControl().getWhoseTurn().getPicture());
//                //vibrator.vibrate(50);
//            }
//        });
//        btn_board_3x3_cell_1x1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_board_3x3_cell_1x1.setImageResource(GameControl.getGameControl().getWhoseTurn().getPicture());
//                //vibrator.vibrate(50);
//            }
//        });
//        btn_board_3x3_cell_1x2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_board_3x3_cell_1x2.setImageResource(GameControl.getGameControl().getWhoseTurn().getPicture());
//                //vibrator.vibrate(50);
//            }
//        });
//        btn_board_3x3_cell_2x0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_board_3x3_cell_2x0.setImageResource(GameControl.getGameControl().getWhoseTurn().getPicture());
//                //vibrator.vibrate(50);
//            }
//        });
//        btn_board_3x3_cell_2x1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_board_3x3_cell_2x1.setImageResource(GameControl.getGameControl().getWhoseTurn().getPicture());
//                //vibrator.vibrate(50);
//            }
//        });
//        btn_board_3x3_cell_2x2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_board_3x3_cell_2x2.setImageResource(GameControl.getGameControl().getWhoseTurn().getPicture());
//                //vibrator.vibrate(50);
//            }
//        });
//
}