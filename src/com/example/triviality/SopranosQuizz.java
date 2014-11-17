package com.example.triviality;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class SopranosQuizz extends Activity implements OnClickListener {

	BackgroundSound mBackgroundSound = new BackgroundSound();
	MediaPlayer zik;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBackgroundSound.execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

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

	public class BackgroundSound extends AsyncTask<Void, Void, Void> {

	    @Override
	    protected Void doInBackground(Void... params) {
	        zik = MediaPlayer.create(SopranosQuizz.this, R.raw.theme); 
	        zik.setLooping(true);
	        zik.setVolume(100,100); 
	        zik.start(); 
	        return null;
	    }
	}

	
	public void onPause() {
	    super.onPause();
	    mBackgroundSound.cancel(true);
	    
	}
	
	public void onDestroy() {
	    super.onDestroy();
	    mBackgroundSound.cancel(true);
	    
	}
	
	@Override
	protected void onStop() {
	    super.onStop();
	    mBackgroundSound.cancel(true);
	    
	}


}
