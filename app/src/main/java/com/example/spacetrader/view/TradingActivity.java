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
    private TextView marketNameTextView;
    private TextView creditsTextView;
    private TextView cargoTextView;
    private TextView resourceTypeTextView;
    private TextView techLevelTextView;

    private Button buyWaterButton;
    private Button buyFursButton;
    private Button buyFoodButton;
    private Button buyOreButton;
    private Button buyGamesButton;
    private Button buyFirearmsButton;
    private Button buyMedicineButton;
    private Button buyMachinesButton;
    private Button buyNarcoticsButton;
    private Button buyRobotsButton;

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
        marketNameTextView = findViewById(R.id.location_title);
        creditsTextView = findViewById(R.id.trading_credits);
        cargoTextView = findViewById(R.id.trading_cargo);
        resourceTypeTextView = findViewById(R.id.resource_type_title);
        techLevelTextView = findViewById(R.id.tech_level_title);

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

        buyWaterButton = findViewById(R.id.water_buy);
        buyFursButton = findViewById(R.id.furs_buy);
        buyFoodButton = findViewById(R.id.food_buy);
        buyOreButton = findViewById(R.id.ore_buy);
        buyGamesButton = findViewById(R.id.game_buy);
        buyFirearmsButton = findViewById(R.id.firearms_buy);
        buyMedicineButton = findViewById(R.id.medicine_buy);
        buyMachinesButton = findViewById(R.id.machines_buy);
        buyNarcoticsButton = findViewById(R.id.narcotics_buy);
        buyRobotsButton = findViewById(R.id.robots_buy);

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
        buyWaterButton.setText(game.resourceAvailableToBuy(Resource.WATER) ? "Buy" : "N/A");
        sellWaterButton.setText(game.resourceAvailableToSell(Resource.WATER) ? "Sell (" + game.getCargoCount(Resource.WATER) + ")" : "N/A");

        buyFursButton.setText(game.resourceAvailableToBuy(Resource.FURS) ? "Buy" : "N/A");
        sellFursButton.setText(game.resourceAvailableToSell(Resource.FURS) ? "Sell (" + game.getCargoCount(Resource.FURS) + ")" : "N/A");

        buyFoodButton.setText(game.resourceAvailableToBuy(Resource.FOOD) ? "Buy" : "N/A");
        sellFoodButton.setText(game.resourceAvailableToSell(Resource.FOOD) ? "Sell (" + game.getCargoCount(Resource.FOOD) + ")" : "N/A");

        buyOreButton.setText(game.resourceAvailableToBuy(Resource.ORE) ? "Buy" : "N/A");
        sellOreButton.setText(game.resourceAvailableToSell(Resource.ORE) ? "Sell (" + game.getCargoCount(Resource.ORE) + ")" : "N/A");

        buyGamesButton.setText(game.resourceAvailableToBuy(Resource.GAMES) ? "Buy" : "N/A");
        sellGamesButton.setText(game.resourceAvailableToSell(Resource.GAMES) ? "Sell (" + game.getCargoCount(Resource.GAMES) + ")" : "N/A");

        buyFirearmsButton.setText(game.resourceAvailableToBuy(Resource.FIREARMS) ? "Buy" : "N/A");
        sellFirearmsButton.setText(game.resourceAvailableToSell(Resource.FIREARMS) ? "Sell (" + game.getCargoCount(Resource.FIREARMS) + ")" : "N/A");

        buyMedicineButton.setText(game.resourceAvailableToBuy(Resource.MEDICINE) ? "Buy" : "N/A");
        sellMedicineButton.setText(game.resourceAvailableToSell(Resource.MEDICINE) ? "Sell (" + game.getCargoCount(Resource.MEDICINE) + ")" : "N/A");

        buyMachinesButton.setText(game.resourceAvailableToBuy(Resource.MACHINES) ? "Buy" : "N/A");
        sellMachinesButton.setText(game.resourceAvailableToSell(Resource.MACHINES) ? "Sell (" + game.getCargoCount(Resource.MACHINES) + ")" : "N/A");

        buyNarcoticsButton.setText(game.resourceAvailableToBuy(Resource.NARCOTICS) ? "Buy" : "N/A");
        sellNarcoticsButton.setText(game.resourceAvailableToSell(Resource.NARCOTICS) ? "Sell (" + game.getCargoCount(Resource.NARCOTICS) + ")" : "N/A");

        buyRobotsButton.setText(game.resourceAvailableToBuy(Resource.ROBOTS) ? "Buy" : "N/A");
        sellRobotsButton.setText(game.resourceAvailableToSell(Resource.ROBOTS) ? "Sell (" + game.getCargoCount(Resource.ROBOTS) + ")" : "N/A");

        cargoTextView.setText("Cargo: " + game.getCurrentCargo() + " / " + game.getMaxCargo());
        creditsTextView.setText("Credits: " + game.getPlayerCredits());

        techLevelTextView.setText("TL: " + game.getCurrentPlanetTechLevel());
        resourceTypeTextView.setText("RT: " + game.getCurrentPlanetResourceType());
        marketNameTextView.setText("Market: " + game.getCurrentPlanetName());
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
