package com.example.spacetrader;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.List;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

	private TextView mTextMessage;
	private TextView addButton;
	private Player player;

    private Button okButton;
    private Button pilotPlusButton;
    private Button pilotMinusButton;
    private Button fighterPlusButton;
    private Button fighterMinusButton;
    private Button traderPlusButton;
    private Button traderMinusButton;
    private Button engineerPlusButton;
    private Button engineerMinusButton;

    private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        player = new Player();

        final TextView mTextView2 = (TextView) findViewById(R.id.total_counter);
        mTextView2.setText("" + player.getRemainingCount());

        //populate spinner
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> dataAdapter =
				ArrayAdapter.createFromResource(this, R.array.difficulty, R.layout.my_spinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        ///////////////////////////////////////////////////////////////////////////////////////
        //Pilot
        ///////////////////////////////////////////////////////////////////////////////////////

        pilotPlusButton = findViewById(R.id.pilot_plus);
        pilotPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.pilot_counter);
                final TextView mTextView2 = (TextView) findViewById(R.id.total_counter);
                player.incrementPilotSkillPoints();
                mTextView.setText("" + player.getPilotSkillPoints());
                mTextView2.setText("" + player.getRemainingCount());
            }
        });

        pilotMinusButton = findViewById(R.id.pilot_minus);
        pilotMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.pilot_counter);
                final TextView mTextView2 = (TextView) findViewById(R.id.total_counter);
                player.decrementPilotSkillPoints();
                mTextView.setText("" + player.getPilotSkillPoints());
                mTextView2.setText("" + player.getRemainingCount());
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
                final TextView mTextView2 = (TextView) findViewById(R.id.total_counter);
                player.incrementFighterSkillPoints();
                mTextView.setText("" + player.getFighterSkillPoints());
                mTextView2.setText("" + player.getRemainingCount());
            }
        });

        fighterMinusButton = findViewById(R.id.fighter_minus);
        fighterMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.fighter_counter);
                final TextView mTextView2 = (TextView) findViewById(R.id.total_counter);
                player.decrementFighterSkillPoints();
                mTextView.setText("" + player.getFighterSkillPoints());
                mTextView2.setText("" + player.getRemainingCount());
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
                final TextView mTextView2 = (TextView) findViewById(R.id.total_counter);
                player.incrementTraderSkillPoints();
                mTextView.setText("" + player.getTraderSkillPoints());
                mTextView2.setText("" + player.getRemainingCount());
            }
        });

        traderMinusButton = findViewById(R.id.trader_minus);
        traderMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.trader_counter);
                final TextView mTextView2 = (TextView) findViewById(R.id.total_counter);
                player.decrementTraderSkillPoints();
                mTextView.setText("" + player.getTraderSkillPoints());
                mTextView2.setText("" + player.getRemainingCount());
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
                final TextView mTextView2 = (TextView) findViewById(R.id.total_counter);
                player.incrementEngineerSkillPoints();
                mTextView.setText("" + player.getEngineerSkillPoints());
                mTextView2.setText("" + player.getRemainingCount());
            }
        });

        engineerMinusButton = findViewById(R.id.engineer_minus);
        engineerMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView mTextView = (TextView) findViewById(R.id.engineer_counter);
                final TextView mTextView2 = (TextView) findViewById(R.id.total_counter);
                player.decrementEngineerSkillPoints();
                mTextView.setText("" + player.getEngineerSkillPoints());
                mTextView2.setText("" + player.getRemainingCount());
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
                EditText myName = findViewById(R.id.editText);
                player.setName(myName.getText().toString());
                Log.e("main", player.toString());
            }
        });



	}

}
