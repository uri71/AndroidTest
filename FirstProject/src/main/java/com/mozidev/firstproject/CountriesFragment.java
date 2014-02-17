package com.mozidev.firstproject;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CountriesFragment extends ListFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Locale[] locales = Locale.getAvailableLocales();
        List<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);

        MyArAdapter<String> adapter = new MyArAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, countries);
        setListAdapter(adapter);
    }
}

    class MyArAdapter <String> extends ArrayAdapter {
        private LayoutInflater inflater;
        private List<String> countres;

    public MyArAdapter (Context context,  int resource, List<String> countres){
        super(context, resource, countres);
        inflater = LayoutInflater.from(context);
        this.countres = countres;

    }
    public View getView (int position, View convertView, ViewGroup parent){
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView text1 = (TextView)v.findViewById(android.R.id.text1);
        text1.setText(countres.get(position).toString());

        if (position%2==0 ){
        text1.setBackgroundColor(Color.BLUE);
        }
        else text1.setBackgroundColor(Color.GRAY);

    return v;
    }

}

