package com.mozidev.firstproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by y.storchak on 1/10/14.
 */
public class PlaceholderFragment extends Fragment implements View.OnClickListener {

    TextView tv;
    Button btn;
    EditText etTop;
    EditText etBot;

    public PlaceholderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etTop = (EditText) view.findViewById(R.id.editTextTop);
        etBot = (EditText) view.findViewById(R.id.editTextBottom);
        tv = (TextView) view.findViewById(R.id.TextViewFM);

        btn = (Button) view.findViewById(R.id.buttonFM);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        SimpleDateFormat sdt = new SimpleDateFormat("HH:mm:ss");
        String time = sdt.format(new Date(System.currentTimeMillis()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(new Date(System.currentTimeMillis()));

        tv.setText(etTop.getText().toString()+etBot.getText().toString()+date+"\n"+time);
    }
}
