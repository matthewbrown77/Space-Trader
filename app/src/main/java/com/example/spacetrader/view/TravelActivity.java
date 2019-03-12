package com.example.spacetrader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.graphics.Paint;
import android.graphics.drawable.*;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Coordinate;
import com.example.spacetrader.entity.Game;
import com.example.spacetrader.entity.SolarSystem;

import java.util.List;

public class TravelActivity extends AppCompatActivity {

    private View map;
    private Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        game = Game.getInstance();
        map = findViewById(R.id.mapView);
        map.setBackground(new Map());
    }

    public class Map extends Drawable {

        private int alpha;
        private ColorFilter colorFilter;

        @Override
        public void draw(Canvas canvas) {
            int radius = 10;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawPaint(paint);
            List<SolarSystem> solarSystemList = game.getSolarSystems();
            for (SolarSystem s: solarSystemList) {
                int x = (int)((double) s.getCoordinate().getX() / Coordinate.MAX_X * canvas.getWidth());
                int y = (int)((double) s.getCoordinate().getY() / Coordinate.MAX_Y * canvas.getHeight());
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
}
