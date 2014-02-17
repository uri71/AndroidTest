package com.mozidev.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by y.storchak on 2/17/14.
 */
public class DisplayLocalesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_locales);


        Intent intent = getIntent();
        String name = intent.getStringExtra("LOCAL");


        TextView tv_country_info = (TextView) findViewById(R.id.tv_country_info);
        tv_country_info.setText(name);
    }
}


