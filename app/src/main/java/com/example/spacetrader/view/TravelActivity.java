package com.example.spacetrader.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.os.Handler;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Game;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.SolarSystem;

public class TravelActivity extends AppCompatActivity {

    private View mapView;
    private Game game;
    private Map map;
    private TextView planetTextView;
    private TextView solarSystemFuelTextView;
    private TextView planetFuelTextView;
    private TextView overallFuelTextView;
    private TextView locationTextView;
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

        solarSystemSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, game.getSolarSystemsInRange()));
        planetSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, game.getPlanetsInRange(selectedSolarSystem)));

        solarSystemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSolarSystem = game.getSolarSystemsInRange().get(position);
                planetSpinner.setAdapter(createNewAdapter(selectedSolarSystem));
                updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        planetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 selectedPlanet = game.getPlanetsInRange(selectedSolarSystem).get(position);
                 updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //set up text
        solarSystemFuelTextView = findViewById(R.id.fuelSS_text);
        planetFuelTextView = findViewById(R.id.fuelp_text);
        planetTextView = findViewById(R.id.location_text);
        locationTextView = findViewById(R.id.current_location_text);
        overallFuelTextView = findViewById(R.id.ship_fuel_text);
        planetTextView.setText("Planet: " + game.getCurrentPlanetName());

        updateText();
    }

    /**
     * Creates a new arrayAdapter for the planet dropdown so that it populates with the
     * planet names from the selected universe
     * @param s selected Solar System
     * @return ArrayAdapter with planet Strings.
     */
    private ArrayAdapter<Planet> createNewAdapter (SolarSystem s) {
        final ArrayAdapter<Planet> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, game.getPlanetsInRange(s));
        return adapter;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Update methods
    ///////////////////////////////////////////////////////////////////////////////////////

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

    private void updateText() {
        solarSystemFuelTextView.setText("Fuel: " + (int)game.getDistance(selectedSolarSystem));
        planetFuelTextView.setText("Fuel: " + game.getDistance(selectedPlanet));
        planetSpinner.setSelection(game.getPlanetsInRange(selectedSolarSystem).indexOf(selectedPlanet));
        solarSystemSpinner.setSelection(game.getSolarSystemsInRange().indexOf(selectedSolarSystem));
        overallFuelTextView.setText("Remaining Ship Fuel: " + game.getFuel());
        locationTextView.setText("Current Location: " + game.getCurrentPlanetName() + " (" + game.getCurrentSolarSystemName() + ")");

    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Map Setting Buttons
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickViewSolarSystem(View v) {
        mapSetting = mapSetting > 0 ? mapSetting - 1 : 0;
        update(mapSetting);
    }

    public void onClickViewUniverse(View v) {
        mapSetting = mapSetting < 2 ? mapSetting + 1 : 2;
        update(mapSetting);
    }

    public void onClickBackTravel(View v) {
        handler.removeCallbacksAndMessages(null);//ends the handler
        finish();
    }

    public void onClickTravelVerified(View v) {
        game.travel(selectedSolarSystem, selectedPlanet);
        solarSystemSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, game.getSolarSystemsInRange()));
        planetSpinner.setAdapter(createNewAdapter(selectedSolarSystem));
        updateText();
    }
}
