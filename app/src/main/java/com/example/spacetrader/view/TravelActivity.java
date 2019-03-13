package com.example.spacetrader.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.graphics.Paint;
import android.graphics.drawable.*;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Coordinate;
import com.example.spacetrader.entity.Game;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.SolarSystem;

import java.util.List;

public class TravelActivity extends AppCompatActivity {

    private View mapView;
    private Game game;
    private Map map;
    private TextView planetTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        game = Game.getInstance();
        mapView = findViewById(R.id.mapView);
        map = new Map(0);
        mapView.setBackground(map);
        planetTextView = findViewById(R.id.location_text);
        planetTextView.setText("Planet: " + game.getCurrentPlanetName());
    }

    public class Map extends Drawable {

        private int alpha;
        private ColorFilter colorFilter;
        private int mapType;

        public Map (int mapType) {
            this.mapType = mapType;
        }

        @Override
        public void draw(Canvas canvas) { ;
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
            Planet[] planets = game.getCurrentSolarSystemPlanets();
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawPaint(paint);
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(canvas.getWidth()/2,canvas.getWidth()/2,25, paint);
            int icd = canvas.getWidth()/2 / (planets.length + 1); //inner circle distance
            for (int i = 0; i < planets.length; i++) {
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.WHITE);
                canvas.drawCircle(canvas.getWidth()/2,canvas.getWidth()/2,icd * (i+1), paint);
                double period = 300 * Math.sqrt(Math.pow(i+1,3)) / Math.sqrt(8);
                double angle = ((System.currentTimeMillis()/100)%period)/period * 6.283;
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(planets[i].getColor());
                canvas.drawCircle(canvas.getWidth()/2 + (int)(icd * (i + 1) * Math.cos(angle)),canvas.getWidth()/2 +(int)(icd * (i + 1) * Math.sin(angle)),15, paint);
                paint.setColor(Color.WHITE);
                paint.setTextSize(50);
                canvas.drawText(planets[i].getName(), canvas.getWidth()/2 + (int)(icd * (i + 1) * Math.cos(angle)) + 10, canvas.getWidth()/2 +(int)(icd * (i + 1) * Math.sin(angle) - 30), paint);
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

    public void onClickBackTravel(View vi) {
        finish();
    }
}
