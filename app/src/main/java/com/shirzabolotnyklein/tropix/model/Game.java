package com.shirzabolotnyklein.tropix.model;

public class Game {
    /**
     * This class represents the game.
     * In each game there are 2 players (the user and the computer).
     * The user should choose which of the players would represent his moves and which the computer moves.
     * The user should choose which of the boards he would like to use. Each board is awarded a different coins score.
     *
     * @param user      The user player
     * @param rival     The rival player
     * @param board     The chosen board of the game
     * @param gameCoins The amount of coins in the game
     */

    //------------------------------------- Parameters -------------------------------------

    private Player user;
    private Player rival;
    private Board board;
    private int gameCoins;

    //------------------------------------- Constructors -------------------------------------

    public Game(Player user, Player rival, Board board, int gameCoins) {
        this.user = user;
        this.rival = rival;
        this.board = board;
        this.gameCoins = gameCoins;
    }

    //------------------------------------- Getters & Setters --------------------------------


    public Player getUser() {
        return user;
    }

    public void setUser(Player user) {
        this.user = user;
    }

    public Player getRival() {
        return rival;
    }

    public void setRival(Player rival) {
        this.rival = rival;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getGameCoins() {
        return gameCoins;
    }

    public void setGameCoins(int gameCoins) {
        this.gameCoins = gameCoins;
    }
}
