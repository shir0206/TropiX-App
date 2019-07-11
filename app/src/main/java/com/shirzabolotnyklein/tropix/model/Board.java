package com.shirzabolotnyklein.tropix.model;

public class Board {
    /**
     * This class represents the board.
     * The user should choose which of the boards he would like to use. Each board is awarded a different coins score.
     * @param size          The board size
     * @param color         The board size
     * @param maxVicCoins   The sum of the coins that are added to the winner
     */


    //------------------------------------- Parameters -------------------------------------

    private int size;
    private String color;
    private int maxVicCoins;

    //------------------------------------- Constructors -------------------------------------

    public Board(int size, String color, int maxVicCoins) {
        this.size = size;
        this.color = color;
        this.maxVicCoins = maxVicCoins;
    }

    //------------------------------------- Getters & Setters --------------------------------


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxVicCoins() {
        return maxVicCoins;
    }

    public void setMaxVicCoins(int maxVicCoins) {
        this.maxVicCoins = maxVicCoins;
    }
}
