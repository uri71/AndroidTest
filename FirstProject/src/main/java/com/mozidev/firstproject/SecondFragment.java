package com.mozidev.firstproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "SecondFragment";

    private EditText etLastName;
    private EditText etFirstName;
    private TextView tv;

    public SecondFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnPress = (Button) view.findViewById(R.id.btnPress);
        btnPress.setOnClickListener(this);

        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etLastName = (EditText) view.findViewById(R.id.etLastName);

        tv = (TextView) view.findViewById(R.id.tvFullName);
    }

    @Override
    public void onClick(View v) {
        String str = etFirstName.getText().toString()
                + " " + etLastName.getText().toString();

        if (str.length() == 0) {
            Log.e(TAG, "str.length() == 0");
            return;
        }

        tv.setText(getString(R.string.resultLabelDescription) + str);
    }
}