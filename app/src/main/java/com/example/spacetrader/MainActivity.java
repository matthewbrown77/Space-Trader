package com.example.spacetrader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    private Player player;

    private Button okButton;
    private Button exitButton;
    private Button pilotPlusButton;
    private Button pilotMinusButton;
    private Button fighterPlusButton;
    private Button fighterMinusButton;
    private Button traderPlusButton;
    private Button traderMinusButton;
    private Button engineerPlusButton;
    private Button engineerMinusButton;

    private Spinner spinner;

    private TextView counterTextView;
    private TextView pilotCounterTextView;
    private TextView pilotFighterTextView;
    private TextView pilotTraderTextView;
    private TextView pilotEngineerTextView;
    private TextView toastTextView;

    private EditText myName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        player = new Player();

        counterTextView = findViewById(R.id.total_counter);
        counterTextView.setText("" + player.getRemainingCount());

        pilotCounterTextView = findViewById(R.id.pilot_counter);
        pilotFighterTextView = findViewById(R.id.fighter_counter);
        pilotTraderTextView = findViewById(R.id.trader_counter);
        pilotEngineerTextView = findViewById(R.id.engineer_counter);
        toastTextView = findViewById(R.id.toast);

        myName = findViewById(R.id.editText);

        //populate spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> dataAdapter =
				ArrayAdapter.createFromResource(this, R.array.difficulty, R.layout.my_spinner);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(dataAdapter);

        ///////////////////////////////////////////////////////////////////////////////////////
        //Pilot
        ///////////////////////////////////////////////////////////////////////////////////////

        pilotPlusButton = findViewById(R.id.pilot_plus);
        pilotPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.incrementPilotSkillPoints();
                pilotCounterTextView.setText("" + player.getPilotSkillPoints());
                counterTextView.setText("" + player.getRemainingCount());
            }
        });

        pilotMinusButton = findViewById(R.id.pilot_minus);
        pilotMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.decrementPilotSkillPoints();
                pilotCounterTextView.setText("" + player.getPilotSkillPoints());
                counterTextView.setText("" + player.getRemainingCount());
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
        //Fighter
        ///////////////////////////////////////////////////////////////////////////////////////

        fighterPlusButton = findViewById(R.id.fighter_plus);
        fighterPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.incrementFighterSkillPoints();
                pilotFighterTextView.setText("" + player.getFighterSkillPoints());
                counterTextView.setText("" + player.getRemainingCount());
            }
        });

        fighterMinusButton = findViewById(R.id.fighter_minus);
        fighterMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.decrementFighterSkillPoints();
                pilotFighterTextView.setText("" + player.getFighterSkillPoints());
                counterTextView.setText("" + player.getRemainingCount());
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
        //Trader
        ///////////////////////////////////////////////////////////////////////////////////////

        traderPlusButton = findViewById(R.id.trader_plus);
        traderPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.incrementTraderSkillPoints();
                pilotTraderTextView.setText("" + player.getTraderSkillPoints());
                counterTextView.setText("" + player.getRemainingCount());
            }
        });

        traderMinusButton = findViewById(R.id.trader_minus);
        traderMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.decrementTraderSkillPoints();
                pilotTraderTextView.setText("" + player.getTraderSkillPoints());
                counterTextView.setText("" + player.getRemainingCount());
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
        //Engineer
        ///////////////////////////////////////////////////////////////////////////////////////

        engineerPlusButton = findViewById(R.id.engineer_plus);
        engineerPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.incrementEngineerSkillPoints();
                pilotEngineerTextView.setText("" + player.getEngineerSkillPoints());
                counterTextView.setText("" + player.getRemainingCount());
            }
        });

        engineerMinusButton = findViewById(R.id.engineer_minus);
        engineerMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.decrementEngineerSkillPoints();
                pilotEngineerTextView.setText("" + player.getEngineerSkillPoints());
                counterTextView.setText("" + player.getRemainingCount());
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
        //Ok
        ///////////////////////////////////////////////////////////////////////////////////////

        okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myName.getText().length() == 0) {
                    toastTextView.setText("PLEASE ENTER NAME");
                } else if (player.getRemainingCount() != 0) {
                    toastTextView.setText("PLEASE ALLOCATE ALL SKILL POINTS");
                } else {
                    player.setName(myName.getText().toString());
                    toastTextView.setText("PLAYER SUCCESSFULLY CREATED");
                    Log.e("main", player.toString());
                }
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
        //Exit
        ///////////////////////////////////////////////////////////////////////////////////////

        exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("main", "Will Exit Game");
                finish();
                System.exit(0);
            }
        });



	}

}
