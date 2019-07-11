package com.shirzabolotnyklein.tropix.model;

public class Lock {
    /**
     * This class represents the player lock status.
     * Some of the players have to be purchased before the user could choose them.
     * @param status    Player status (True = The user can use the player. False = the user cannot use the player).
     * @param statusPic Status picture (Picture ID)
     */

    //------------------------------------- Parameters -------------------------------------

    private boolean status;
    private String statusPic;

    //------------------------------------- Constructors -------------------------------------

    public Lock(boolean status, String statusPic) {
        this.status = status;
        this.statusPic = statusPic;
    }

    //------------------------------------- Getters & Setters --------------------------------


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusPic() {
        return statusPic;
    }

    public void setStatusPic(String statusPic) {
        this.statusPic = statusPic;
    }
}
