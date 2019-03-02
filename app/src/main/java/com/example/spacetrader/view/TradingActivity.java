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
    //Buy / Sell Buttons
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
    //Back
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickBack(View v) {
        finish();
    }
}
