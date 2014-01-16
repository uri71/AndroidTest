package com.mozidev.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTop = (Button) findViewById(R.id.btnTop);
        Button btnBot = (Button) findViewById(R.id.btnBot);

        btnTop.setOnClickListener(this);
        btnBot.setOnClickListener(this);

    }

    public void onClick(View v) {
        Class<? extends Activity> target;
        switch (v.getId()) {
            case R.id.btnTop:
                target = SecondActivity.class;
                break;
            default:
                target = WebActivity.class;
                break;
        }

        Intent intent = new Intent(this, target);
        startActivity(intent);
    }
}

