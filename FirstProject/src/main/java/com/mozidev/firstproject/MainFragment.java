package com.mozidev.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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

        Button btnTop, btnBot, countries, longList, locales, btn_locales;

        btnTop = (Button) view.findViewById(R.id.btnTop);
        btnTop.setOnClickListener(this);

        btnBot = (Button) view.findViewById(R.id.btnBot);
        btnBot.setOnClickListener(this);

        countries = (Button) view.findViewById(R.id.countries);
        countries.setOnClickListener(this);

        longList = (Button) view.findViewById(R.id.longList);
        longList.setOnClickListener(this);

        locales = (Button) view.findViewById(R.id.locales);
        locales.setOnClickListener(this);

        LinearLayout llMFrag = (LinearLayout) view.findViewById(R.id.llMFrag);

        btn_locales = new Button(getActivity());
        btn_locales.setLayoutParams(new ViewGroup.LayoutParams(-1,-2));

        btn_locales.setText("LOCAL");
        btn_locales.setOnClickListener(this);
        btn_locales.setId(1);
        btn_locales.setBackgroundColor(Color.GRAY);
        btn_locales.setPadding(0, 25, 25, 0);

        llMFrag.addView(btn_locales);

    }

    @Override
    public void onClick(View v) {

        Class<? extends Activity> target;
        switch (v.getId()) {
            case R.id.btnTop:
                target = SecondActivity.class;
                break;

            case R.id.countries:
                target = MyTabActivity.class;
                break;

            case R.id.longList:
                target = LongListActivity.class;
                break;

            case R.id.locales:
                target = TestView.class;
                break;

            case 1:
                target = LocalesActivity.class;
                break;

            default:
                target = WebActivity.class;
                break;
        }

        Intent intent = new Intent(getActivity(), target);
        getActivity().startActivity(intent);
    }

}

