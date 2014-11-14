package com.example.triviality;

import java.util.List;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		// get rating bar object
		RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);
		bar.setNumStars(5);
		bar.setStepSize(0.5f);
		// get text view
		TextView t = (TextView) findViewById(R.id.textResult);
		// get score
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		// display score
		bar.setRating(score);
		switch (score) {
		case 1:
		case 2:
			t.setText("Oopsie! Better Luck Next Time! "+score);
			break;
		case 3:
		case 4:
			t.setText("Hmmmm.. Someone's been reading a lot of trivia "+score);
			break;
		case 5:
			t.setText("Who are you? A trivia wizard??? "+score);
			break;
		}
		Button menuBouton = (Button) findViewById(R.id.backbttn);
        menuBouton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, com.example.triviality.SopranosQuizz.class);
		
		startActivity(intent);
	}
	

}
