package com.example.spacetrader.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Game;
import com.example.spacetrader.entity.Resource;

import java.io.Serializable;

public class TradingActivity extends AppCompatActivity {

    private Game game;
    private TextView creditsTextView;
    private TextView cargoTextView;
    private Button sellWaterButton;
    private Button sellFursButton;
    private Button sellFoodButton;
    private Button sellOreButton;
    private Button sellGamesButton;
    private Button sellFirearmsButton;
    private Button sellMedicineButton;
    private Button sellMachinesButton;
    private Button sellNarcoticsButton;
    private Button sellRobotsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trading);
        game = (Game)getIntent().getSerializableExtra("Game");//gets game from main Activity
        creditsTextView = findViewById(R.id.trading_credits);
        cargoTextView = findViewById(R.id.trading_cargo);
        sellWaterButton = findViewById(R.id.water_sell);
        sellFursButton = findViewById(R.id.furs_sell);
        sellFoodButton = findViewById(R.id.food_sell);
        sellOreButton = findViewById(R.id.ore_sell);
        sellGamesButton = findViewById(R.id.game_sell);
        sellFirearmsButton = findViewById(R.id.firearms_sell);
        sellMedicineButton = findViewById(R.id.medicine_sell);
        sellMachinesButton = findViewById(R.id.machines_sell);
        sellNarcoticsButton = findViewById(R.id.narcotics_sell);
        sellRobotsButton = findViewById(R.id.robots_sell);
        updateText();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Buy Buttons
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickBuyWater(View v) {
        game.addCargo(Resource.WATER);
        updateText();
    }

    public void onClickBuyFurs(View v) {
        game.addCargo(Resource.FURS);
        updateText();
    }

    public void onClickBuyFood(View v) {
        game.addCargo(Resource.FOOD);
        updateText();
    }

    public void onClickBuyOre(View v) {
        game.addCargo(Resource.ORE);
        updateText();
    }

    public void onClickBuyGames(View v) {
        game.addCargo(Resource.GAMES);
        updateText();
    }

    public void onClickBuyFirearms(View v) {
        game.addCargo(Resource.FIREARMS);
        updateText();
    }

    public void onClickBuyMedicine(View v) {
        game.addCargo(Resource.MEDICINE);
        updateText();
    }

    public void onClickBuyMachines(View v) {
        game.addCargo(Resource.MACHINES);
        updateText();
    }

    public void onClickBuyNarcotics(View v) {
        game.addCargo(Resource.NARCOTICS);
        updateText();
    }

    public void onClickBuyRobots(View v) {
        game.addCargo(Resource.ROBOTS);
        updateText();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Update Text for Sell Buttons
    ///////////////////////////////////////////////////////////////////////////////////////

    public void updateText() {
        sellWaterButton.setText("Sell (" + game.getCargoCount(Resource.WATER) + ")");
        sellFursButton.setText("Sell (" + game.getCargoCount(Resource.FURS) + ")");
        sellFoodButton.setText("Sell (" + game.getCargoCount(Resource.FOOD) + ")");
        sellOreButton.setText("Sell (" + game.getCargoCount(Resource.ORE) + ")");
        sellGamesButton.setText("Sell (" + game.getCargoCount(Resource.GAMES) + ")");
        sellFirearmsButton.setText("Sell (" + game.getCargoCount(Resource.FIREARMS) + ")");
        sellMedicineButton.setText("Sell (" + game.getCargoCount(Resource.MEDICINE) + ")");
        sellMachinesButton.setText("Sell (" + game.getCargoCount(Resource.MACHINES) + ")");
        sellNarcoticsButton.setText("Sell (" + game.getCargoCount(Resource.WATER) + ")");
        sellRobotsButton.setText("Sell (" + game.getCargoCount(Resource.WATER) + ")");
        cargoTextView.setText("Cargo: " + game.getCurrentCargo() + " / " + game.getMaxCargo());
        creditsTextView.setText("Credits: " + game.getPlayerCredits());
    }


    ///////////////////////////////////////////////////////////////////////////////////////
    //Sell Buttons
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickSellWater(View v) {
        game.removeCargo(Resource.WATER);
        updateText();
    }

    public void onClickSellFurs(View v) {
        game.removeCargo(Resource.FURS);
        updateText();
    }

    public void onClickSellFood(View v) {
        game.removeCargo(Resource.FOOD);
        updateText();
    }

    public void onClickSellOre(View v) {
        game.removeCargo(Resource.ORE);
        updateText();
    }

    public void onClickSellGames(View v) {
        game.removeCargo(Resource.GAMES);
        updateText();
    }

    public void onClickSellFirearms(View v) {
        game.removeCargo(Resource.FIREARMS);
        updateText();
    }

    public void onClickSellMedicine(View v) {
        game.removeCargo(Resource.MEDICINE);
        updateText();
    }

    public void onClickSellMachines(View v) {
        game.removeCargo(Resource.MACHINES);
        updateText();
    }

    public void onClickSellNarcotics(View v) {
        game.removeCargo(Resource.NARCOTICS);
        updateText();
    }

    public void onClickSellRobots(View v) {
        game.removeCargo(Resource.ROBOTS);
        updateText();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Back
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickBack(View v) {
        finish();
    }
}
