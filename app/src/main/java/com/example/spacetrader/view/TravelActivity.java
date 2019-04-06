package com.example.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private TextView shipHealthTextView;
    private Spinner solarSystemSpinner;
    private Spinner planetSpinner;

    private SolarSystem selectedSolarSystem; //solar system selected in the drop down menu
    private Planet selectedPlanet; //planet selected in the drop down menu

    private int mapSetting;
    private final Handler handler = new Handler(); //handler for map update

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        game = Game.getInstance();

        //set up spinners
        selectedSolarSystem = game.getCurrentSolarSystem();
        selectedPlanet = game.getCurrentPlanet();

        solarSystemSpinner = findViewById(R.id.solar_system_spinner);
        planetSpinner = findViewById(R.id.planet_spinner);

        solarSystemSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, game.getSolarSystemsInRange()));
        planetSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, game.getPlanetsInRange(selectedSolarSystem)));


        solarSystemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSolarSystem = (SolarSystem)solarSystemSpinner.getSelectedItem();
                //sets selected solar system to selected position in dropdown
                planetSpinner.setAdapter(createNewAdapter(selectedSolarSystem));
                //resets planets in planet dropdown
                selectedPlanet = (Planet) planetSpinner.getSelectedItem();
                // sets the selected planet to the selected position
                Map.setSelectedPlanet(selectedPlanet);
                //sets the map setting to draw the correct planet
                Map.setSelectedSolarSystem(selectedSolarSystem);
                //sets the map setting to draw the correct solar system
                updateText();
                //updates all text fields (including the fuel required to get to the
                // newly selected solar system)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        planetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 selectedPlanet = (Planet) planetSpinner.getSelectedItem();
                 Map.setSelectedPlanet(selectedPlanet);
                 updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //set up map
        mapView = findViewById(R.id.mapView);
        mapSetting = 1;
        map = new Map(mapSetting);
        mapView.setBackground(map);

        //update map
        class MyRunnable implements Runnable {
            private final Handler handler;
            MyRunnable(Handler handler) {
                this.handler = handler;
            }
            @Override
            public void run() {
                this.handler.postDelayed(this, 10);
                update();
            }
        }
        handler.post(new MyRunnable(handler));

        //set up text
        solarSystemFuelTextView = findViewById(R.id.fuelSS_text);
        planetFuelTextView = findViewById(R.id.fuelp_text);
        planetTextView = findViewById(R.id.location_text);
        locationTextView = findViewById(R.id.current_location_text);
        overallFuelTextView = findViewById(R.id.ship_fuel_text);
        shipHealthTextView = findViewById(R.id.ship_status);

        updateText();
    }

    /**
     * Creates a new arrayAdapter for the planet dropdown so that it populates with the
     * planet names from the selected universe
     * @param s selected Solar System
     * @return ArrayAdapter with planet Strings.
     */
    private ArrayAdapter<Planet> createNewAdapter (SolarSystem s) {
        final ArrayAdapter<Planet> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, game.getPlanetsInRange(s));
        return adapter;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Update methods
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Updates the map using the current mapSetting
     */
    private void update() {
        map = new Map(mapSetting);
        mapView.setBackground(map);
    }

    /**
     * Updates the mapSetting, and then updates the map
     * @param newMapSetting
     */
    private void update(int newMapSetting) {
        mapSetting = newMapSetting;
        update();
    }

    /**
     * Updates text fields
     */
    private void updateText() {
        solarSystemFuelTextView.setText("Fuel: " + (int)game.getDistance(selectedSolarSystem));
        planetFuelTextView.setText("Fuel: " + game.getDistance(selectedPlanet));
        overallFuelTextView.setText("Remaining Ship Fuel: " + game.getFuel());
        shipHealthTextView.setText("Ship Health " + game.getShipHealth() + "/100");
        locationTextView.setText("Current Location: " + game.getCurrentPlanetName()
                + " (" + game.getCurrentSolarSystemName() + ")");
        if (mapSetting == 0) {
            planetTextView.setText("Planet: " + selectedPlanet);
        } else if (mapSetting == 1) {
            planetTextView.setText("Solar System: " + selectedSolarSystem);
        } else if (mapSetting == 2) {
            planetTextView.setText("Universe");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Buttons
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickViewSolarSystem(View v) {
        mapSetting = (mapSetting > 0) ? (mapSetting - 1) : 0;
        update(mapSetting);
        updateText();
    }

    public void onClickViewUniverse(View v) {
        mapSetting = (mapSetting < 2) ? (mapSetting + 1) : 2;
        update(mapSetting);
        updateText();
    }

    public void onClickBackTravel(View v) {
        handler.removeCallbacksAndMessages(null);//ends the handler
        finish();
    }

    public void onClickTravelVerified(View v) {
        if (game.travel(selectedSolarSystem, selectedPlanet)) {
            solarSystemSpinner.setAdapter(new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, game.getSolarSystemsInRange()));
            planetSpinner.setAdapter(createNewAdapter(selectedSolarSystem));
            updateText();
            Intent intent = new Intent(TravelActivity.this, EncounterActivity.class);
            startActivity(intent); //goes to encounter.
        }
    }

    public void onClickShipStatus(View v) {
        game.incrementFuel();
        solarSystemSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, game.getSolarSystemsInRange()));
        planetSpinner.setAdapter(createNewAdapter(selectedSolarSystem));
        overallFuelTextView.setText("Remaining Ship Fuel: " + game.getFuel());
        updateText();
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        if (hasFocus) {
            updateText();
        }
    }
}
