package com.example.spacetrader.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Encounter;
import com.example.spacetrader.entity.Game;
import com.example.spacetrader.entity.PirateEncounter;
import com.example.spacetrader.entity.PoliceEncounter;
import com.example.spacetrader.entity.TraderEncounter;

import java.util.List;

/**
 * Activity for an encounter
 */
public class EncounterActivity extends AppCompatActivity {

    private List<Encounter> encounters;
    private Encounter encounter;
    private Game game;

    private Button option0Button;
    private Button option1Button;
    private Button option2Button;
    private Button continueButton;

    private TextView messageText;
    private TextView shipHealth;
    private TextView opponentShipHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);
        game = Game.getInstance();
        encounters = game.getEncounters();

        option0Button = findViewById(R.id.attack_button);
        option1Button = findViewById(R.id.flee_button);
        option2Button = findViewById(R.id.surrender_button);
        continueButton = findViewById(R.id.continue_button);
        messageText = findViewById(R.id.encounter_text);
        shipHealth = findViewById(R.id.ship_health_text);
        opponentShipHealth = findViewById(R.id.opponent_ship_health_text);

        encounter = encounters.remove(0);
        setEnabledButtons(false);
        updateText();

    }

    private void setEnabledButtons(boolean encounterFinished) {
        option0Button.setEnabled(!encounterFinished);
        option1Button.setEnabled(!encounterFinished);
        option2Button.setEnabled(!encounterFinished);
        continueButton.setEnabled(encounterFinished);
    }

    private void doOption(int option) {
        encounter.doOption(option);
        messageText.setText(encounter.getMessage());
        setEnabledButtons(encounter.finished());
        if (encounter.finished()) {
            encounter = null;
        } else {
            updateText();
        }
    }

    private void updateText() {
        if (encounter != null) {
            messageText.setText(encounter.getMessage());
            option0Button.setText(encounter.getThreeOptions()[0]);
            option1Button.setText(encounter.getThreeOptions()[1]);
            option2Button.setText(encounter.getThreeOptions()[2]);
            if (encounter instanceof PirateEncounter || encounter instanceof TraderEncounter) {
                opponentShipHealth.setText("Opponent Ship Health: " + encounter.getOpponentShipHealth()
                        + "/100");
            } else {
                opponentShipHealth.setText("");
            }
        } else {
            opponentShipHealth.setText("");
        }
        shipHealth.setText("Ship Health " + game.getShipHealth() + "/100");
    }

    /**
     * Option 1 clicked
     * @param v View
     */
    public void onClickOption1(View v) {
        doOption(0);
    }

    /**
     * Option 2 clicked
     * @param v View
     */
    public void onClickOption2(View v) {
        doOption(1);
    }

    /**
     * Option 3 clicked
     * @param v View
     */
    public void onClickOption3(View v) {
        doOption(2);
    }

    /**
     * Continue clicked
     * @param v View
     */
    public void onClickContinue(View v) {
        if (encounters.isEmpty()) {
            finish();
            return;
        } else {
            encounter = encounters.remove(0);
        }
        setEnabledButtons(false);
        updateText();
    }
}
