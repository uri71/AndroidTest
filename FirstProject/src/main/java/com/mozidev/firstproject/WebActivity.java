package com.mozidev.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public  class WebActivity extends Activity implements View.OnClickListener {
    Button ok;
    EditText etScheme;
    String uri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(this);

        etScheme = (EditText)findViewById(R.id.etScheme);
        uri = etScheme.getText().toString();

    }

    @Override
    public void onClick(View v) {

        if (!(uri.startsWith("http://"))) {
            Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
        }
    }
}









