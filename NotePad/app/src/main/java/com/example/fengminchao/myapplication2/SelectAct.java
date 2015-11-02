package com.example.fengminchao.myapplication2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jikexueyuan.hellonotes.R;

public class SelectAct extends Activity implements OnClickListener {

	private Button s_delete, s_back;
	private TextView s_tv;
	private NotesDB notesDB;
	private SQLiteDatabase dbWriter;
	private  static String s;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select);
		s_delete = (Button) findViewById(R.id.s_delete);
		s_back = (Button) findViewById(R.id.s_back);
		s_tv = (TextView) findViewById(R.id.s_tv);
		notesDB = new NotesDB(this);
		s_back.setOnClickListener(this);
		s_delete.setOnClickListener(this);
		s = getIntent().getStringExtra((NotesDB.CONTENT));
		s_tv.setText(getIntent().getStringExtra(NotesDB.CONTENT));
		dbWriter = NotesDB.getInstance(SelectAct.this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.s_delete:
				deleteDate();
				finish();
				break;

			case R.id.s_back:
				finish();
				break;
		}
	}

	public void deleteDate() {
		dbWriter.delete(NotesDB.TABLE_NAME, NotesDB.CONTENT + "like ?" , new String[]{ s });
				finish();
	}
}
