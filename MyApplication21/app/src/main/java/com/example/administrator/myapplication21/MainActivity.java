package com.example.administrator.myapplication21;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {
    private Button search;
    private EditText edt;

List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
    SimpleAdapter simpleAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt = (EditText) findViewById(R.id.edt);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注意每次需new一个实例,新建的任务只能执
                // 行一次,否则会出现异常
                MyTask myTask = new MyTask();
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("https://api.douban.com/v2/user?q=").append(URLEncoder.encode(edt.getText().toString(),"utf-8"));
                    myTask.execute(new URL(stringBuilder.toString()));
                    //System.out.println(edt.getText());
                    Toast.makeText(MainActivity.this,stringBuilder,Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                execute.setEnabled(false);
//                path = "https://api.douban.com/v2/user?q=" + edt.getText().toString();
            }
        });


    }

    class MyTask extends AsyncTask<URL, Void , String > {


        @Override
        protected String doInBackground(URL... params) {
            StringBuilder sb = new StringBuilder();
            try {
                HttpURLConnection conn = (HttpURLConnection) params[0].openConnection();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8")
                );
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                super.onPostExecute(result);

                JSONObject jsonObj = new JSONObject(result);
                JSONArray jsonArr = jsonObj.getJSONArray("users");
                for (int i = 0; i < jsonArr.length(); i++) {
                    Map<String,Object> list = new HashMap<String,Object>();
                    list.put("name", "姓名:" + jsonArr.getJSONObject(i).getString("name"));
                    list.put("id", "ID:" + jsonArr.getJSONObject(i).getString("id"));
//                    list.put("loc_name", "现居地：" + jsonArr.getJSONObject(i).getString("loc_name"));
                    list.put("created", "创建时间：" + jsonArr.getJSONObject(i).getString("created"));
                    list.put("desc", "简介：" + jsonArr.getJSONObject(i).getString("desc"));
                    lists.add(list);
                }
                simpleAdapter = new SimpleAdapter(MainActivity.this,lists,R.layout.list_item,
                        new String[] {"name","id","created","desc"},new int[]{R.id.name,R.id.id,R.id.created,R.id.desc});
                ListView lv = (ListView)findViewById(R.id.lv);
                lv.setAdapter(simpleAdapter);
                simpleAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
}

