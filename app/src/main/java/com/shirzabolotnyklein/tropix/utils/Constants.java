package com.shirzabolotnyklein.tropix.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.gui.ApplicationContextProvider;
import com.shirzabolotnyklein.tropix.model.Board;
import com.shirzabolotnyklein.tropix.model.Lock;
import com.shirzabolotnyklein.tropix.model.LockStatus;
import com.shirzabolotnyklein.tropix.model.Player;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Constants implements Serializable {

    //------------------------------------- Singleton ------------------------------------------
    private static Constants instance = null;

    private Constants() {
        this.allPlayers = new ArrayList<>();
        this.allBoards = new HashMap<Integer, Board>();
        this.allWinCoins = new HashMap<Integer, Integer>();
        this.context = ApplicationContextProvider.getContext();
    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    //------------------------------------- Other ------------------------------------------

    private Context context;

    //=============================================================================================//
    //=========================================== Game  ===========================================//
    //=============================================================================================//

    //------------------------------------- Game Parameters ----------------------------------------

    private int totalCoins = 0;

    private final int boardMoveCoins = 1;

    private final int boardWinCoins3 = 25;
    private final int boardWinCoins4 = 50;
    private final int boardWinCoins5 = 75;
    private final int boardWinCoins6 = 100;

    HashMap<Integer, Integer> allWinCoins = new HashMap<Integer, Integer>();

    private HashMap<Integer, Integer> allWinCoins() {
        if (allWinCoins.isEmpty()) {
            allWinCoins.put(3, boardWinCoins3);
            allWinCoins.put(4, boardWinCoins4);
            allWinCoins.put(5, boardWinCoins5);
            allWinCoins.put(6, boardWinCoins6);
        }
        return allWinCoins;
    }

    public int getBoardVicCoins(int size){
        allWinCoins();
        int vicCoins = allWinCoins.get(size);
        return vicCoins;
    }

    //--------------------------------------- Game Getters ------------------------------------------
    public int getTotalCoins() {
        readFromFileTotalCoins();
        return totalCoins;
    }

    public void setTotalCoins(int totalCoins) {
        this.totalCoins = totalCoins;
        writeToFileTotalCoins();
    }

    public int getBoardMoveCoins() {
        return boardMoveCoins;
    }

    //=============================================================================================//
    //=========================================== Board  ===========================================//
    //=============================================================================================//

    private final Board board3x3 = new Board(3, 9, boardWinCoins3);
    private final Board board4x4 = new Board(4, 16, boardWinCoins4);
    private final Board board5x5 = new Board(5, 25, boardWinCoins5);
    private final Board board6x6 = new Board(6, 36, boardWinCoins6);

    HashMap<Integer, Board> allBoards;

    public HashMap<Integer, Board> getAllBoards() {
        if (allBoards.isEmpty()) {
            allBoards.put(3, board3x3);
            allBoards.put(4, board4x4);
            allBoards.put(5, board5x5);
            allBoards.put(6, board6x6);
        }
        return allBoards;
    }


    /**
     *
     * @param size Receive Board ID
     * @return Board according to the key in the HashMap allBoards
     */
    public Board getBoard(int size) {
        Board board = allBoards.get(size);
        return board;
    }

    //=============================================================================================//
    //=========================================== Lock  ===========================================//
    //=============================================================================================//

    //------------------------------------- Lock Parameters ----------------------------------------
    private final Lock open = new Lock(LockStatus.OPEN, R.drawable.t000_open);
    private final Lock close = new Lock(LockStatus.CLOSE, R.drawable.t000_close);

    //------------------------------------- Lock Getters ------------------------------------------
    public Lock getOpen() {
        return open;
    }

    public Lock getClose() {
        return close;
    }

    //=============================================================================================//
    //======================================== Players  ===========================================//
    //=============================================================================================//

    //----------------------------------- Player Parameters ----------------------------------------
    private List<Player> allPlayers;

    private final Player player1 = new Player(1, "palm", 100, R.drawable.t001_palm, open);
    private final Player player2 = new Player(2, "iced_tea", 120, R.drawable.t002_iced_tea, open);
    private final Player player3 = new Player(3, "sunglasses", 150, R.drawable.t003_sunglasses, open);
    private final Player player4 = new Player(4, "starfish", 160, R.drawable.t004_starfish, open);
    private final Player player5 = new Player(5, "banana", 160, R.drawable.t005_banana, open);
    private final Player player6 = new Player(6, "beach_ball", 200, R.drawable.t006_beach_ball, close);
    private final Player player7 = new Player(7, "ice_cream", 240, R.drawable.t007_ice_cream, close);
    private final Player player8 = new Player(8, "tiki", 250, R.drawable.t008_tiki, close);
    private final Player player9 = new Player(9, "dolphin", 260, R.drawable.t009_dolphin, close);
    private final Player player10 = new Player(10, "lemon", 280, R.drawable.t010_lemon, close);
    private final Player player11 = new Player(11, "flamingo", 320, R.drawable.t011_flamingo, close);
    private final Player player12 = new Player(12, "shack", 350, R.drawable.t012_shack, close);
    private final Player player13 = new Player(13, "sun_cream", 390, R.drawable.t013_sun_cream, close);
    private final Player player14 = new Player(14, "flower", 400, R.drawable.t014_flower, close);
    private final Player player15 = new Player(15, "cactus", 420, R.drawable.t015_cactus, close);
    private final Player player16 = new Player(16, "volcano", 470, R.drawable.t016_volcano, close);
    private final Player player17 = new Player(17, "bucket", 480, R.drawable.t017_bucket, close);
    private final Player player18 = new Player(18, "beach", 480, R.drawable.t018_beach, close);
    private final Player player19 = new Player(19, "cherries", 490, R.drawable.t019_cherries, close);
    private final Player player20 = new Player(20, "sunset", 500, R.drawable.t020_sunset, close);
    private final Player player21 = new Player(21, "yatch", 510, R.drawable.t021_yatch, close);
    private final Player player22 = new Player(22, "pamela", 520, R.drawable.t022_pamela, close);
    private final Player player23 = new Player(23, "flower", 520, R.drawable.t023_flower, close);
    private final Player player24 = new Player(24, "hammock", 560, R.drawable.t024_hammock, close);
    private final Player player25 = new Player(25, "slippers", 560, R.drawable.t025_slippers, close);
    private final Player player26 = new Player(26, "palm_tree", 570, R.drawable.t026_palm_tree, close);
    private final Player player27 = new Player(27, "coconut", 580, R.drawable.t027_coconut, close);
    private final Player player28 = new Player(28, "sun", 580, R.drawable.t028_sun, close);
    private final Player player29 = new Player(29, "macaw", 600, R.drawable.t029_macaw, close);
    private final Player player30 = new Player(30, "necklace", 640, R.drawable.t030_necklace, close);
    private final Player player31 = new Player(31, "pineapple", 660, R.drawable.t031_pineapple, close);
    private final Player player32 = new Player(32, "shell", 680, R.drawable.t032_shell, close);
    private final Player player33 = new Player(33, "watermelon", 710, R.drawable.t033_watermelon, close);
    private final Player player34 = new Player(34, "ice_cream", 720, R.drawable.t034_ice_cream, close);
    private final Player player35 = new Player(35, "leaf", 720, R.drawable.t035_leaf, close);
    private final Player player36 = new Player(36, "toucan", 750, R.drawable.t036_toucan, close);
    private final Player player37 = new Player(37, "flower", 790, R.drawable.t037_flower, close);
    private final Player player38 = new Player(38, "popsicle", 820, R.drawable.t038_popsicle, close);
    private final Player player39 = new Player(39, "flower", 880, R.drawable.t039_flower, close);
    private final Player player40 = new Player(40, "mango", 900, R.drawable.t040_mango, close);
    private final Player player41 = new Player(41, "cocktail", 920, R.drawable.t041_cocktail, close);
    private final Player player42 = new Player(42, "surfboard", 960, R.drawable.t042_surfboard, close);
    private final Player player43 = new Player(43, "shell", 970, R.drawable.t043_shell, close);
    private final Player player44 = new Player(44, "jellyfish", 980, R.drawable.t044_jellyfish, close);
    private final Player player45 = new Player(45, "wave", 990, R.drawable.t045_wave, close);
    private final Player player46 = new Player(46, "crab", 1000, R.drawable.t046_crab, close);
    private final Player player47 = new Player(47, "clown_fish", 1010, R.drawable.t047_clown_fish, close);
    private final Player player48 = new Player(48, "lifesaver", 1250, R.drawable.t048_lifesaver, close);
    private final Player player49 = new Player(49, "shirt", 1440, R.drawable.t049_shirt, close);
    private final Player player50 = new Player(50, "compass", 1500, R.drawable.t050_compass, close);

    //------------------------------------ All Players Getter --------------------------------------

    public List<Player> getAllPlayers() {

            if(allPlayers.isEmpty()) {
                allPlayers.add(player1);
                allPlayers.add(player2);
                allPlayers.add(player3);
                allPlayers.add(player4);
                allPlayers.add(player5);
                allPlayers.add(player6);
                allPlayers.add(player7);
                allPlayers.add(player8);
                allPlayers.add(player9);
                allPlayers.add(player10);
                allPlayers.add(player11);
                allPlayers.add(player12);
                allPlayers.add(player13);
                allPlayers.add(player14);
                allPlayers.add(player15);
                allPlayers.add(player16);
                allPlayers.add(player17);
                allPlayers.add(player18);
                allPlayers.add(player19);
                allPlayers.add(player20);
                allPlayers.add(player21);
                allPlayers.add(player22);
                allPlayers.add(player23);
                allPlayers.add(player24);
                allPlayers.add(player25);
                allPlayers.add(player26);
                allPlayers.add(player27);
                allPlayers.add(player28);
                allPlayers.add(player29);
                allPlayers.add(player30);
                allPlayers.add(player31);
                allPlayers.add(player32);
                allPlayers.add(player33);
                allPlayers.add(player34);
                allPlayers.add(player35);
                allPlayers.add(player36);
                allPlayers.add(player37);
                allPlayers.add(player38);
                allPlayers.add(player39);
                allPlayers.add(player40);
                allPlayers.add(player41);
                allPlayers.add(player42);
                allPlayers.add(player43);
                allPlayers.add(player44);
                allPlayers.add(player45);
                allPlayers.add(player46);
                allPlayers.add(player47);
                allPlayers.add(player48);
                allPlayers.add(player49);
                allPlayers.add(player50);
                if (!preferenceFileExist("allPlayersStatus")) {
                    writeToFilePlayerStatus();
                }
            }
            else {readFromFilePlayerStatus();}

        return allPlayers;
    }


    /**
     * @param id Receive Player ID
     * @return Player according to the index in the ArrayList
     */
    public Player getPlayer(int id) {
        Player player = allPlayers.get(id - 1);
        return player;
    }


    //=============================================================================================//
    //========================== Shared Preferences - All Players Status ==========================//
    //=============================================================================================//

    //--------------------------------- Write, Read & Edit file ------------------------------------

    /**
     * Write from SharedPreferences "allPlayersStatus" file.
     * Get allPlayers status date from allPlayers ArrayList, update the data in the file.
     *
     */
    private void writeToFilePlayerStatus() {

        // Get the file named "allPlayersStatus", private
        SharedPreferences allPlayersStatus = context.getSharedPreferences("allPlayersStatus", Context.MODE_PRIVATE);

        // Get the editor to edit the file
        SharedPreferences.Editor editor = allPlayersStatus.edit();

        // Put key+value to the file
        String currentPlayerId = "";
        String currentPlayerStatus = "";

        for (Player player : allPlayers) {
            currentPlayerId = Integer.toString(player.getId());
            currentPlayerStatus = player.getIsLocked().getStatus().toString();
            editor.putString(currentPlayerId, currentPlayerStatus);

            // Save the changes
            editor.apply();
        }
    }

    /**
     * Read from SharedPreferences "allPlayersStatus" file.
     * Get allPlayers status date, update the data in allPlayers ArrayList
     */
    private void readFromFilePlayerStatus() {

        // Get the file named "allPlayersStatus", private
        SharedPreferences allPlayersStatus = context.getSharedPreferences("allPlayersStatus", Context.MODE_PRIVATE);

        Lock playerLock;

        //Init the status of allPlayers ArrayList, by iterating through the file, and reading the status for each player
        for (Player p : allPlayers) {
            String keyPlayerId = String.valueOf(p.getId());

            String statusFromFile = allPlayersStatus.getString(keyPlayerId, "CLOSED");

            if (statusFromFile.equals("OPEN")) {
                playerLock = open;
            } else {
                playerLock = close;
            }

            // Set the status
            allPlayers.get(p.getId() - 1).setIslocked(playerLock);
        }
    }

    /**
     * Edit SharedPreferences "allPlayersStatus" file, update player status.
     *
     * @param playerId the player to update
     */
    protected void editFilePlayerStatus(int playerId) {

        // Get the file named "allPlayersStatus", private
        SharedPreferences allPlayersStatus = context.getSharedPreferences("allPlayersStatus", Context.MODE_PRIVATE);

        // Get the editor to edit the file
        SharedPreferences.Editor editor = allPlayersStatus.edit();

        // Put key+value to the file
        String currentPlayerId = String.valueOf(playerId);
        String currentPlayerStatus = getPlayer(playerId).getIsLocked().getStatus().toString();

        editor.putString(currentPlayerId, currentPlayerStatus);

        // Save the changes
        editor.apply();
    }


    public boolean preferenceFileExist(String fileName) {
        File f = new File(context.getApplicationInfo().dataDir + "/shared_prefs/"
                + fileName + ".xml");
        return f.exists();
    }

    //=============================================================================================//
    //============================= Shared Preferences - User Coins  ==============================//
    //=============================================================================================//

    //--------------------------------- Write, Read & Edit file ------------------------------------

    /**
     * Write to SharedPreferences file all the Players status.
     * Get allPlayers status date from allPlayers ArrayList, update the data in the file.
     *
     */
    private void writeToFileTotalCoins() {

        String keyTotalCoins = "userTotalCoins";

        // Get the file named "userTotalCoins", private
        SharedPreferences userTotalCoins = context.getSharedPreferences(keyTotalCoins, Context.MODE_PRIVATE);

        // Get the editor to edit the file
        SharedPreferences.Editor editor = userTotalCoins.edit();

        editor.putInt(keyTotalCoins,totalCoins);

        // Save the changes
        editor.apply();
    }

    /**
     * Read from SharedPreferences "userTotalCoins" file.
     * Get allPlayers status date, update the data in allPlayers ArrayList
     */
    private void readFromFileTotalCoins() {

        String keyTotalCoins = "userTotalCoins";

        // Get the file named "userTotalCoins", private
        SharedPreferences userTotalCoins = context.getSharedPreferences(keyTotalCoins, Context.MODE_PRIVATE);

        int totalCoinsFromFile = userTotalCoins.getInt(keyTotalCoins, -1);

        if (totalCoinsFromFile == -1){
            writeToFileTotalCoins();
        }

        else {
            totalCoins = totalCoinsFromFile;
        }

    }
}
