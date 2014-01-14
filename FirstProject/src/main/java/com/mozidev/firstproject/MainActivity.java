package com.mozidev.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity  extends Activity implements View.OnClickListener {
    Button btnTop;
    Button btnBot;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTop = (Button) findViewById(R.id.btnTop);
        Button btnBot = (Button) findViewById(R.id.btnBot);

        btnTop.setOnClickListener(this);
        btnBot.setOnClickListener(this);

           }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnTop:
                startActivity(new Intent(this, TwoActivity.class));
                break;
            case R.id.btnBot:
                startActivity(new Intent(this, WebActivity.class));
                break;
        }
    }
}

