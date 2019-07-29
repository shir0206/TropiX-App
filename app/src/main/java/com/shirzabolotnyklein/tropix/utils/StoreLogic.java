package com.shirzabolotnyklein.tropix.utils;

import android.content.Context;

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

        }
        return instance;
    }

    //-------------------------------- Store Parameters -------------------------------------

    /**
     * Is the user want to purchase the Player
     */
    boolean wantPurchase;

    /**
     * ID of the Player that the user want to purchased
     */
    int purchasePlayer;

    //-------------------------------- Store Logic Methods -------------------------------------


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

    /**
     * Calculate the total sum of coins if that Player would be purchased
     * @return
     */
    public int calcTotalCoinsWhenPurchase() {

        int playerPrice = Constants.getInstance().getPlayer(purchasePlayer).getPrice();

        int totalCoins = Constants.getInstance().getTotalCoins();

        int decTotalCoins = totalCoins - playerPrice;

        return decTotalCoins;
    }

    /**
     * Purchase the player, decrease the coins  and update in the file
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

    public int getPurchasePlayer() {
        return purchasePlayer;
    }
}
