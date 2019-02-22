package com.example.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Game;
import com.example.spacetrader.entity.Player;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

	private TextView mTextMessage;
	private Game game;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_travel:
					mTextMessage.setText(R.string.title_travel);
					return true;
				case R.id.navigation_store:
					mTextMessage.setText(R.string.title_store);
					return true;
				case R.id.navigation_inventory:
					mTextMessage.setText(R.string.title_inventory);
					return true;
			}
			return false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Serializable player = getIntent().getSerializableExtra("Player");
		//Log.e("main", "Created Player " + player.toString());
		game = new Game((Player)player);

		mTextMessage = (TextView) findViewById(R.id.message);
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
	}



}
