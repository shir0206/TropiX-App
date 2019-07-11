package com.shirzabolotnyklein.tropix.model;


public class Player {
    /**
     * This class represents the player in the game.
     * In each game there are 2 players (the user and the computer).
     * The user should choose which of the players would represent his moves and which the computer moves.
     * @param id        Player ID
     * @param name      Player name
     * @param price     Player price (Some of the players have to be purchased before the user could choose them)
     * @param picture   Player picture (picture ID)
     * @param lock      Player lock (Some of the players are locked, in order to open the lock the user should purchase them)
     */

    //------------------------------------- Parameters -------------------------------------

    private int id;
    private String name;
    private int price;
    private String picture;
    private Lock lock;

    //------------------------------------- Constructors -------------------------------------

    public Player(int id, String name, int price, String picture, Lock lock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.lock = lock;
    }

    //------------------------------------- Getters & Setters --------------------------------


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
