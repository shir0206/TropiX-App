package com.shirzabolotnyklein.tropix.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.Player;
import com.shirzabolotnyklein.tropix.utils.Constants;
import com.shirzabolotnyklein.tropix.utils.GameLogic;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_rival;
    private ImageView img_my;

    private TextView tv_gameCoinsSum;
    private TextView tv_totalCoinsSum;

    private Context context;

    private boolean againstComputer;
    private int size;

    protected static Activity gameActivity;

    private ArrayList<ArrayList<ImageButton>> boardImageButtons = new ArrayList<ArrayList<ImageButton>>();
    int nextPlayerTurnId;
    Player nextPlayerTurn;
    int my;
    int rival;
    int first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameActivity = this;
        context = ApplicationContextProvider.getContext();
        againstComputer = GameLogic.getGameLogic().isAgainstComputer();

        my = GameLogic.getGameLogic().getMy();
        rival = GameLogic.getGameLogic().getRival();
        first = GameLogic.getGameLogic().getFirst();

        Resources.Theme theme = super.getTheme();

        // Switch case which board layout should be opened
        switch (GameLogic.getGameLogic().getGame().getBoard().getSize()) {
            case 3:
                theme.applyStyle(R.style.YellowTheme, true);
                setContentView(R.layout.lay_game_3x3);
                break;
            case 4:
                theme.applyStyle(R.style.PinkTheme, true);
                setContentView(R.layout.lay_game_4x4);
                break;
            case 5:
                theme.applyStyle(R.style.RedTheme, true);
                setContentView(R.layout.lay_game_5x5);
                break;
            case 6:
                theme.applyStyle(R.style.GreenTheme, true);
                setContentView(R.layout.lay_game_6x6);
                break;

        }

        initPlayers();
        initBoard();
        initCoins();

        // Place Rival Player move
        if (againstComputer && first == rival) {

            // Get the rival move in the board
            Point cell = GameLogic.getGameLogic().rivalRandomMove();
            ImageButton cellBtn = createImageButtonOnBoard(cell);

            // Put the image of the Player in the clicked cell of the game board
            setPlayerInCellBtnVsRival(cellBtn);

        }
    }

    private void initCoins() {

        tv_gameCoinsSum = findViewById(R.id.tv_gameCoinsSum);
        tv_totalCoinsSum = findViewById(R.id.tv_totalCoinsNewSum);

        int gameCoins = Constants.getInstance().getBoardVicCoins(size);
        int totalCoins = Constants.getInstance().getTotalCoins();

        tv_gameCoinsSum.setText(Integer.toString(gameCoins));
        tv_totalCoinsSum.setText(Integer.toString(totalCoins));
    }

    /**
     * Init board of the game with Image Buttons
     */
    private void initBoard() {

        // Get board size
        size = GameLogic.getGameLogic().getGame().getBoard().getSize();

        for (int i = 0; i < size; i++) {
            boardImageButtons.add(new ArrayList<ImageButton>(size));

            for (int j = 0; j < size; j++) {

                // Create String for the ID of each Image Button in the board (For example: btn_board_3x3_cell_0x0)
                String cellStringID = "btn_board_" + size + "x" + size + "_cell_" + i + "x" + j;

                // Get the address of the ID of the Image Button
                int cellID = getResources().getIdentifier(cellStringID, "id", getPackageName());

                // Init new Image Button
                ImageButton cellImageButton = findViewById(cellID);

                // Add the Image Button to the board in the right position
                boardImageButtons.get(i).add(j, cellImageButton);

                // Turn on the Listener
                boardImageButtons.get(i).get(j).setOnClickListener(this);
            }
        }
    }

    /**
     * Update the User "My Player" & "Rival Player" on the screen
     */
    private void initPlayers() {

        img_rival = findViewById(R.id.img_rival);
        img_rival.setImageResource(GameLogic.getGameLogic().getGame().getRival().getPicture());

        img_my = findViewById(R.id.img_my);
        img_my.setImageResource(GameLogic.getGameLogic().getGame().getMy().getPicture());
    }

    /**
     * Click on an Image button set the next player image on that button and check if that move is a win
     */
    @Override
    public void onClick(View view) {

        ImageButton cellBtn = ((ImageButton) view);

        // Put the image of the Player in the clicked cell of the game board
        setPlayerInCellBtnVsRival(cellBtn);

        // Check which player is a winner
        boolean endGame = checkEndGame(cellBtn);

        if (againstComputer) {

            // If the game is not over yet, create AI move
            if (!endGame) {

                cellBtn = createRivalRandomMoveByMyPlayerMove(cellBtn);

                // Put the image of the Player in the clicked cell of the game board
                setPlayerInCellBtnVsRival(cellBtn);

                // Check which player is a winner
                checkEndGame(cellBtn);
            }
        }
    }

    public ImageButton createRivalRandomMoveByMyPlayerMove(ImageButton myCellBtn) {

        // Get the cell ID&name (For example "btn_board_3x3_cell_0x0")
        int cellId = myCellBtn.getId();
        String cellName = myCellBtn.getResources().getResourceName(cellId);

        // Get the position of the cell out of its name (For example "btn_board_3x3_cell_0{=i}x0{=j}")
        int myi = Character.getNumericValue(cellName.charAt(cellName.length() - 3));
        int myj = Character.getNumericValue(cellName.charAt(cellName.length() - 1));

        Point myPlayer = new Point(myi, myj);

        // Get the rival available cell in the board
        Point cell = GameLogic.getGameLogic().rivalMove(myPlayer);
        ImageButton cellBtn = createImageButtonOnBoard(cell);

        return cellBtn;
    }

    public ImageButton createImageButtonOnBoard(Point cell) {

        int i = cell.x;
        int j = cell.y;

        // Create String for the ID of each Image Button in the board (For example: btn_board_3x3_cell_0x0)
        String cellStringID = "btn_board_" + size + "x" + size + "_cell_" + i + "x" + j;

        // Get the address of the ID of the Image Button
        int cellID = getResources().getIdentifier(cellStringID, "id", getPackageName());

        // Init new Image Button
        ImageButton cellBtn = findViewById(cellID);

        return cellBtn;
    }

    /**
     * Check if there is a next turn, if not refresh game
     */
    private void checkHasNext() {
        boolean hasNext = GameLogic.getGameLogic().hasNextTurn();
        if (!hasNext) {
            String noNext = "נגמר המשחקיX";
            Toast.makeText(GameActivity.this, noNext, Toast.LENGTH_LONG).show();

            refreshGame();
        }
    }

    /**
     * Check which player is a winner
     *
     * @param cellBtn The clicked ImageButton cell in the board game
     */
    private boolean checkEndGame(ImageButton cellBtn) {

        // Check if there is a winner. If there is a winner return the winner ID, else return -1.
        int win = GameLogic.getGameLogic().checkWinner();

        // Check if there is a next turn. If there is next turn return true, else return false.
        boolean hasNext = GameLogic.getGameLogic().hasNextTurn();


        // If there is a winner, display a winner popup & refresh game
        if (win != -1) {
            endGamePopup(win);
            return true;

        }

        // Else check if there is a next turn
        else if (!hasNext) {
            endGamePopup(-1);
            return true;
        } else return false;
    }

    /**
     * Receive the winner Player ID and display winner message
     *
     * @param win
     */
    private void endGamePopup(int win) {

        if (win != -1) {
            GameLogic.getGameLogic().increaseTotalCoinsWin(win);
            String winMsg = win + " ניצח!!! :)";
            Toast.makeText(GameActivity.this, winMsg, Toast.LENGTH_LONG).show();

        } else {
            String drawMsg = "נגמר המשחק";
            Toast.makeText(GameActivity.this, drawMsg, Toast.LENGTH_LONG).show();
        }


        disableBoard();

        Intent intent = new Intent(context, GamePopUp.class);
        intent.putExtra("WINNER", win);
        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    /**
     * Disable all the buttons in the board
     */
    private void disableBoard() {

        // Get board size
        size = GameLogic.getGameLogic().getGame().getBoard().getSize();

        // Set the button disable
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardImageButtons.get(i).get(j).setEnabled(false);
            }
        }
    }

    /**
     * Update the Player ID position in the matrix of the game.
     *
     * @param cellBtn The clicked ImageButton cell in the board game
     */
    private void updateCellBtnPosition(ImageButton cellBtn) {

        // Get the cell ID&name (For example "btn_board_3x3_cell_0x0")
        int cellId = cellBtn.getId();
        String cellName = cellBtn.getResources().getResourceName(cellId);

        // Get the position of the cell out of its name (For example "btn_board_3x3_cell_0{=i}x0{=j}")
        int i = Character.getNumericValue(cellName.charAt(cellName.length() - 3));
        int j = Character.getNumericValue(cellName.charAt(cellName.length() - 1));

        // Update the Player ID position in the matrix of the game.
        GameLogic.getGameLogic().updateMoveInTheBoardMatrix(i, j, nextPlayerTurnId);
    }

    /**
     * Put the image of the Player in the clicked cell of the game board
     *
     * @param cellBtn The clicked ImageButton cell in the board game
     */
    private void setPlayerInCellBtnVsRival(ImageButton cellBtn) {

        // If cell in the board is clicked, get who is the next (my player/ rival player)
        nextPlayerTurnId = GameLogic.getGameLogic().getWhoseTurn();
        nextPlayerTurn = Constants.getInstance().getPlayer(nextPlayerTurnId);

        // Set the next player image in the cell
        cellBtn.setImageResource(nextPlayerTurn.getPicture());

        // Set enabled the button of the cell
        cellBtn.setClickable(false);

        // Update the Player ID position in the matrix of the game.
        updateCellBtnPosition(cellBtn);

        ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);
    }

    /**
     * Refresh game, reset game control class and return to MainActivity
     */
    private void refreshGame() {
        GameLogic.getGameLogic().resetGame();

        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

        GameActivity.this.finish();
    }

}