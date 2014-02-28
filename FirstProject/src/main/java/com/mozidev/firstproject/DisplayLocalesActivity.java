package com.mozidev.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by y.storchak on 2/17/14.
 */
public class DisplayLocalesActivity extends ActionBarActivity {
    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_locales);

        Intent intent = getIntent();
        String text = intent.getStringExtra("EXTRAS_TEXT");
        String country = intent.getStringExtra("EXTRAS_COUNTRY");

        Integer imgRes = intent.getIntExtra("EXTRAS_IMAGE_RESOURSE", android.R.drawable.ic_delete);

        EditText tv_country_info = (EditText) findViewById(R.id.tv_country_info);
        TextView tv_country_name = (TextView) findViewById(R.id.tv_country_name);
        ImageView img = (ImageView) findViewById(R.id.ivImageRes);

        tv_country_name.setText(country);
        tv_country_info.setText(text);
        img.setImageResource(imgRes);
        bar = getSupportActionBar();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String str;

        switch (menuItem.getItemId()) {
            case R.id.menu_save:
                str = (String) menuItem.getTitle();
                break;
            case R.id.menu_delete:
                str = (String) menuItem.getTitle();
                break;
            case R.id.selecta:
                str = (String) menuItem.getTitle();
                break;
            case R.id.selectb:
                str = (String) menuItem.getTitle();
                break;
            case R.id.selectc:
                str = (String) menuItem.getTitle();
                break;
            case android.R.id.home:
                Intent intent = new Intent(this, CountriesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }

        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        return true;
    }
}


