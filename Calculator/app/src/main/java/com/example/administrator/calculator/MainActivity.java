package com.example.administrator.calculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button c;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btndot, add, reduce, mul, divde, btnequ;
    TextView textView;
    int way;
    double sum;
    String m;
    String s = "";
    int pointnum = 0;
    double a, b;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) findViewById(R.id.textView);
        preferences =getSharedPreferences("fengminchao",MODE_PRIVATE);
        editor = preferences.edit();
        float value = preferences.getFloat("value",0);
        textView.setText(""+value);
        if (value!=0) {
            s = ""+value;
            a = Double.valueOf(s);
        }
        btn0 = (Button) findViewById(R.id.btn0);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn1 = (Button) findViewById(R.id.btn1);
        c = (Button) findViewById(R.id.c);
        btndot = (Button) findViewById(R.id.btndot);
        add = (Button) findViewById(R.id.add);
        reduce = (Button) findViewById(R.id.reduce);
        mul = (Button) findViewById(R.id.mul);
        divde = (Button) findViewById(R.id.divide);
        btnequ = (Button) findViewById(R.id.btnequ);
        btn0.setOnClickListener(l);
        btn1.setOnClickListener(l);
        btn2.setOnClickListener(l);
        btn3.setOnClickListener(l);
        btn4.setOnClickListener(l);
        btn5.setOnClickListener(l);
        btn6.setOnClickListener(l);
        btn7.setOnClickListener(l);
        btn8.setOnClickListener(l);
        btn9.setOnClickListener(l);
        add.setOnClickListener(l);
        reduce.setOnClickListener(l);
        mul.setOnClickListener(l);
        divde.setOnClickListener(l);
        btnequ.setOnClickListener(l);
        btndot.setOnClickListener(l);
        c.setOnClickListener(l);
    }

    OnClickListener l = new OnClickListener() {

        @Override
        public void onClick(View v) {
            textView = (TextView) findViewById(R.id.textView);
            Button btn = (Button) v;

//输入a或者b
            if (btn.getId() == R.id.btn0 || btn.getId() == R.id.btn1 || btn.getId() == R.id.btn2 ||
                    btn.getId() == R.id.btn3 || btn.getId() == R.id.btn4 || btn.getId() == R.id.btn5
                    || btn.getId() == R.id.btn6 || btn.getId() == R.id.btn7 || btn.getId() == R.id.btn8
                    || btn.getId() == R.id.btn9) {
                   if(s.length()<10)
                    s += btn.getText();
                else
                    Toast.makeText(getApplicationContext(), "输入已达上限", Toast.LENGTH_SHORT).show();
                textView.setText(s);
            }
//小数点输入检测
                if (btn.getId() == R.id.btndot) {
                    pointnum++;
                    if (pointnum > 1) {
                        Toast.makeText(getApplicationContext(), "输入有误：已有一个小数点", Toast.LENGTH_SHORT).show();
                        textView.setText(s);
                    } else if (s.length() < 10) {
                        if (s.length() == 0) s = "0.";
                        else
                            s += btn.getText();
                        textView.setText(s);
                    } else
                        Toast.makeText(getApplicationContext(), "输入已达上限", Toast.LENGTH_SHORT).show();
                    textView.setText(s);
                }
//判断输入的符号
                if (btn.getId() == R.id.add || btn.getId() == R.id.reduce || btn.getId() == R.id.mul
                        || btn.getId() == R.id.divide) {
                    if (s == null||s.equals("")) a = 0;
                    a = Double.valueOf(s);
                    s = "";
                    m = ""+btn.getText();
                    textView.setText(m);
                    pointnum = 0;
                    switch (btn.getId()) {
                        case R.id.add:
                            way = 1;
                            break;
                        case R.id.reduce:
                            way = 2;
                            break;
                        case R.id.mul:
                            way = 3;
                            break;
                        case R.id.divide:
                            way = 4;
                            break;
                        default:
                            break;
                    }

                }
                //点击等于号的时候输出结果
                if (btn.getId() == R.id.btnequ) {
                    if (s.equals("") || s == null) {
                        Toast.makeText(getApplicationContext(), "请输入b", Toast.LENGTH_SHORT).show();
                    } else {
                        if (way != 0) {
                            b = Double.valueOf(s);
                            switch (way) {
                                case 1:
                                    sum = a + b;
                                    break;
                                case 2:
                                    sum = a - b;
                                    break;
                                case 3:
                                    sum = a * b;
                                    break;
                                case 4:
                                    if (b == 0) {
                                        Toast.makeText(getApplicationContext(), "除数不能为零，请重新输入除数", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    sum = a / b;
                                    break;
                                default:
                                    break;
                            }
                            s = ""+sum;
                            a = sum;
                            editor.putFloat("value",(float)a);
                            editor.commit();
                            if (s.length() > 10 || a > 999)
                                Toast.makeText(getApplicationContext(), "结果溢出", Toast.LENGTH_SHORT).show();
                                textView.setText(s);
                        }
                    }
                }
                if (btn.getId() == R.id.c) {
                    way = 0;
                    s = "";
                    a = 0;
                    b = 0;
                    pointnum = 0;
                    textView.setText(s);
                    editor.clear();
                }
            }
        };
    }


