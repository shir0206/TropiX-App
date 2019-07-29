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

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.Player;
import com.shirzabolotnyklein.tropix.utils.Constants;
import com.shirzabolotnyklein.tropix.utils.GameLogic;

import java.util.Random;

public class ChoosePlayerPopUpFirst extends AppCompatActivity {

    Context context;

    private Button btn_switch;
    private Button btn_start;
    private ImageView img_first;
    private ImageView img_second;
    private TextView tv_first_player;
    private TextView tv_second_player;
    private Player my;
    private Player rival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_choose_player_first);
        context = ApplicationContextProvider.getContext();

        btn_switch = findViewById(R.id.btn_switch);
        btn_start = findViewById(R.id.btn_start);

        initWindow();

        my = Constants.getInstance().getPlayer(GameLogic.getGameLogic().getMy());
        rival = Constants.getInstance().getPlayer(GameLogic.getGameLogic().getRival());

        initUI();

        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Switch first player
                if (GameLogic.getGameLogic().getFirst() == my.getId()) {
                    setRivalPlayerFirst();
                } else {
                    setMyPlayerFirst();
                }
                ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);

            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);

                Intent intent = new Intent(context, GameActivity.class);
                startActivity(intent);

                ChoosePlayer.choosePlayerActivity.finish();

                finish();
            }
        });
    }

    private void initUI() {

        img_first = findViewById(R.id.img_first);
        tv_first_player = findViewById(R.id.tv_first_player);

        img_second = findViewById(R.id.img_second);
        tv_second_player = findViewById(R.id.tv_second_player);


        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(2);
        if (random == 0) {
            setRivalPlayerFirst();
        } else {
            setMyPlayerFirst();
        }
    }

    private void setMyPlayerFirst() {

        // Init My Player as first
        img_first.setImageResource(my.getPicture());
        tv_first_player.setText(R.string.tv_me);

        // Init Rival Player as second
        img_second.setImageResource(rival.getPicture());
        tv_second_player.setText(R.string.tv_rival);

        GameLogic.getGameLogic().setFirst(my.getId());
    }

    private void setRivalPlayerFirst() {

        // Init Rival Player as first
        img_first.setImageResource(rival.getPicture());
        tv_first_player.setText(R.string.tv_rival);

        // Init My Player as second
        img_second.setImageResource(my.getPicture());
        tv_second_player.setText(R.string.tv_me);

        GameLogic.getGameLogic().setFirst(rival.getId());
    }


    /**
     * Init pop-up window properties
     */
    private void initWindow() {
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
    }
}
