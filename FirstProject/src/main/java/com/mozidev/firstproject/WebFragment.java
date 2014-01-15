package com.mozidev.firstproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by y.storchak on 1/15/14.
 */
public class WebFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "WebFragment";

    private Button ok;
    private EditText etScheme;

    public WebFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ok = (Button) view.findViewById(R.id.ok);
        ok.setOnClickListener(this);

        etScheme = (EditText) view.findViewById(R.id.etScheme);
    }

    @Override
    public void onClick(View v) {
        String uri = etScheme.getText().toString();

        if (!uri.startsWith("http://")) {
            Toast.makeText(getActivity(), "ERROR ADRESS", Toast.LENGTH_SHORT).show();

        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
        }
    }
}