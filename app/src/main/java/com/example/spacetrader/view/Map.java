package com.example.spacetrader.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.spacetrader.entity.Coordinate;
import com.example.spacetrader.entity.Game;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.SolarSystem;

import java.util.List;

/**
 * Map class draws the map of the planet, solar system, observable universe, and total universe
 */
public class Map extends Drawable {

    private int alpha;
    private ColorFilter colorFilter;
    private int mapType; //0 = planet, 1 = solar system, 2 = observable universe, 3 = total universe
    public final int totalMapTypes = 4; //total number of maps. Used to adjust buttons in travel activity
    private Game game;

    public Map (int mapType) {
        this.mapType = mapType;
        this.game = Game.getInstance();
    }

    /**
     * Draws the appropriate map on the canvas
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        switch(mapType) {
            case 0: {
                drawPlanet(canvas);
            } break;
            case 1: {
                drawSolarSystem(canvas);
            } break;
            case 2: {
                drawObservableUniverse(canvas);
            } break;
        }
    }

    /**
     * Draws the planet on the canvas
     * @param canvas
     */
    private void drawPlanet(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(canvas.getWidth()/2,canvas.getWidth()/2,canvas.getWidth()/3, paint);
    }

    /**
     * Draws the solarSystem on the canvas
     * @param canvas
     */
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

    /**
     * Draws the observable universe on the canvas
     * @param canvas
     */
    private void drawObservableUniverse(Canvas canvas) {
        int radius = 10;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        List<SolarSystem> solarSystemList = game.getSolarSystems();
        for (SolarSystem s: solarSystemList) {
            int x = (int)((double) s.getCoordinate().getX() / Coordinate.MAX_X * canvas.getWidth());
            int y = (int)((double) s.getCoordinate().getY() / Coordinate.MAX_Y * canvas.getHeight());
            if (game.solarSystemInRange(s)) {
                paint.setColor(Color.WHITE);
                paint.setTextSize(50);
                canvas.drawText(s.getName(), x + radius * 2, y, paint);
                paint.setColor(Color.GREEN);
            } else {
                paint.setColor(Color.RED);
            }
            canvas.drawCircle(x,y,radius, paint);
        }
        paint.setStyle(Paint.Style.STROKE);
        Coordinate c = game.getCurrentSolarSystemCoordinate();
        int x = (int)((double) c.getX() / Coordinate.MAX_X * canvas.getWidth());
        int y = (int)((double) c.getY() / Coordinate.MAX_Y * canvas.getHeight());
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x,y,(int)(game.getFuel() * 12.4), paint);
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