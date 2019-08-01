package com.shirzabolotnyklein.tropix.gui;

import android.content.Context;
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
import com.shirzabolotnyklein.tropix.utils.Constants;
import com.shirzabolotnyklein.tropix.utils.StoreLogic;

public class StorePopUpPurchase extends AppCompatActivity {

    private Context context;

    private Button btn_approve;
    private Button btn_cancel;

    private ImageView img_purchase;
    private TextView tv_totalCoinsNewSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_store_popup_purchase);
        context = ApplicationContextProvider.getContext();

        initUI();
        initWindow();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int purchasePlayer = -1;

                StoreLogic.getStoreLogic().setPurchasePlayer(purchasePlayer);
                StoreLogic.getStoreLogic().setWantPurchase(false);

                ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);

                finish();
            }
        });


        btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int purchasePlayer = StoreLogic.getStoreLogic().getPurchasePlayer();

                StoreLogic.getStoreLogic().setWantPurchase(true);

                StoreLogic.getStoreLogic().purchase();

                ((Store) context).initImageBitmapsForPurchasedPlayer(purchasePlayer);
                ((Store) context).initCoins();

                ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);

                finish();
            }
        });


    }

    /**
     * Init UI
     */
    private void initUI() {

        img_purchase = findViewById(R.id.img_purchase);
        tv_totalCoinsNewSum = findViewById(R.id.tv_totalCoinsNewSum);

        // Init image of Player for purchase
        int purchaseImg = Constants.getInstance().getPlayer(StoreLogic.getStoreLogic().getPurchasePlayer()).getPicture();
        img_purchase.setImageResource(purchaseImg);

        // Init total sum of user if would purchase
        int newTotal = StoreLogic.getStoreLogic().calcTotalCoinsWhenPurchase();
        tv_totalCoinsNewSum.setText(String.valueOf(newTotal));

        btn_approve = findViewById(R.id.btn_approve);
        btn_cancel = findViewById(R.id.btn_cancel);

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
