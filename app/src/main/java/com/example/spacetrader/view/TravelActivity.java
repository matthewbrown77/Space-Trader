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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        game = Game.getInstance();

        //set up map
        mapView = findViewById(R.id.mapView);
        map = new Map(0);
        mapView.setBackground(map);

        final int i = 0;
        //final TextView textView = new TextView(this);
        final Handler handler = new Handler();
        class MyRunnable implements Runnable {
            private Handler handler;
            public MyRunnable(Handler handler) {
                this.handler = handler;
                map.update();
            }
            @Override
            public void run() {
                this.handler.postDelayed(this, 10);
                map.update();
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
        //planetTextView = findViewById(R.id.location_text);
        //planetTextView.setText("Planet: " + game.getCurrentPlanetName());
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

    public class Map extends Drawable {

        private int alpha;
        private ColorFilter colorFilter;
        private int mapType;
        private Canvas canvas;

        public Map (int mapType) {
            this.mapType = mapType;
            mapSetting = mapType;
        }

        public void update() {
            mapView.setBackground(new Map(mapSetting));
        }

        @Override
        public void draw(Canvas canvas) {
            this.canvas = canvas;
            switch(mapType) {
                case 0: {
                    drawSolarSystem(canvas);
                } break;
                case 1: {
                    drawUniverse(canvas);
                } break;
            }
        }

        private void drawSolarSystem(Canvas canvas) {
            List<Planet> planets = game.getCurrentSolarSystemPlanets();
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawPaint(paint);
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(canvas.getWidth()/2,canvas.getWidth()/2,25, paint);
            int icd = canvas.getWidth()/2 / (planets.size() + 1); //inner circle distance
            for (int i = 0; i < planets.size(); i++) {
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.WHITE);
                canvas.drawCircle(canvas.getWidth()/2,canvas.getWidth()/2,icd * (i+1), paint);
                double period = 9000 * Math.sqrt(Math.pow(i+1,3)) / Math.sqrt(8);
                double angle = ((System.currentTimeMillis()/10)%period)/period * 6.283;
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(planets.get(i).getColor());
                canvas.drawCircle(canvas.getWidth()/2 + (int)(icd * (i + 1) * Math.cos(angle)),canvas.getWidth()/2 +(int)(icd * (i + 1) * Math.sin(angle)),15, paint);
                paint.setColor(Color.WHITE);
                paint.setTextSize(50);
                canvas.drawText(planets.get(i).getName(), canvas.getWidth()/2 + (int)(icd * (i + 1) * Math.cos(angle)) + 10, canvas.getWidth()/2 +(int)(icd * (i + 1) * Math.sin(angle) - 30), paint);
            }
        }

        private void drawUniverse(Canvas canvas) {
            int radius = 10;
            int buffer = 100;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawPaint(paint);
            List<SolarSystem> solarSystemList = game.getSolarSystems();
            for (SolarSystem s: solarSystemList) {
                int x = buffer + (int)((double) s.getCoordinate().getX() / Coordinate.MAX_X * (canvas.getWidth() - buffer*2));
                int y = buffer + (int)((double) s.getCoordinate().getY() / Coordinate.MAX_Y * (canvas.getHeight() - buffer*2));
                paint.setColor(Color.WHITE);
                paint.setTextSize(50);
                if (x < canvas.getWidth()/2) {
                    canvas.drawText(s.getName(), x + radius * 2, y, paint);
                } else {
                    canvas.drawText(s.getName(), x - s.getName().length() * 30, y, paint);
                }
                paint.setColor(Color.YELLOW);
                canvas.drawCircle(x,y,radius, paint);
            }
            paint.setStyle(Paint.Style.STROKE);
            Coordinate c = game.getCurrentSolarSystemCoordinate();
            int x = (int)((double) c.getX() / Coordinate.MAX_X * canvas.getWidth());
            int y = (int)((double) c.getY() / Coordinate.MAX_Y * canvas.getHeight());
            paint.setColor(Color.WHITE);
            canvas.drawCircle(x,y,400, paint);
        }

        @Override
        public void setAlpha(int alpha) {
            this.alpha = alpha;
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            this.colorFilter = colorFilter;
        }

        @Override
        public int getOpacity() {
            final int i = 0;
            return i;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Map Setting Buttons
    ///////////////////////////////////////////////////////////////////////////////////////

    public void onClickViewSolarSystem(View v) {
        mapView.setBackground(new Map(0));
    }

    public void onClickViewUniverse(View v) {
        mapView.setBackground(new Map(1));
    }

    public void onClickBackTravel(View v) {
        finish();
    }

    public void onClickTravelVerified(View v) {
        game.travel(selectedSolarSystem, selectedPlanet);
    }
}
