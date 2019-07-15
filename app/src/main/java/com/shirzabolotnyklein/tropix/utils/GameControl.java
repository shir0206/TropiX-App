package com.shirzabolotnyklein.tropix.utils;

import android.widget.ImageButton;

import com.shirzabolotnyklein.tropix.model.Board;
import com.shirzabolotnyklein.tropix.model.Game;
import com.shirzabolotnyklein.tropix.model.Player;

import java.util.ArrayList;

public class GameControl {

    //------------------------------------- Singleton ------------------------------------------
    private static GameControl instance = null;

    private GameControl() {
    }

    public static GameControl getGameControl() {
        if (instance == null) {
            instance = new GameControl();
        }
        return instance;
    }

    //-------------------------------- Current Game Parameters -------------------------------------

    /**
     * The user choice of My Player
     */
    private Player my;

    /**
     * The user choice of Rival Player
     */
    private Player rival;

    /**
     * The user choice of size of board game
     */
    private Board board;

    /**
     * The current coins that the user earned during the current game.
     */
    private int gameCoins;

    /**
     * The current game information
     */
    private Game game;

    /**
     * The player ID whose should be next turn.
     */
    private int whoseTurn = -1;

    /**
     * The sum of the moves that already taken / the sum of the cells that are already filled.
     * In every turn, increase with 1 the moves count.
     */
    private int movesCount;

    /**
     * The matrix board represents in each cell:
     *   -1               If the cell is empty
     *   My Player ID     If the user put "My Player" in that cell
     *   Rival Player ID  If the rival put "Rival Player" in that cell
     */
    private ArrayList<ArrayList<Integer>> boardMatrix;

    //-------------------------------- Game Logic Methods -------------------------------------

    /**
     * The matrix board represents in each cell:
     *   -1               If the cell is empty
     *   My Player ID     If the user put "My Player" in that cell
     *   Rival Player ID  If the rival put "Rival Player" in that cell
     * -
     * To check if there is a winner and who is the winner,
     * calculate the sum of each player ID, from i = 0 to i = board size (sum = ID * board size)
     * If the sum of a row / col / diagonal equals to one of the sums, there is a win.
     *
     * @return if there is a winner, return the winner ID, else return -1.
     */
    public int checkWinner() {

        int sumNoWin = -1 * board.getSize();
        int sumMyPlayerWin = my.getId() * board.getSize();
        int sumRivalPlayerWin = rival.getId() * board.getSize();

        int sumRow = 0;
        int sumCol = 0;
        int sumDiagonalLeft = 0;
        int sumDiagonalRight = 0;

        // Check all rows
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                sumRow += boardMatrix.get(i).get(j);
                sumCol += boardMatrix.get(j).get(i);
                sumDiagonalLeft += boardMatrix.get(i).get(i);
                sumDiagonalRight += boardMatrix.get(j).get(j);
            }

            // At the end of each row, check row sum
            // If the sum of the row / col / diagonals equal to the sum of my player ID, return my player ID
            if ((sumRow == sumMyPlayerWin)
                    || (sumCol == sumMyPlayerWin)
                    || (sumDiagonalLeft == sumMyPlayerWin)
                    || (sumDiagonalRight == sumMyPlayerWin)) {
                return my.getId();
            }

            // If the sum of the row / col / diagonals equal to the sum of rival player ID, return rival player ID
            else if ((sumRow == sumRivalPlayerWin)
                    || (sumCol == sumRivalPlayerWin)
                    || (sumDiagonalLeft == sumRivalPlayerWin)
                    || (sumDiagonalRight == sumRivalPlayerWin)) {
                return rival.getId();
            }
        }

        // If there is no winner, return -1
        return -1;
    }

    /**
     * Check who played last and set whose turn now.
     * In every turn, increase with 1 the moves count (the sum of the cells that are already filled).
     *
     * @return whoseTurn
     */
    public int getWhoseTurn() {

        // If this is the first turn, start with My Player and Initiate the moves count to 0.
        if (whoseTurn == -1) {
            whoseTurn = my.getId();
            movesCount = 0;
        }

        // If My Player was last, set the next turn to Rival Player, increase board coins for My Player, increase the moves count.
        else if (whoseTurn == my.getId()) {
            whoseTurn = rival.getId();
            gameCoins += Constants.getInstance().getBoardMoveCoins();
            movesCount++;
        }

        // If Rival Player was last, set the next turn to My Player, increase the moves count.
        else if (whoseTurn == rival.getId()) {
            whoseTurn = my.getId();
            movesCount++;
        }

        return whoseTurn;
    }

    /**
     * Check if there is a next move or if the board is already filled.
     *
     * @return false if there is not next move
     */
    public boolean hasNextTurn() {
        if (movesCount > board.getMaxMoves())
            return false;
        return true;
    }

    //-------------------------------- Getters & Setters -------------------------------------


    /**
     * Update "My Player" parameter of the game according to the user choice.
     *
     * @param myId Receive "My Player" ID according to the user choice.
     */
    public void setMy(int myId) {
        // Get the Player Object from the DB
        this.my = Constants.getInstance().getPlayer(myId);
    }

    /**
     * Update "Rival Player" parameter of the game according to the user choice.
     *
     * @param rivalId Receive "Rival Player" ID according to the user choice.
     */
    public void setRival(int rivalId) {
        // Get the Player Object from the DB
        this.rival = Constants.getInstance().getPlayer(rivalId);
    }

    /**
     * Update "Board Type" parameter of the game according to the user choice.
     *
     * @param boardType Receive "Board Type" ID according to the user choice.
     */
    public void setBoard(int boardType) {
        this.board = Constants.getInstance().getBoard(boardType);
    }

    public void setGameCoins(int gameCoins) {
        this.gameCoins = gameCoins;
    }

    /**
     * Set a new game according to the user chosen players & board
     */
    public void setGame() {

        // Init game coins to 0.
        setGameCoins(0);

        // Init new Game with the user chosen players & board
        this.game = new Game(my, rival, board, gameCoins);

        // Init new matrix board
        boardMatrix = new ArrayList<ArrayList<Integer>>(board.getSize());

        // Init the matrix board with -1
        for (int i = 0; i < board.getSize(); i++) {
            boardMatrix.add(new ArrayList<Integer>(board.getSize()));

            for (int j = 0; j < board.getSize(); j++) {
                boardMatrix.get(i).add(j, -1);
            }
        }
    }


    public Game getGame() {
        return game;
    }


}
