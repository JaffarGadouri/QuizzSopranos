package com.example.triviality;

import com.example.triviality.R.id;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity implements OnClickListener{
	
	EditText NICK;
	Button SUBMIT;
	String nickname;
	Context ctx = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		// get rating bar object
//		RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);
//		bar.setNumStars(5);
//		bar.setStepSize(0.5f);
		// get text view
		TextView t2 = (TextView) findViewById(R.id.textResult2);
		ImageView img = (ImageView) findViewById(id.imgscore);
		// get score
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		// display score
//		bar.setRating(score);
		switch (score) {
		case 0:
		case 1:
		case 2:
			t2.setText("Your score : "+score+"/10");
			img.setImageResource(R.drawable.sop2);
			break;
		case 3:
		case 4:
			t2.setText("Your score : "+score+"/10");
			img.setImageResource(R.drawable.sop4);
			break;
		case 5:
		case 6:
			t2.setText("Your score : "+score+"/10");
			img.setImageResource(R.drawable.sop6);
			break;
		case 7:
		case 8:	
			t2.setText("Your score : "+score+"/10");
			img.setImageResource(R.drawable.sop8);
			break;
		case 9:	
			t2.setText("Your score : "+score+"/10");
			img.setImageResource(R.drawable.sop9);
			break;
		case 10:	
			t2.setText("Your score : "+score+"/10");
			img.setImageResource(R.drawable.sop10);
			break;
		}
		Button menuBouton = (Button) findViewById(R.id.backbttn);
        menuBouton.setOnClickListener(this);
        
		NICK = (EditText) findViewById(R.id.nickname);
		SUBMIT = (Button) findViewById(R.id.submit);
		SUBMIT.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nickname = NICK.getText().toString();
				Bundle b = getIntent().getExtras();
				int score = b.getInt("score");
				DbHelper dbh = new DbHelper(ctx);
				dbh.submitScore(dbh, nickname, score);
				Toast.makeText(getBaseContext(), "Score saved !", Toast.LENGTH_LONG).show();
				finish();
			}
		});
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_result, menu);
//		return true;
//	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, com.example.triviality.SopranosQuizz.class);
		
		startActivity(intent);
	}
	

}
