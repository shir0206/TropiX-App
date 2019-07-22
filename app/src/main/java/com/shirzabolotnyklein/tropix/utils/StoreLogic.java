package com.shirzabolotnyklein.tropix.utils;

import android.content.Context;
import android.widget.Toast;

import com.shirzabolotnyklein.tropix.gui.ApplicationContextProvider;
import com.shirzabolotnyklein.tropix.model.Lock;

public class StoreLogic {


    //------------------------------------- Singleton ------------------------------------------
    private static StoreLogic instance = null;

    Context context;

    private StoreLogic() {
    }

    public static StoreLogic getStoreLogic() {
        if (instance == null) {
            instance = new StoreLogic();
            //this.context = ApplicationContextProvider.getContext();

        }
        return instance;
    }

    //-------------------------------- Store Logic Methods -------------------------------------


    int purchasePlayer;

    public void setPurchasePlayer(int purchasePlayer) {
        this.purchasePlayer = purchasePlayer;
    }

    /**
     * Check is the Player can be purchased, by comparing the current total points and the player price.
     *
     * @param price The price of the Player.
     * @return
     */
    public boolean canPurchase(int price) {
        int playerPrice = price;
        int totalCoins = Constants.getInstance().getTotalCoins();
        if (playerPrice <= totalCoins) {
            //decreaseCoinsWhenPurchase(price);
            return true;
        }
        return false;
    }

    /**
     * When the player is purchased, decrease the total coins of the user.
     */
    public void decreaseCoinsWhenPurchase() {

        if (purchasePlayer > 0) {
            int playerPrice = Constants.getInstance().getPlayer(purchasePlayer).getPrice();

            int totalCoins = Constants.getInstance().getTotalCoins();

            int decTotalCoins = totalCoins - playerPrice;

            Constants.getInstance().setTotalCoins(decTotalCoins);
        }

    }


    /**
     *
     */
    public void purchase(){

        // Update player status to be open
        Lock open = Constants.getInstance().getOpen();
        Constants.getInstance().getPlayer(purchasePlayer).setIslocked(open);

        // Update file
        Constants.getInstance().editFilePlayerStatus(purchasePlayer);

        // Decrease points
        decreaseCoinsWhenPurchase();

        // Init purchasePlayer
        setPurchasePlayer(-1);

    }
}
