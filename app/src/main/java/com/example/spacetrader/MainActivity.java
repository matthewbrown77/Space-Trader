package com.example.spacetrader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

	private TextView mTextMessage;
	private TextView addButton;
	private Player player;

    Button okButton;
    Button pilotPlusButton;
    Button pilotMinusButton;

    Button fighterPlusButton;
    Button fighterMinusButton;

    Button traderPlusButton;
    Button traderMinusButton;

    Button engineerPlusButton;
    Button engineerMinusButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		player = new Player();
		setContentView(R.layout.activity_main);

        ///////////////////////////////////////////////////////////////////////////////////////
        //Pilot
        ///////////////////////////////////////////////////////////////////////////////////////

        pilotPlusButton = findViewById(R.id.pilot_plus);
        pilotPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.pilot_counter);
                player.incrementPilotSkillPoints();
                mTextView.setText("" + player.getPilotSkillPoints());
            }
        });

        pilotMinusButton = findViewById(R.id.pilot_minus);
        pilotMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.pilot_counter);
                player.decrementPilotSkillPoints();
                mTextView.setText("" + player.getPilotSkillPoints());
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
        //Fighter
        ///////////////////////////////////////////////////////////////////////////////////////

        fighterPlusButton = findViewById(R.id.fighter_plus);
        fighterPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.fighter_counter);
                player.incrementFighterSkillPoints();
                mTextView.setText("" + player.getFighterSkillPoints());
            }
        });

        fighterMinusButton = findViewById(R.id.fighter_minus);
        fighterMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.fighter_counter);
                player.decrementFighterSkillPoints();
                mTextView.setText("" + player.getFighterSkillPoints());
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
        //Trader
        ///////////////////////////////////////////////////////////////////////////////////////

        traderPlusButton = findViewById(R.id.trader_plus);
        traderPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.trader_counter);
                player.incrementTraderSkillPoints();
                mTextView.setText("" + player.getTraderSkillPoints());
            }
        });

        traderMinusButton = findViewById(R.id.trader_minus);
        traderMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.trader_counter);
                player.decrementTraderSkillPoints();
                mTextView.setText("" + player.getTraderSkillPoints());
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
        //Engineer
        ///////////////////////////////////////////////////////////////////////////////////////

        engineerPlusButton = findViewById(R.id.engineer_plus);
        engineerPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.engineer_counter);
                player.incrementEngineerSkillPoints();
                mTextView.setText("" + player.getEngineerSkillPoints());
            }
        });

        engineerMinusButton = findViewById(R.id.engineer_minus);
        engineerMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.engineer_counter);
                player.decrementEngineerSkillPoints();
                mTextView.setText("" + player.getEngineerSkillPoints());
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
        //Ok
        ///////////////////////////////////////////////////////////////////////////////////////

        mTextMessage = (TextView) findViewById(R.id.message);
        okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("main", "Ok was clicked");
            }
        });



	}

}
