package com.shirzabolotnyklein.tropix.gui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.shirzabolotnyklein.tropix.R;
import com.shirzabolotnyklein.tropix.utils.Constants;
import com.shirzabolotnyklein.tropix.utils.GameLogic;
import com.shirzabolotnyklein.tropix.utils.Settings;

import java.util.Locale;

import static com.shirzabolotnyklein.tropix.gui.ApplicationContextProvider.getContext;

public class MainActivity extends AppCompatActivity {

    private Button btn_chooseBoard;
    private Button btn_store;

    Configuration configuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();

        setContentView(R.layout.lay_main);

        Constants instance = Constants.getInstance();

        instance.getAllPlayers();
        instance.getAllBoards();

        btn_chooseBoard = (Button) findViewById(R.id.btn_startGame);
        btn_store = (Button) findViewById(R.id.btn_popup_store);

        btn_chooseBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ChooseBoard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

                ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);
            }
        });

        btn_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Store.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

                ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20);
            }
        });

    }

    /**
     * Reset game on resume main activity
     */
    @Override
    public void onResume() {
        super.onResume();
        GameLogic.getGameLogic().resetGame();
    }

    /**
     * Show change language dialog.
     * English - Update to English
     * Hebrew - Update to Hebrew.
     */
    private void showChangeLanguageDialog() {
        // Array of language to display in alert dialog
        final String[] listItems = {"English", "עברית"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        //builder.setTitle("Choose Language...");
        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    // English
                    setLocale("en");
                    recreate();
                } else if (i == 1) {
                    // Hebrew
                    setLocale("he");
                    recreate();
                }

                // Dismiss alert dialog when language stored
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();

        // Show alert dialog
        alertDialog.show();
    }


    /**
     * Set language in the app
     * @param language Language to update
     */
    private void setLocale(String language) {

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        configuration = getContext().getResources().getConfiguration();

        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        Settings.getSettings().writeToFileLanguage(language);
    }

    public void loadLocale() {
        String language = Settings.getSettings().readFromFileLanguage();
        setLocale(language);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        //Inflate the menu - Add items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }


    @Override
    /**
     * Switch case to actions in menu
     */
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_language:
                showChangeLanguageDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
