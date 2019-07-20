package com.shirzabolotnyklein.tropix.gui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.utils.StoreLogic;

public class StorePopUpPurchase extends Activity {

    private Context context;

    Button btn_approve;
    Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_store_popup_purchase);

        context = ApplicationContextProvider.getContext();

        btn_approve = findViewById(R.id.btn_approve);
        btn_cancel = findViewById(R.id.btn_cancel);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .6), (int) (height * .5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);




        /*btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String purchaseSuccessfully = "חבל שלא קנית אותי :(";
                Toast.makeText(context, purchaseSuccessfully, Toast.LENGTH_SHORT).show();


                int purchasePlayer = -1;
                StoreLogic.getStoreLogic().setPurchasePlayer(purchasePlayer);

                finish();
            }
        });


        btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Decrease Coins
                StoreLogic.getStoreLogic().decreaseCoinsWhenPurchase();

                // Update item for purchase
                int purchasePlayer = -1;
                StoreLogic.getStoreLogic().setPurchasePlayer(purchasePlayer);

                String purchaseSuccessfully = "תודה שקנית אותי :)";
                Toast.makeText(context, purchaseSuccessfully, Toast.LENGTH_SHORT).show();


                finish();
            }
        });
*/

    }


}
