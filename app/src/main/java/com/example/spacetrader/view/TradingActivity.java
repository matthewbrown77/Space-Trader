package com.example.spacetrader.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Game;
import com.example.spacetrader.entity.Resource;

import java.io.Serializable;

public class TradingActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trading);
        game = (Game)getIntent().getSerializableExtra("Game");//gets game from main Activity
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Buy Buttons
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickBuyWater(View v) {
        game.addCargo(Resource.WATER);
    }

    public void onClickBuyFurs(View v) {
        game.addCargo(Resource.FURS);
    }

    public void onClickBuyFood(View v) {
        game.addCargo(Resource.FOOD);
    }

    public void onClickBuyOre(View v) {
        game.addCargo(Resource.ORE);
    }

    public void onClickBuyGames(View v) {
        game.addCargo(Resource.GAMES);
    }

    public void onClickBuyFirearms(View v) {
        game.addCargo(Resource.FIREARMS);
    }

    public void onClickBuyMedicine(View v) {
        game.addCargo(Resource.MEDICINE);
    }

    public void onClickBuyMachines(View v) {
        game.addCargo(Resource.MACHINES);
    }

    public void onClickBuyNarcotics(View v) {
        game.addCargo(Resource.NARCOTICS);
    }

    public void onClickBuyRobots(View v) {
        game.addCargo(Resource.ROBOTS);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Sell Buttons
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickSellWater(View v) {
        game.removeCargo(Resource.WATER);
    }

    public void onClickSellFurs(View v) {
        game.removeCargo(Resource.FURS);
    }

    public void onClickSellFood(View v) {
        game.removeCargo(Resource.FOOD);
    }

    public void onClickSellOre(View v) {
        game.removeCargo(Resource.ORE);
    }

    public void onClickSellGames(View v) {
        game.removeCargo(Resource.GAMES);
    }

    public void onClickSellFirearms(View v) {
        game.removeCargo(Resource.FIREARMS);
    }

    public void onClickSellMedicine(View v) {
        game.removeCargo(Resource.MEDICINE);
    }

    public void onClickSellMachines(View v) {
        game.removeCargo(Resource.MACHINES);
    }

    public void onClickSellNarcotics(View v) {
        game.removeCargo(Resource.NARCOTICS);
    }

    public void onClickSellRobots(View v) {
        game.removeCargo(Resource.ROBOTS);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Back
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickBack(View v) {
        finish();
    }
}
