package com.shirzabolotnyklein.tropix.utils;

import com.shirzabolotnyklein.tropix.model.Board;
import com.shirzabolotnyklein.tropix.model.Game;
import com.shirzabolotnyklein.tropix.model.Player;

public class GameControl {

    //------------------------------------- Singleton ------------------------------------------
    private static GameControl instance = null;

    private GameControl() { }

    public static GameControl getGameControl() {
        if (instance == null) {
            instance = new GameControl();
        }
        return instance;
    }

    //-------------------------------- Current Game Parameters -------------------------------------


    private Player my;
    private Player rival;
    private Board board;
    private int gameCoins;




    Game game;


    /**
     * Update "My Player" parameter of the game according to the user choice.
     * @param myId Receive "My Player" ID according to the user choice.
     */
    public void setMy(int myId) {
        // Get the Player Object from the DB
        this.my = Constants.getInstance().getPlayer(myId);
    }

    /**
     * Update "Rival Player" parameter of the game according to the user choice.
     * @param rivalId Receive "Rival Player" ID according to the user choice.
     */
    public void setRival(int rivalId) {
        // Get the Player Object from the DB
        this.rival = Constants.getInstance().getPlayer(rivalId);
    }

    /**
     * Update "Board Type" parameter of the game according to the user choice.
     * @param boardType Receive "Board Type" ID according to the user choice.
     */
    public void setBoard(int boardType) {
        this.board = Constants.getInstance().getBoard(boardType);
    }

    public void setGameCoins(int gameCoins) {
        this.gameCoins = gameCoins;
    }





    public void setGame() {
        setGameCoins(0);

        this.game = new Game(my, rival, board, gameCoins);
    }


    public Game getGame() {
        return game;
    }

}
