package com.mozidev.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;

/**
 * Created by y.storchak on 1/15/14.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    public MainFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnTop = (Button) view.findViewById(R.id.btnTop);
        btnTop.setOnClickListener(this);

        Button btnBot = (Button) view.findViewById(R.id.btnBot);
        btnBot.setOnClickListener(this);

        Button countries = (Button) view.findViewById(R.id.countries);
        countries.setOnClickListener(this);

        Button simpleAdapter = (Button) view.findViewById(R.id.simpleAdapter);
        simpleAdapter.setOnClickListener(this);

        Button longList = (Button) view.findViewById(R.id.longList);
        longList.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Class<? extends Activity> target;
        switch (v.getId()) {
            case R.id.btnTop:
                target = SecondActivity.class;
                break;

            case R.id.countries:
                target = CountriesActivity.class;
                break;

            case R.id.simpleAdapter:
                target = SimplAdapter.class;
                break;

            case R.id.longList:
                target = LongListActivity.class;
                break;

            default:
                target = WebActivity.class;
                break;

        }

            Intent intent = new Intent(getActivity(), target);
            getActivity().startActivity(intent);

    }

}
