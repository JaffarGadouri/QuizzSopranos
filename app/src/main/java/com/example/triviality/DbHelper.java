package com.example.triviality;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "triviaQuiz";
	// tasks table name
	private static final String TABLE_QUEST = "quest";
	private static final String TABLE_SCORE = "score";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; // correct option
	private static final String KEY_OPTA = "opta"; // option a
	private static final String KEY_OPTB = "optb"; // option b
	private static final String KEY_OPTC = "optc"; // option c
	private static final String KEY_NICK = "nickname"; // NICKNAME
	private static final String KEY_SCORE = "score"; // SCORE
	private SQLiteDatabase dbase;
	private DbHelper dbh;

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase = db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
				+ KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";

		String sql2 = "CREATE TABLE IF NOT EXISTS " + TABLE_SCORE + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NICK
				+ " TEXT, " + KEY_SCORE + " INTEGER)";
		db.execSQL(sql);
		db.execSQL(sql2);
		addQuestions();
	}

	private void addQuestions() {
		Question q1 = new Question("Who created the TV show"
				+ " The Sopranos ?", "Martin Scorsese", "James Gandolfini",
				"David Chase", "David Chase");
		this.addQuestion(q1);
		Question q2 = new Question("Where the majority of the exterior"
				+ " scenes take place?", "New York", "New Jersey", "Sicilia",
				"New Jersey");
		this.addQuestion(q2);
		Question q3 = new Question("Who played Dr.Melfi ?", "Lorraine Bracco",
				"Nancy Marchand", "Edie Falco", "Lorraine Bracco");
		this.addQuestion(q3);
		Question q4 = new Question("How many seasons" + " do we got?", "6",
				"4", "8", "6");
		this.addQuestion(q4);
		Question q5 = new Question("When was the HBO premiere"
				+ " of The Sopranos?", "January 30, 2000", "January 20, 1998",
				"January 10, 1999", "January 10, 1999");
		this.addQuestion(q5);
		Question q6 = new Question("What is the name of"
				+ " Artie's restaurant?", "Vesuvio", "Sicilia", "Pompeii",
				"Vesuvio");
		this.addQuestion(q6);
		Question q7 = new Question("What is the real name"
				+ " of 'Big Pussy' ?", "Eugene Pontecorvo",
				"Robert Baccalieri, Jr", "Salvatore Bonpensiero",
				"Salvatore Bonpensiero");
		this.addQuestion(q7);
		Question q8 = new Question("Who is Tony Blundetto"
				+ " played by Steve Buscemi ?", "Tony Soprano's brother",
				"Tony Soprano's cousin ", "Tony Soprano's boss",
				"Tony Soprano's cousin ");
		this.addQuestion(q8);
		Question q9 = new Question("What's the favorite movie of"
				+ " Ralphie Cifaretto ?", "Gladiator", "Alien", "Blade Runner",
				"Gladiator");
		this.addQuestion(q9);
		Question q10 = new Question("Who killed Christopher Moltisanti,"
				+ " after a car accident ?", "Adriana La Cerva",
				"Tony Soprano", "Phil Leotardo", "Tony Soprano");
		this.addQuestion(q10);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}

	// Adding new question
	public void addQuestion(Question quest) {
		// SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION());
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);
	}

	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase = this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}

	public int rowcount() {
		int row = 0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row = cursor.getCount();
		return row;
	}

	public int getCount() {
		String countQuery = "SELECT * FROM " + TABLE_SCORE;
		Cursor cursor = dbase.rawQuery(countQuery, null);
		int nb = cursor.getCount();
		cursor.close();
		return nb;
	}

	public void submitScore(DbHelper dbh, String nickname, int score) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DbHelper.KEY_NICK, nickname);
		cv.put(DbHelper.KEY_SCORE, score);
		db.insert(DbHelper.TABLE_SCORE, null, cv);
	}

	public List<String> getScores() {
		dbase = this.getReadableDatabase();
		List<String> highscores = new ArrayList<String>();
		String selectQuery = "SELECT * FROM " + DbHelper.TABLE_SCORE
				+ " ORDER BY " + DbHelper.KEY_SCORE + " DESC";
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			highscores.add(cursor.getString(1) + " : " + cursor.getString(2)
					+ " pts");
			cursor.moveToNext();
		}
		cursor.close();
		return highscores;
	}

	public void open() throws SQLException {
		dbase = this.getWritableDatabase();
	}

	public void close() {
		dbh.close();
	}
}
