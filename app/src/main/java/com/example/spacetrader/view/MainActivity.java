package com.example.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Game;
import com.example.spacetrader.entity.Player;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

	private TextView nameTextView;
	private TextView creditsTextView;
	private TextView shipNameTextView;
	private TextView pilotCounterTextView;
	private TextView fighterCounterTextView;
	private TextView traderCounterTextView;
	private TextView engineerCounterTextView;
	private TextView mTextMessage;
	private Game game;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_travel:
					mTextMessage.setText(R.string.title_travel);
					return true;
				case R.id.navigation_store:
					mTextMessage.setText(R.string.title_store);
					return true;
				case R.id.navigation_inventory:
					mTextMessage.setText(R.string.title_inventory);
					return true;
			}
			return false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Serializable player = getIntent().getSerializableExtra("Player");//gets player from CreatePlayerActivity
		game = new Game((Player)player); //adds the player to the game.

		mTextMessage = (TextView) findViewById(R.id.message);
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		update();
	}

	public void update() {
		nameTextView = findViewById(R.id.text_commander_name);
		creditsTextView = findViewById(R.id.text_credits);
		pilotCounterTextView = findViewById(R.id.pilot_counter);
		fighterCounterTextView = findViewById(R.id.fighter_counter);
		traderCounterTextView = findViewById(R.id.trader_counter);
		engineerCounterTextView = findViewById(R.id.engineer_counter);
		shipNameTextView = findViewById(R.id.text_ship_type);

		nameTextView.setText("Commander " + game.getPlayerName());
		creditsTextView.setText("" + game.getPlayerCredits());
		int[] skillPointsArray = game.getPlayerSkillPointsArray();
		pilotCounterTextView.setText("" + skillPointsArray[0]);
		fighterCounterTextView.setText("" + skillPointsArray[1]);
		traderCounterTextView.setText("" + skillPointsArray[2]);
		engineerCounterTextView.setText("" + skillPointsArray[3]);
		shipNameTextView.setText(game.getPlayerShipName());
	}

}
