package com.shirzabolotnyklein.tropix.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.model.Lock;
import com.shirzabolotnyklein.tropix.model.LockStatus;
import com.shirzabolotnyklein.tropix.model.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Constants implements Serializable {
    private static Constants instance = null;
    private List<Player> allPlayers;
    private Context context;

    private Constants() {
        allPlayers = new ArrayList<>();
    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    //------------------------------------- Parameters -------------------------------------

    private final int boardMoveCoins = 5;

    private final int boardVicCoins3 = 300;
    private final int boardVicCoins4 = 400;
    private final int boardVicCoins5 = 500;
    private final int boardVicCoins6 = 600;

    //------------------------------------- Locks -------------------------------------

    private final Lock open = new Lock(LockStatus.OPEN, R.drawable.t000_open);
    private final Lock close = new Lock(LockStatus.CLOSE, R.drawable.t000_close);

    //------------------------------------- Players -------------------------------------

    private final Player player1 = new Player(1, "palm", 100, R.drawable.t001_palm, open);
    private final Player player2 = new Player(2, "iced_tea", 100, R.drawable.t002_iced_tea, open);
    private final Player player3 = new Player(3, "sunglasses", 100, R.drawable.t003_sunglasses, open);
    private final Player player4 = new Player(4, "starfish", 100, R.drawable.t004_starfish, open);
    private final Player player5 = new Player(5, "banana", 100, R.drawable.t005_banana, open);
    private final Player player6 = new Player(6, "beach_ball", 200, R.drawable.t006_beach_ball, close);
    private final Player player7 = new Player(7, "ice_cream", 200, R.drawable.t007_ice_cream, close);
    private final Player player8 = new Player(8, "tiki", 200, R.drawable.t008_tiki, close);
    private final Player player9 = new Player(9, "dolphin", 200, R.drawable.t009_dolphin, close);
    private final Player player10 = new Player(10, "lemon", 200, R.drawable.t010_lemon, close);
    private final Player player11 = new Player(11, "flamingo", 300, R.drawable.t011_flamingo, close);
    private final Player player12 = new Player(12, "shack", 300, R.drawable.t012_shack, close);
    private final Player player13 = new Player(13, "sun_cream", 300, R.drawable.t013_sun_cream, close);
    private final Player player14 = new Player(14, "flower", 400, R.drawable.t014_flower, close);
    private final Player player15 = new Player(15, "cactus", 400, R.drawable.t015_cactus, close);
    private final Player player16 = new Player(16, "volcano", 400, R.drawable.t016_volcano, close);
    private final Player player17 = new Player(17, "bucket", 400, R.drawable.t017_bucket, close);
    private final Player player18 = new Player(18, "beach", 400, R.drawable.t018_beach, close);
    private final Player player19 = new Player(19, "cherries", 400, R.drawable.t019_cherries, close);
    private final Player player20 = new Player(20, "sunset", 500, R.drawable.t020_sunset, close);
    private final Player player21 = new Player(21, "yatch", 500, R.drawable.t021_yatch, close);
    private final Player player22 = new Player(22, "pamela", 500, R.drawable.t022_pamela, close);
    private final Player player23 = new Player(23, "flower", 500, R.drawable.t023_flower, close);
    private final Player player24 = new Player(24, "hammock", 500, R.drawable.t024_hammock, close);
    private final Player player25 = new Player(25, "slippers", 500, R.drawable.t025_slippers, close);
    private final Player player26 = new Player(26, "palm_tree", 500, R.drawable.t026_palm_tree, close);
    private final Player player27 = new Player(27, "coconut", 500, R.drawable.t027_coconut, close);
    private final Player player28 = new Player(28, "sun", 500, R.drawable.t028_sun, close);
    private final Player player29 = new Player(29, "macaw", 600, R.drawable.t029_macaw, close);
    private final Player player30 = new Player(30, "necklace", 600, R.drawable.t030_necklace, close);
    private final Player player31 = new Player(31, "pineapple", 600, R.drawable.t031_pineapple, close);
    private final Player player32 = new Player(32, "shell", 600, R.drawable.t032_shell, close);
    private final Player player33 = new Player(33, "watermelon", 700, R.drawable.t033_watermelon, close);
    private final Player player34 = new Player(34, "ice_cream", 700, R.drawable.t034_ice_cream, close);
    private final Player player35 = new Player(35, "leaf", 700, R.drawable.t035_leaf, close);
    private final Player player36 = new Player(36, "toucan", 700, R.drawable.t036_toucan, close);
    private final Player player37 = new Player(37, "flower", 700, R.drawable.t037_flower, close);
    private final Player player38 = new Player(38, "popsicle", 800, R.drawable.t038_popsicle, close);
    private final Player player39 = new Player(39, "flower", 800, R.drawable.t039_flower, close);
    private final Player player40 = new Player(40, "mango", 900, R.drawable.t040_mango, close);
    private final Player player41 = new Player(41, "cocktail", 900, R.drawable.t041_cocktail, close);
    private final Player player42 = new Player(42, "surfboard", 900, R.drawable.t042_surfboard, close);
    private final Player player43 = new Player(43, "shell", 900, R.drawable.t043_shell, close);
    private final Player player44 = new Player(44, "jellyfish", 900, R.drawable.t044_jellyfish, close);
    private final Player player45 = new Player(45, "wave", 900, R.drawable.t045_wave, close);
    private final Player player46 = new Player(46, "crab", 1000, R.drawable.t046_crab, close);
    private final Player player47 = new Player(47, "clown_fish", 1000, R.drawable.t047_clown_fish, close);
    private final Player player48 = new Player(48, "lifesaver", 1000, R.drawable.t048_lifesaver, close);
    private final Player player49 = new Player(49, "shirt", 1000, R.drawable.t049_shirt, close);
    private final Player player50 = new Player(50, "compass", 1000, R.drawable.t050_compass, close);


    public List<Player> initAllPlayersList() {

        if (allPlayers.isEmpty()) {
            Log.d("INSIDE if", "INSIDE if");

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

        }

        return allPlayers;

    }


    //------------------------------------- Shared Preferences Methods -------------------------------------

    /**
     * Write from SharedPreferences "allPlayersStatus" file.
     * Get allPlayers status date from allPlayers ArrayList, update the data in the file.
     *
     * @param view
     */
    private void writeToFile(View view) {

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
        }

        // Save the changes
        editor.apply();
    }

    /**
     * Read from SharedPreferences "allPlayersStatus" file.
     * Get allPlayers status date, update the data in allPlayers ArrayList
     */
    private void readFromFile() {

        // Get the file named "allPlayersStatus", private
        SharedPreferences allPlayersStatus = context.getSharedPreferences("allPlayersStatus", Context.MODE_PRIVATE);

        Map<String, ?> allPlayersMap = allPlayersStatus.getAll();

        int id;
        String status;
        Lock playerLock;

        for (Map.Entry<String, ?> entry : allPlayersMap.entrySet()) {
            id = Integer.parseInt(entry.getKey());
            status = entry.getValue().toString();

            if (status == "OPEN") {
                playerLock = open;
            } else {
                playerLock = close;
            }

            allPlayers.get(id - 1).setIslocked(playerLock);
        }
    }

    /**
     * Edit SharedPreferences "allPlayersStatus" file, update player status.
     *
     * @param view
     * @param player the player to update
     */
    private void editFile(View view, Player player) {

        // Get the file named "allPlayersStatus", private
        SharedPreferences allPlayersStatus = context.getSharedPreferences("allPlayersStatus", Context.MODE_PRIVATE);

        // Get the editor to edit the file
        SharedPreferences.Editor editor = allPlayersStatus.edit();

        // Put key+value to the file
        String currentPlayerId = "";
        String currentPlayerStatus = "";

        currentPlayerId = Integer.toString(player.getId());
        currentPlayerStatus = player.getIsLocked().getStatus().toString();

        editor.putString(currentPlayerId, currentPlayerStatus);

        // Save the changes
        editor.apply();
    }


    /*
     * create String for file with all the players details - player id & player status.
     * separate each player with the char "#", and each player detail with the char ","
     * As the following example: id1,status1#id2,status2#id3,status3#...
     *
     * @return the allPlayersString to write in the file.
     *
    private String createStringToFile() {

        String allPlayersStringToFile = "";
        String currentPlayerStringToFile = "";

        for (Player player : allPlayers) {

            currentPlayerStringToFile = Integer.toString(player.getId()) + "," +
                    player.getIsLocked().getStatus() + "#";

            allPlayersStringToFile = allPlayersStringToFile.concat(currentPlayerStringToFile);
        }
        return allPlayersStringToFile;
    }
    */

    /*
     * Read the text of all players status from the file and update them in the allPlayers array list.
     *
     * @param allPlayersString receive the string from file
     *
    private void SplitStringFromFile(String allPlayersString) {

        // Every player is separated with the char "#", slip all the players into allPlayersFromFile String array
        String allPlayersFromFile[] = allPlayersString.split("#");
        ;

        // Every player detail is separated with the char ",", slip all the player details into playerFromFile String array
        for (String p : allPlayersFromFile) {

            String playerFromFile[] = p.split(",");

            int id = Integer.parseInt(playerFromFile[0]); // Get the player id

            String status = playerFromFile[1]; // Get the player status
            Lock playerLock;

            if (status == "OPEN") {
                playerLock = open;
            } else {
                playerLock = close;
            }

            allPlayers.get(id - 1).setIslocked(playerLock);
        }

    }
    */


    /*
    private void writeAllPlayersListToFile(){
        FileOutputStream fileOutput = null;
        try {
            fileOutput = new FileOutputStream("allPlayers.tmp");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectOutputStream objectOutput = null;
        try {
            objectOutput = new ObjectOutputStream(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objectOutput.writeObject(allPlayers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objectOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAllPlayersListToFile() {
        Log.d("INSIDE READ", "INSIDE READ");
        try {
            FileInputStream fileInput = new FileInputStream("allPlayers.tmp");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            allPlayers = (List<Player>) objectInput.readObject();
            objectInput.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    */


}
