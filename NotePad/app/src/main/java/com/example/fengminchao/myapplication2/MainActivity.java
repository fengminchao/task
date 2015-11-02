package com.example.fengminchao.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jikexueyuan.hellonotes.R;

public class MainActivity extends Activity{

	private Button add;
	private ListView lv;
	private Intent i;
	private com.example.fengminchao.myapplication2.MyAdapter adapter;
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	public void initView() {

		lv = (ListView) findViewById(R.id.list);
		add = (Button) findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					i = new Intent(MainActivity.this, AddContent.class);
							startActivity(i);
			}
		});

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(MainActivity.this, SelectAct.class);
                View chosen_view = lv.getChildAt(position);
				TextView list_content =(TextView) chosen_view.findViewById(R.id.list_content);
				String content = list_content.getText().toString();
				TextView list_time =(TextView) chosen_view.findViewById(R.id.list_time);
				String time = list_time.getText().toString();
				i.putExtra(NotesDB.CONTENT,content);
				i.putExtra(NotesDB.TIME,time);
				startActivity(i);
			}
		});
	}

	public void selectDB() {
		SQLiteDatabase dbReader = NotesDB.getInstance(MainActivity.this);
		cursor = dbReader.query(NotesDB.TABLE_NAME, null, null, null, null,
				null, null);
		adapter = new MyAdapter(this, cursor);
		lv.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		selectDB();
	}

}
