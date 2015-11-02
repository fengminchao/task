package com.example.fengminchao.myapplication2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDB extends SQLiteOpenHelper {

	public static final String TABLE_NAME = " notes ";
	public static final String CONTENT = " content ";
	public static final String ID = " _id ";
	public static final String TIME = " time ";
	public static NotesDB helper = null;

	public NotesDB(Context context1) {
		super(context1, "notes", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + CONTENT + "TEXT NOT NULL,"
				+ TIME + " TEXT NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	public static SQLiteDatabase getInstance(Context context){
		if (helper==null){
			helper = new NotesDB(context);
		}
		return helper.getReadableDatabase();
	}

}
