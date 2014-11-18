package com.example.triviality;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;

public class SopranosQuizz extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TranslateAnimation anim = new TranslateAnimation(0, 0, 300, 0);
		anim.setStartOffset(320);
		anim.setFillAfter(true);
		anim.setDuration(500);
		this.findViewById(R.id.play).startAnimation(anim);
		this.findViewById(R.id.score).startAnimation(anim);
		this.findViewById(R.id.options).startAnimation(anim);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.play:
			Intent intent = new Intent(this,
					com.example.triviality.QuizActivity.class);
			startActivity(intent);
			break;
		case R.id.options:
			Intent intent2 = new Intent(this,
					com.example.triviality.OptionsActivity.class);
			startActivity(intent2);
			break;
		case R.id.score:
			Intent intent3 = new Intent(this,
					com.example.triviality.ScoreActivity.class);
			startActivity(intent3);
			break;
		}

	}

}
