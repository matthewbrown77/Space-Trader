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

public class TradingActivity extends AppCompatActivity {

    private Game game;
    private TextView marketNameTextView;
    private TextView creditsTextView;
    private TextView cargoTextView;
    private TextView resourceTypeTextView;
    private TextView techLevelTextView;
    private TextView governmentTextView;

    private TextView waterTextView;
    private TextView fursTextView;
    private TextView foodTextView;
    private TextView oreTextView;
    private TextView gamesTextView;
    private TextView firearmsTextView;
    private TextView medicineTextView;
    private TextView machinesTextView;
    private TextView narcoticsTextView;
    private TextView robotsTextView;

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
        //game = (Game)getIntent().getSerializableExtra("Game");//gets game from main Activity
        game = Game.getInstance();
        marketNameTextView = findViewById(R.id.location_title);
        creditsTextView = findViewById(R.id.trading_credits);
        cargoTextView = findViewById(R.id.trading_cargo);
        resourceTypeTextView = findViewById(R.id.resource_type_title);
        techLevelTextView = findViewById(R.id.tech_level_title);
        governmentTextView = findViewById(R.id.government_title);

        waterTextView = findViewById(R.id.water_title);
        fursTextView = findViewById(R.id.fur_title);
        foodTextView = findViewById(R.id.food_title);
        oreTextView = findViewById(R.id.ore_title);
        gamesTextView = findViewById(R.id.games_title);
        firearmsTextView = findViewById(R.id.firearms_title);
        medicineTextView = findViewById(R.id.medicine_title);
        machinesTextView = findViewById(R.id.machines_title);
        narcoticsTextView = findViewById(R.id.narcotics_title);
        robotsTextView = findViewById(R.id.robots_title);

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

    ///////////////////////////////////////////////////////////////////////////////////////
    //Update Text for Sell Buttons
    ///////////////////////////////////////////////////////////////////////////////////////

    public String generateResourceText(Resource resource) {
        return "" + resource + (game.allowedToBuy(resource)||game.allowedToSell(resource) ? " - $" + game.getResourcePrice(resource): "");
    }

    public String generateBuyResourceText(Resource resource) {
        return game.allowedToBuy(resource) ? "Buy (" + game.getResourceAmount(resource) + ")": "N/A";
    }

    public String generateSellResourceText(Resource resource) {
        return game.allowedToSell(resource) ? "Sell (" + game.getCargoCount(resource) + ")" : "N/A";
    }


    public void updateText() {
        waterTextView.setText(generateResourceText(Resource.WATER));
        buyWaterButton.setText(generateBuyResourceText(Resource.WATER));
        sellWaterButton.setText(generateSellResourceText(Resource.WATER));

        fursTextView.setText(generateResourceText(Resource.FURS));
        buyFursButton.setText(generateBuyResourceText(Resource.FURS));
        sellFursButton.setText(generateSellResourceText(Resource.FURS));

        foodTextView.setText(generateResourceText(Resource.FOOD));
        buyFoodButton.setText(generateBuyResourceText(Resource.FOOD));
        sellFoodButton.setText(generateSellResourceText(Resource.FOOD));

        oreTextView.setText(generateResourceText(Resource.ORE));
        buyOreButton.setText(generateBuyResourceText(Resource.ORE));
        sellOreButton.setText(generateSellResourceText(Resource.ORE));

        gamesTextView.setText(generateResourceText(Resource.GAMES));
        buyGamesButton.setText(generateBuyResourceText(Resource.GAMES));
        sellGamesButton.setText(generateSellResourceText(Resource.GAMES));

        firearmsTextView.setText(generateResourceText(Resource.FIREARMS));
        buyFirearmsButton.setText(generateBuyResourceText(Resource.FIREARMS));
        sellFirearmsButton.setText(generateSellResourceText(Resource.FIREARMS));

        medicineTextView.setText(generateResourceText(Resource.MEDICINE));
        buyMedicineButton.setText(generateBuyResourceText(Resource.MEDICINE));
        sellMedicineButton.setText(generateSellResourceText(Resource.MEDICINE));

        machinesTextView.setText(generateResourceText(Resource.MACHINES));
        buyMachinesButton.setText(generateBuyResourceText(Resource.MACHINES));
        sellMachinesButton.setText(generateSellResourceText(Resource.MACHINES));

        narcoticsTextView.setText(generateResourceText(Resource.NARCOTICS));
        buyNarcoticsButton.setText(generateBuyResourceText(Resource.NARCOTICS));
        sellNarcoticsButton.setText(generateSellResourceText(Resource.NARCOTICS));

        robotsTextView.setText(generateResourceText(Resource.ROBOTS));
        buyRobotsButton.setText(generateBuyResourceText(Resource.ROBOTS));
        sellRobotsButton.setText(generateSellResourceText(Resource.ROBOTS));

        cargoTextView.setText("Cargo: " + game.getCurrentCargo() + " / " + game.getMaxCargo());
        creditsTextView.setText("Credits: " + game.getPlayerCredits());

        techLevelTextView.setText("TL: " + game.getCurrentPlanetTechLevel());
        resourceTypeTextView.setText("RT: " + game.getCurrentPlanetResourceType());
        marketNameTextView.setText("Market: " + game.getCurrentPlanetName());
        governmentTextView.setText("GOV: " + game.getGovernmentType());
    }

}
