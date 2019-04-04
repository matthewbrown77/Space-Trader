package com.example.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Game;

import java.io.File;


public class MainActivity extends AppCompatActivity {

	private TextView nameTextView;
	private TextView creditsTextView;
	private TextView shipNameTextView;
	private TextView pilotCounterTextView;
	private TextView fighterCounterTextView;
	private TextView traderCounterTextView;
	private TextView engineerCounterTextView;
	private TextView cargoCounterTextView;
	private TextView shipHealthTextView;

	private TextView planetTextView;
	private Game game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		game = Game.getInstance();
		update();
	}

	public void onWindowFocusChanged (boolean hasFocus) {
		if (hasFocus) {
			update();
			File file = new File(this.getFilesDir(), Game.DEFAULT_BINARY_FILE_NAME);
			game.saveBinary(file); //saves game
		}
	}

	private void update() {
		nameTextView = findViewById(R.id.text_commander_name);
		creditsTextView = findViewById(R.id.text_credits);
		pilotCounterTextView = findViewById(R.id.pilot_counter);
		fighterCounterTextView = findViewById(R.id.fighter_counter);
		traderCounterTextView = findViewById(R.id.trader_counter);
		engineerCounterTextView = findViewById(R.id.engineer_counter);
		shipNameTextView = findViewById(R.id.text_ship_type);
		cargoCounterTextView = findViewById(R.id.text_cargo_amount);
		shipHealthTextView = findViewById(R.id.text_ship_health);
		planetTextView = findViewById(R.id.text_planet_title);

		nameTextView.setText("Commander " + game.getPlayerName());
		creditsTextView.setText("Credits: $" + game.getPlayerCredits());
		int[] skillPointsArray = game.getPlayerSkillPointsArray();
		pilotCounterTextView.setText("Pilot: " + skillPointsArray[0]);
		fighterCounterTextView.setText("Fighter: " + skillPointsArray[1]);
		traderCounterTextView.setText("Trader: " + skillPointsArray[2]);
		engineerCounterTextView.setText("Engineer: " + skillPointsArray[3]);
		shipNameTextView.setText("Ship: " + game.getPlayerShipName());
		cargoCounterTextView.setText("Cargo: " + game.getCurrentCargo() + " / " + game.getMaxCargo());
		shipHealthTextView.setText("Ship Health: " + game.getShipHealth() + "/100");
		planetTextView.setText("" + game.getCurrentPlanet());
	}

	///////////////////////////////////////////////////////////////////////////////////////
	//Navigation Buttons
	///////////////////////////////////////////////////////////////////////////////////////

    public void onClickTravel(View v) {
        Intent intent = new Intent(MainActivity.this, TravelActivity.class);
        startActivity(intent); //goes to Inventory.
    }

	public void onClickInventory(View v) {
		Intent intent = new Intent(MainActivity.this, InventoryActivity.class);
		startActivity(intent); //goes to Inventory.
	}

	public void onClickTrading(View v) {
		Intent intent = new Intent(MainActivity.this, TradingActivity.class);
		//intent.putExtra("Game", game);
		startActivity(intent); //goes to Trading Activity.
	}

	public void onClickUpgradeShip(View v) {
		Intent intent = new Intent(MainActivity.this, ShipUpgradeActivity.class);
		startActivity(intent); //goes to encounter.
	}

	public void onClickPlanetInfo(View v) {
		Intent intent = new Intent(MainActivity.this, PlanetInfoActivity.class);
		startActivity(intent); //goes to planet info.
	}

}
