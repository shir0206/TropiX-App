package com.shirzabolotnyklein.tropix.model;

public class Lock {


    /**
     * This class represents the player lock status.
     * Some of the players have to be purchased before the user could choose them.
     *
     * @param status    Player status (True = The user can use the player. False = the user cannot use the player).
     * @param statusPic Status picture (Picture ID)
     */

    //------------------------------------- Parameters -------------------------------------

    private LockStatus status;
    private int statusPic;

    //------------------------------------- Constructors -------------------------------------

    public Lock(LockStatus status, int statusPic) {
        this.status = status;
        this.statusPic = statusPic;
    }

    //------------------------------------- Getters & Setters --------------------------------


    public LockStatus getStatus() {
        return status;
    }

    public void setStatus(LockStatus status) {
        this.status = status;
    }

    public int getStatusPic() {
        return statusPic;
    }

    public void setStatusPic(int statusPic) {
        this.statusPic = statusPic;
    }
}
