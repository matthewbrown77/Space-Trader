package com.example.spacetrader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

	private TextView mTextMessage;

    Button okButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextMessage = (TextView) findViewById(R.id.message);

		okButton = findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e("main", "This worked");
			}
		});


	}

}
