package com.example.administrator.calculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button c;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btndot, add, reduce, mul, divde, btnequ;
    TextView textView;
    int state, way;
    String s = "";
    double sum;
    double a, b;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) findViewById(R.id.textView);
        btn0 = (Button) findViewById(R.id.btn1);
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
        btn1 = (Button) findViewById(R.id.btn1);
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
//            s = textView.getText().toString();
            Button btn = (Button) v;
//            String m = "";

                if (btn.getId() == R.id.btn0 || btn.getId() == R.id.btn1 || btn.getId() == R.id.btn2 || btn.getId() == R.id.btn3 || btn.getId() == R.id.btn4 || btn.getId() == R.id.btn5 || btn.getId() == R.id.btn6 || btn.getId() == R.id.btn7 || btn.getId() == R.id.btn8 || btn.getId() == R.id.btn9 || btn.getId() == R.id.btndot) {
                    s += btn.getText();
                    textView.setText(s);
                    if (way == 0) {
                        a = Double.valueOf(s);
                    }
                    if (way != 0) {
                        b = Double.valueOf(s);
                    }
                }

            if (btn.getId() == R.id.add || btn.getId() == R.id.reduce || btn.getId() == R.id.mul || btn.getId() == R.id.divide) {
                state = 1;
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
                }
                s = "";
            }
            if (btn.getId() == R.id.btnequ) {
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
                        sum = a / b;
                        break;
                }
                s = "" + sum;
                textView.setText(s);
            }
            if (btn.getId() == R.id.c) {
                way = 0;
                s = "";
                textView.setText(s);
                state = 0;
            }
        }
    };
}
