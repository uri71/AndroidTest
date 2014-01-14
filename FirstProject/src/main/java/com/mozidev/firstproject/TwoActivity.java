package com.mozidev.firstproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TwoActivity extends Activity implements View.OnClickListener {

    Button btnPress;
    EditText etTop;
    EditText etBot;
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        btnPress = (Button) findViewById(R.id.btnPress);
        btnPress.setOnClickListener(this);
        etTop = (EditText) findViewById(R.id.etTop);
        etBot = (EditText) findViewById(R.id.etBot);
        tv = (TextView) findViewById(R.id.tv);



    }

    @Override
    public void onClick(View v) {
        String str = "";
        str = etTop.getText().toString()+etBot.getText().toString();
        if (str.equals("")) {
            Toast.makeText(this, "Введите текст", Toast.LENGTH_LONG).show();

        }
        else tv.setText(str);
        }
    }


