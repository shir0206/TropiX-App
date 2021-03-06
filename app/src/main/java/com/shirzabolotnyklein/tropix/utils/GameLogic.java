package com.shirzabolotnyklein.tropix.utils;

import android.graphics.Point;

import com.shirzabolotnyklein.tropix.model.Board;
import com.shirzabolotnyklein.tropix.model.Game;
import com.shirzabolotnyklein.tropix.model.Player;
import com.shirzabolotnyklein.tropix.model.Winner;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    //------------------------------------- Singleton ------------------------------------------
    private static GameLogic instance = null;

    private GameLogic() {
    }

    public static GameLogic getGameLogic() {
        if (instance == null) {
            instance = new GameLogic();
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
     * The max moves of the board of the game
     */
    private int maxMoves;

    /**
     * The user choice of first Player
     */
    private int first;

    /**
     * The matrix board represents in each cell:
     * -1               If the cell is empty
     * My Player ID     If the user put "My Player" in that cell
     * Rival Player ID  If the rival put "Rival Player" in that cell
     */
    private ArrayList<ArrayList<Integer>> boardMatrix;

    /**
     * Is the user plays against the computer
     */
    private boolean againstComputer;

    /**
     * The current available moves on the board
     */
    private ArrayList<Point> availableMoves = new ArrayList<Point>();

    //-------------------------------- Game Logic Methods -------------------------------------

    /**
     * The matrix board represents in each cell:
     * 0                If the cell is empty
     * My Player ID     If the user put "My Player" in that cell
     * Rival Player ID  If the rival put "Rival Player" in that cell
     * -
     * To check if there is a winner and who is the winner, for each player calculate the sum for win.
     * The sum for win would be the player ID from i = 0 to i = board size >> sumWin = ID * boardSize.
     * If the sum in a certain row / col / diagonal equals to the sum for win of a certain player,
     * there is a winner.
     *
     * @return if there is a winner, return the winner ID, else return -1.
     */
    public int checkWinner() {

        int size = board.getSize();

        int myId = my.getId();
        int rivalId = rival.getId();
        int currCell = 0;

        int sumMyPlayerWin = my.getId() * board.getSize();
        int sumRivalPlayerWin = rival.getId() * board.getSize();

        int sumMyRow = 0;
        int sumMyCol = 0;
        int sumMyDiagonalLeft = 0;
        int sumMyDiagonalRight = 0;

        int sumRivalRow = 0;
        int sumRivalCol = 0;
        int sumRivalDiagonalLeft = 0;
        int sumRivalDiagonalRight = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                // Calculate row sum
                currCell = boardMatrix.get(i).get(j);
                if (currCell == myId) {
                    sumMyRow += currCell;
                } else if (currCell == rivalId) {
                    sumRivalRow += currCell;
                }

                // Calculate col sum
                currCell = boardMatrix.get(j).get(i);
                if (currCell == myId) {
                    sumMyCol += currCell;
                } else if (currCell == rivalId) {
                    sumRivalCol += currCell;
                }
            }

            // Calculate diagonal-left sum (\)
            currCell = boardMatrix.get(i).get(i);
            if (currCell == myId) {
                sumMyDiagonalLeft += currCell;
            } else if (currCell == rivalId) {
                sumRivalDiagonalLeft += currCell;
            }

            // Calculate diagonal-right sum (/)
            currCell = boardMatrix.get(i).get(size - 1 - i);
            if (currCell == myId) {
                sumMyDiagonalRight += currCell;
            } else if (currCell == rivalId) {
                sumRivalDiagonalRight += currCell;
            }

            // At the end of each row / col, check row / col sum
            // If the sum of the row / col / diagonals equal to the sum of my player ID, return my player ID
            if ((sumMyRow == sumMyPlayerWin)
                    || (sumMyCol == sumMyPlayerWin)
                    || (sumMyDiagonalLeft == sumMyPlayerWin)
                    || (sumMyDiagonalRight == sumMyPlayerWin)) {
                game.setGameOver(true);
                game.setWinner(Winner.MY_PLAYER);
                return my.getId();
            }

            // If the sum of the row / col / diagonals equal to the sum of rival player ID, return rival player ID
            else if ((sumRivalRow == sumRivalPlayerWin)
                    || (sumRivalCol == sumRivalPlayerWin)
                    || (sumRivalDiagonalLeft == sumRivalPlayerWin)
                    || (sumRivalDiagonalRight == sumRivalPlayerWin)) {
                game.setGameOver(true);
                game.setWinner(Winner.RIVAL_PLAYER);
                return rival.getId();
            }

            // Init to check the next row/col
            sumMyRow = 0;
            sumMyCol = 0;
            sumRivalRow = 0;
            sumRivalCol = 0;
        }

        // If there is no winner, return -1
        game.setWinner(Winner.NONE_PLAYER);
        return -1;
    }

    /**
     * Check who played last and set whose turn now.
     * In every turn, increase with 1 the moves count (the sum of the cells that are already filled).
     *
     * @return whoseTurn
     */
    public int getWhoseTurn() {

        // If this is the first turn, start with My Player and Initiate the moves count to 0, increase board coins for My Player,.
        if (whoseTurn == -1) {
            whoseTurn = first;
            movesCount = 1;
            maxMoves = board.getMaxMoves();
        }

        // If My Player was last, set the next turn to Rival Player, increase the moves count.
        else if (whoseTurn == my.getId()) {
            whoseTurn = rival.getId();
            movesCount++;
        }

        // If Rival Player was last, set the next turn to My Player, increase board coins for My Player, increase the moves count.
        else if (whoseTurn == rival.getId()) {
            whoseTurn = my.getId();
            movesCount++;
        }

        return whoseTurn;
    }

    /**
     * Increase the total coins for the user, if he wins (using My Player)
     * @param winnerId
     */
    public void increaseTotalCoinsWin(int winnerId) {

        if (winnerId == my.getId()) {
            int size = board.getSize();

            int boardVicCoins = Constants.getInstance().getBoardVicCoins(size);

            int winTotalCoins = Constants.getInstance().getTotalCoins() + boardVicCoins;

            Constants.getInstance().setTotalCoins(winTotalCoins);
        }
    }

    /**
     * Check if there is a next move or if the board is already filled.
     *
     * @return false if there is not next move
     */
    public boolean hasNextTurn() {
        if (movesCount >= maxMoves) {
            if (game.getWinner() == Winner.NONE_PLAYER) {
                game.setWinner(Winner.DRAW);
            }
            return false;
        }
        return true;
    }

    /**
     * Generate rival move according to my rival move.
     * Place Rival Player besides My Player in order to attack and break his sequence.
     *
     * @param myPlayer My Player position on the board
     * @return Rival Player position on the board
     */
    public Point rivalMove(Point myPlayer) {

        int myRow = myPlayer.x;
        int myCol = myPlayer.y;

        ArrayList<Integer> rivalAttackRow = new ArrayList<Integer>();
        ArrayList<Integer> rivalAttackCol = new ArrayList<Integer>();

        // init possible attack row indexes

        // If My Player is placed in the top row, place Rival Player in the same row or below.
        if (myRow == 0) {
            rivalAttackRow.add(myRow + 1);
        }

        // If My Player is placed in the bottom row, place Rival Player in the same row or above.
        else if (myRow == board.getSize() - 1) {
            rivalAttackRow.add(myRow - 1);
        }

        // Place Rival Player in the same row / below / above My Player position.
        else {
            rivalAttackRow.add(myRow + 1);
            rivalAttackRow.add(myRow - 1);
        }
        rivalAttackRow.add(myRow);

        // init possible attack col indexes

        // If My Player is placed in the left col, place Rival Player in the same col or to the right.
        if (myCol == 0) {
            rivalAttackCol.add(myCol + 1);
        }

        // If My Player is placed in the right col, place Rival Player in the same col or to the left.
        else if (myCol == board.getSize() - 1) {
            rivalAttackCol.add(myCol - 1);
        }

        // Place Rival Player in the same col / to the left /  to the right of My Player position.
        else {
            rivalAttackCol.add(myCol + 1);
            rivalAttackCol.add(myCol - 1);
        }
        rivalAttackCol.add(myCol);

        // Create an array of all the possible attacks
        ArrayList<Point> rivalAttackPoints = new ArrayList<Point>();

        // Iterate thorough all the possible row/col moves and
        // create a combination of the possible attack points to place Rival Player in the board.
        for (int i = 0; i < rivalAttackRow.size(); i++) {
            for (int j = 0; j < rivalAttackCol.size(); j++) {

                int rivalRow = rivalAttackRow.get(i);
                int rivalCol = rivalAttackCol.get(j);

                // Don't add the point where My Player stands
                if (!(rivalRow == myRow && rivalCol == myCol)) {

                    // Check if the current point in the board is empty
                    if (boardMatrix.get(rivalRow).get(rivalCol) == 0) {

                        Point point = new Point(rivalRow, rivalCol);
                        rivalAttackPoints.add(point);
                    }
                }
            }
        }

        Random randomGenerator = new Random();
        int randomMove;
        Point randomPoint;

        // Place Rival Player besides My Player last move cell position
        if (!rivalAttackPoints.isEmpty()) {
            randomMove = randomGenerator.nextInt(rivalAttackPoints.size());
            randomPoint = rivalAttackPoints.get(randomMove);
        }

        // If there are no any closer available cells besides My Player last move cell position (rivalAttackPoints is empty)
        else {
            randomPoint = rivalRandomMove();
        }
        return randomPoint;
    }

    /**
     * Create a random move on the board out of all the available cells
     * @return  The point where the rial should move to
     */
    public Point rivalRandomMove() {

        Random randomGenerator = new Random();
        int randomMove;
        Point randomPoint;

        randomMove = randomGenerator.nextInt(availableMoves.size());
        randomPoint = availableMoves.get(randomMove);

        return randomPoint;

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

        // Init new Game with the user chosen players & board
        this.game = new Game(my, rival, board, false, gameCoins, againstComputer);

        // Init new matrix board
        boardMatrix = new ArrayList<ArrayList<Integer>>(board.getSize());

        // Init the matrix board with -1
        for (int i = 0; i < board.getSize(); i++) {
            boardMatrix.add(new ArrayList<Integer>(board.getSize()));

            for (int j = 0; j < board.getSize(); j++) {
                boardMatrix.get(i).add(j, 0);

                availableMoves.add(new Point(i, j));
            }
        }
    }

    /**
     * Set a new game according to the user chosen players & board
     */
    public void updateMoveInTheBoardMatrix(int i, int j, int playerId) {

        boardMatrix.get(i).set(j, playerId);
        availableMoves.remove(new Point(i, j));
    }

    public Game getGame() {
        return game;
    }


    public void resetGame() {
        instance = null;
    }


    public int getMy() {
        if (my == null)
            return 0;
        return my.getId();
    }

    public int getRival() {
        if (rival == null)
            return 0;
        return rival.getId();
    }

    public boolean isAgainstComputer() {
        return againstComputer;
    }

    public void setAgainstComputer(boolean againstComputer) {
        this.againstComputer = againstComputer;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

}
