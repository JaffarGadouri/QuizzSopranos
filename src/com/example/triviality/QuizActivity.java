package com.example.triviality;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	List<Question> quesList;
	int score = 0;
	int qid = 0;
	Question currentQ;
	TextView txtQuestion;
	RadioButton rda, rdb, rdc;
	Button butNext;
	MediaPlayer yourStereo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		DbHelper db = new DbHelper(this);
		quesList = db.getAllQuestions();
		Collections.shuffle(quesList);
		currentQ = quesList.get(qid);
		txtQuestion = (TextView) findViewById(R.id.textView1);
		rda = (RadioButton) findViewById(R.id.radio0);
		rdb = (RadioButton) findViewById(R.id.radio1);
		rdc = (RadioButton) findViewById(R.id.radio2);
		butNext = (Button) findViewById(R.id.button1);
		setQuestionView();

		butNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
				RadioButton answer = (RadioButton) findViewById(grp
						.getCheckedRadioButtonId());
				Log.d("yourans", currentQ.getANSWER() + " " + answer.getText());
				if (currentQ.getANSWER().equals(answer.getText())) {
					score++;
					Log.d("score", "Your score" + score);
					Toast.makeText(getApplicationContext(), "Well done !",
							Toast.LENGTH_SHORT).show();
				} else if (!currentQ.getANSWER().equals(answer.getText())) {
					Toast.makeText(getApplicationContext(), "You're wrong !",
							Toast.LENGTH_SHORT).show();
				}
				if (qid < 10) {
					currentQ = quesList.get(qid);
					setQuestionView();
				} else {
					Intent intent = new Intent(QuizActivity.this,
							ResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("score", score); // Your score
					intent.putExtras(b); // Put your score to your next Intent
					startActivity(intent);
					finish();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_quiz, menu);
		return true;
	}

	private void setQuestionView() {
		txtQuestion.setText(currentQ.getQUESTION());
		rda.setText(currentQ.getOPTA());
		rdb.setText(currentQ.getOPTB());
		rdc.setText(currentQ.getOPTC());
		// animation des boutons
		TranslateAnimation anim = new TranslateAnimation(400, 0, 0, 0);
		anim.setStartOffset(320);
		anim.setFillAfter(true);
		anim.setDuration(500);
		txtQuestion.startAnimation(anim);
		rda.startAnimation(anim);
		rdb.startAnimation(anim);
		rdc.startAnimation(anim);
		qid++;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		yourStereo.stop();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		yourStereo = MediaPlayer.create(this, R.raw.theme);
		yourStereo.start();
	}

}
