package com.shirzabolotnyklein.tropix.utils;

public class StoreLogic {


    //------------------------------------- Singleton ------------------------------------------
    private static StoreLogic instance = null;

    private StoreLogic() {
    }

    public static StoreLogic getStoreLogic() {
        if (instance == null) {
            instance = new StoreLogic();
        }
        return instance;
    }

    //-------------------------------- Store Logic Methods -------------------------------------


    public boolean canPurchase(int price) {
        int playerPrice = price;
        int totalCoins = Constants.getInstance().getTotalCoins();

        if (playerPrice <= totalCoins)
            return true;

        return false;
    }


    private boolean initRecyclerView = false;

    public void initRecyclerView(boolean initRecyclerView) {

        this.initRecyclerView = initRecyclerView;

    }

    public boolean isInitRecyclerView() {
        if (initRecyclerView) {
            this.initRecyclerView = false;

            return !(initRecyclerView);

        }
        return initRecyclerView;

    }


}
