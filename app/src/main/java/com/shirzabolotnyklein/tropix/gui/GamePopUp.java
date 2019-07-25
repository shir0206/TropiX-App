package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.Winner;
import com.shirzabolotnyklein.tropix.utils.Constants;
import com.shirzabolotnyklein.tropix.utils.GameLogic;

public class GamePopUp extends AppCompatActivity {

    private static final String TAG = "GamePopUp";
    private Context context;

    private Button btn_popup_store;
    private Button btn_popup_choose_board;

    private ImageView img_my;
    private TextView tv_totalCoinsNewSum;
    private int winnerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Winner winner = GameLogic.getGameLogic().getGame().getWinner();

        switch (winner) {

            case MY_PLAYER:
                setContentView(R.layout.lay_game_popup_win);
                break;
            case RIVAL_PLAYER:
                setContentView(R.layout.lay_game_popup_lose);
                break;
            case DRAW:
                setContentView(R.layout.lay_game_popup_draw);
                break;
        }

        context = ApplicationContextProvider.getContext();

        winnerId = this.getIntent().getIntExtra("WINNER", -1);
        initUI();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .65));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);

        btn_popup_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String store = "מעניין איזה טרופיXים כיפיים אפשר עוד לקנות ;)";
                Toast.makeText(context, store, Toast.LENGTH_SHORT).show();
                ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);

                Intent intent = new Intent(context, Store.class);
                context.startActivity(intent);

                GameActivity.gameActivity.finish();

                finish();

            }
        });


        btn_popup_choose_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String chooseBoard = "יש, עוד משחקיX!";
                Toast.makeText(context, chooseBoard, Toast.LENGTH_SHORT).show();
                ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);

                Intent intent = new Intent(context, ChooseBoard.class);
                context.startActivity(intent);

                GameActivity.gameActivity.finish();

                finish();
            }
        });


    }

    private void initUI() {

        img_my = findViewById(R.id.img_winner);
        tv_totalCoinsNewSum = findViewById(R.id.tv_totalCoinsNewSum);

        // Init image of Player
        int winner = Constants.getInstance().getPlayer(winnerId).getPicture();
        img_my.setImageResource(winner);

        // Init total sum of user if would purchase
      //  int newTotal = GameLogic.getGameControl().calcTotalCoinsWhenWin();
       // tv_totalCoinsNewSum.setText(String.valueOf(newTotal));


        int totalCoins = Constants.getInstance().getTotalCoins();
        tv_totalCoinsNewSum.setText(Integer.toString(totalCoins));


        btn_popup_store = findViewById(R.id.btn_popup_store);
        btn_popup_choose_board = findViewById(R.id.btn_popup_choose_board);

    }

    /**
     * Refresh game, reset game control class and return to MainActivity
     */
    private void refreshGame() {
        GameLogic.getGameLogic().resetGame();

        Intent intent = new Intent(GamePopUp.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

        GamePopUp.this.finish();
    }

}
