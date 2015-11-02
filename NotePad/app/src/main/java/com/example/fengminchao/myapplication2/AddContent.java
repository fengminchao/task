package com.example.fengminchao.myapplication2;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.jikexueyuan.hellonotes.R;

public class AddContent extends Activity implements OnClickListener{

	private Button save, back;
	private EditText ettext;
	private com.example.fengminchao.myapplication2.NotesDB notesDB;
	private SQLiteDatabase dbWriter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontent);
		save = (Button) findViewById(R.id.save);
		back = (Button) findViewById(R.id.back);
		ettext = (EditText) findViewById(R.id.ettext);
		save.setOnClickListener(this);
		back.setOnClickListener(this);
		dbWriter = NotesDB.getInstance(AddContent.this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save:
			addDB();
			finish();
			break;

		case R.id.back:
			finish();
			break;
		}
	}

	public void addDB() {
		ContentValues cv = new ContentValues();
		cv.put(NotesDB.CONTENT, ettext.getText().toString());
		cv.put(NotesDB.TIME, getTime());
		dbWriter.insert(NotesDB.TABLE_NAME,null,cv);
	    }


	private String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date curDate = new Date();
		return format.format(curDate);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		}

}
