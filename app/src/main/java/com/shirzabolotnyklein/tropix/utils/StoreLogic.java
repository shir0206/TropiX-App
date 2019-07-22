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


    boolean wantPurchase;
    int purchasePlayer;


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

        int decTotalCoins = calcTotalCoinsWhenPurchase();
        Constants.getInstance().setTotalCoins(decTotalCoins);
    }


    public int calcTotalCoinsWhenPurchase() {

        int playerPrice = Constants.getInstance().getPlayer(purchasePlayer).getPrice();

        int totalCoins = Constants.getInstance().getTotalCoins();

        int decTotalCoins = totalCoins - playerPrice;

        return decTotalCoins;
    }


    /**
     *
     */
    public void purchase() {

        if ((purchasePlayer > 0) && wantPurchase) {

            // Update player status to be open
            Lock open = Constants.getInstance().getOpen();
            Constants.getInstance().getPlayer(purchasePlayer).setIslocked(open);

            // Update file
            Constants.getInstance().editFilePlayerStatus(purchasePlayer);

            // Decrease points
            decreaseCoinsWhenPurchase();
        }

    }

    //-------------------------------- Getters & Setters -------------------------------------


    public void setWantPurchase(boolean wantPurchase) {
        this.wantPurchase = wantPurchase;
    }

    public void setPurchasePlayer(int purchasePlayer) {
        this.purchasePlayer = purchasePlayer;
    }

    public boolean isWantPurchase() {
        return wantPurchase;
    }

    public int getPurchasePlayer() {
        return purchasePlayer;
    }
}
