package com.example.spacetrader.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.graphics.Paint;
import android.graphics.drawable.*;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.os.Handler;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Coordinate;
import com.example.spacetrader.entity.Game;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.SolarSystem;

import java.util.List;
import java.util.ArrayList;

public class TravelActivity extends AppCompatActivity {

    private View mapView;
    private Game game;
    private Map map;
    private TextView planetTextView;
    private Spinner solarSystemSpinner;
    private Spinner planetSpinner;

    private SolarSystem selectedSolarSystem;
    private Planet selectedPlanet;
    private int mapSetting;
    private final Handler handler = new Handler(); //handler for map update

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        game = Game.getInstance();

        //set up map
        mapView = findViewById(R.id.mapView);
        mapSetting = 0;
        map = new Map(mapSetting);
        mapView.setBackground(map);

        //update map
        class MyRunnable implements Runnable {
            private Handler handler;
            public MyRunnable(Handler handler) {
                this.handler = handler;
            }
            @Override
            public void run() {
                this.handler.postDelayed(this, 10);
                update();
            }
        }
        handler.post(new MyRunnable(handler));

        //set up spinners
        selectedSolarSystem = game.getCurrentSolarSystem();
        selectedPlanet = game.getCurrentPlanet();

        solarSystemSpinner = findViewById(R.id.solar_system_spinner);
        planetSpinner = findViewById(R.id.planet_spinner);

        solarSystemSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, game.getSolarSystems()));
        planetSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedSolarSystem.getPlanets()));

        solarSystemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSolarSystem = game.getSolarSystems().get(position);
                planetSpinner.setAdapter(createNewAdapter(selectedSolarSystem));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        planetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 selectedPlanet = selectedSolarSystem.getPlanets().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //not a good way to do it. change below.
        solarSystemSpinner.setSelection(game.getSolarSystems().indexOf(selectedSolarSystem));
        Log.e("main", "Solar System index: " + game.getSolarSystems().indexOf(selectedSolarSystem));
        planetSpinner.setSelection(selectedSolarSystem.getPlanets().indexOf(selectedPlanet));
        Log.e("main", "Planet index: " + selectedSolarSystem.getPlanets().indexOf(selectedPlanet));
        planetTextView = findViewById(R.id.location_text);
        planetTextView.setText("Planet: " + game.getCurrentPlanetName());
    }


    /**
     * Creates a new arrayAdapter for the planet dropdown so that it populates with the
     * planet names from the selected universe
     * @param s selected Solar System
     * @return ArrayAdapter with planet Strings.
     */
    private ArrayAdapter<Planet> createNewAdapter (SolarSystem s) {
        final ArrayAdapter<Planet> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, s.getPlanets());
        return adapter;
    }

    /**
     * Updates the map using the current mapSetting
     */
    public void update() {
        map = new Map(mapSetting);
        mapView.setBackground(map);
    }

    /**
     * Updates the mapSetting, and then updates the map
     * @param newMapSetting
     */
    public void update(int newMapSetting) {
        mapSetting = newMapSetting;
        update();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Map Setting Buttons
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickViewSolarSystem(View v) {
        update(0);
    }

    public void onClickViewUniverse(View v) {
        update(1);
    }

    public void onClickBackTravel(View v) {
        handler.removeCallbacksAndMessages(null);
        finish();
    }

    public void onClickTravelVerified(View v) {
        game.travel(selectedSolarSystem, selectedPlanet);
    }
}
