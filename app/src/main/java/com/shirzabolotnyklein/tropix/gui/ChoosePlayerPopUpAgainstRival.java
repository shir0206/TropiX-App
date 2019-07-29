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
import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.utils.GameLogic;

public class ChoosePlayerPopUpAgainstRival extends AppCompatActivity {

    Context context;

    private Button btn_friend;
    private Button btn_computer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_choose_player_popup_rival_type);

        btn_friend = findViewById(R.id.btn_friend);
        btn_computer = findViewById(R.id.btn_computer);

        context = ApplicationContextProvider.getContext();

        initWindow();

        btn_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GameLogic.getGameLogic().setAgainstComputer(false);

                ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);

                Intent intent = new Intent(context, ChoosePlayerPopUpFirst.class);
                startActivity(intent);

                finish();
            }
        });

        btn_computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GameLogic.getGameLogic().setAgainstComputer(true);

                ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);

                Intent intent = new Intent(context, ChoosePlayerPopUpFirst.class);
                startActivity(intent);

                finish();
            }
        });
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
