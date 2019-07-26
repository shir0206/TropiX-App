package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.Winner;
import com.shirzabolotnyklein.tropix.utils.Constants;
import com.shirzabolotnyklein.tropix.utils.GameLogic;

import static com.shirzabolotnyklein.tropix.model.Winner.DRAW;
import static com.shirzabolotnyklein.tropix.model.Winner.MY_PLAYER;
import static com.shirzabolotnyklein.tropix.model.Winner.RIVAL_PLAYER;

public class GamePopUp extends AppCompatActivity {

    private static final String TAG = "GamePopUp";
    private Context context;

    private Button btn_popup_store;
    private Button btn_popup_choose_board;

    private ImageView img_end;
    private TextView tv_winMessage;
    private TextView tv_coinsMessage;
    private TextView tv_gameTotalCoinsNewSum;

    private Winner winnerStatus;
    private int winnerId;

    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lay_game_popup_end);


        winnerStatus = GameLogic.getGameLogic().getGame().getWinner();


        context = ApplicationContextProvider.getContext();

        winnerId = this.getIntent().getIntExtra("WINNER", -1);
        initUI();
        initColors(GameLogic.getGameLogic().getGame().getBoard().getSize());

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


        img_end = findViewById(R.id.img_end);
        tv_gameTotalCoinsNewSum = findViewById(R.id.tv_gameTotalCoinsNewSum);

        // Init buttons
        btn_popup_store = findViewById(R.id.btn_popup_store);
        btn_popup_choose_board = findViewById(R.id.btn_popup_choose_board);

        tv_coinsMessage = findViewById(R.id.tv_coinsMessage);

        switch (winnerStatus) {

            case MY_PLAYER:
                img_end.setImageResource(R.drawable.t000_win);

                // Init total sum of user
                tv_coinsMessage.setText(getString(R.string.tv_totalCoinsWin));
                int totalCoins = Constants.getInstance().getTotalCoins();
                tv_gameTotalCoinsNewSum.setText(String.valueOf(totalCoins));
                break;

            case RIVAL_PLAYER:
                img_end.setImageResource(R.drawable.t000_lose);

                tv_coinsMessage.setText(getString(R.string.tv_lose));

                // Don't present total sum
                tv_gameTotalCoinsNewSum.setText("");
                tv_gameTotalCoinsNewSum.setTextSize(0);
                break;

            case DRAW:
                img_end.setImageResource(R.drawable.t000_draw);


                tv_coinsMessage.setText(getString(R.string.tv_lose));

                // Don't present total sum
                tv_gameTotalCoinsNewSum.setText("");
                tv_gameTotalCoinsNewSum.setTextSize(0);
                break;
        }
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


    private void initColors(int size) {

        int sdk = android.os.Build.VERSION.SDK_INT;

        RelativeLayout rl_popup = (RelativeLayout) findViewById(R.id.rl_popup);



        switch (size) {

            case 3:

                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    rl_popup.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_yellow));
                    btn_popup_choose_board.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_yellow));
                    btn_popup_store.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_yellow_border_yellow));

                } else {
                    rl_popup.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_yellow));
                    btn_popup_choose_board.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_yellow));
                    btn_popup_store.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_yellow_border_yellow));
                }


                break;

            case 4:
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    rl_popup.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_pink));
                    btn_popup_choose_board.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_pink));
                    btn_popup_store.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_pink_border_pink));

                } else {
                    rl_popup.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_pink));
                    btn_popup_choose_board.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_pink));
                    btn_popup_store.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_pink_border_pink));
                }

                break;

            case 5:
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    rl_popup.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_red));
                    btn_popup_choose_board.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_red));
                    btn_popup_store.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_red_border_red));

                } else {
                    rl_popup.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_red));
                    btn_popup_choose_board.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_red));
                    btn_popup_store.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_red_border_red));
                }

                break;

            case 6:
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    rl_popup.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_green));
                    btn_popup_choose_board.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_green));
                    btn_popup_store.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drw_fill_green_border_green));

                } else {
                    rl_popup.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_green));
                    btn_popup_choose_board.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_white_border_green));
                    btn_popup_store.setBackground(ContextCompat.getDrawable(context, R.drawable.drw_fill_green_border_green));
                }

                break;
        }
    }

}
