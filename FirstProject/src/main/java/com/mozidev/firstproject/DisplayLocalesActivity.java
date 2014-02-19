package com.mozidev.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
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
        String text = intent.getStringExtra("EXTRAS_TEXT");
        String country = intent.getStringExtra("EXTRAS_COUNTRY");

        Integer imgRes = intent.getIntExtra("EXTRAS_IMAGE_RESOURSE", android.R.drawable.ic_delete);

        TextView tv_country_info = (TextView) findViewById(R.id.tv_country_info);
        TextView tv_country_name = (TextView) findViewById(R.id.tv_country_name);
        ImageView img = (ImageView) findViewById(R.id.ivImageRes);

        tv_country_name.setText(country);
        tv_country_info.setText(text);
        img.setImageResource(imgRes);
    }
}


