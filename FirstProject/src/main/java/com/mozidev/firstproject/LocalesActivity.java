package com.mozidev.firstproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalesActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locales);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LocalesFragment())
                    .commit();
        }
    }


    public class LocalesFragment extends ListFragment {

        private ArrayList<Item> displayCountries = new ArrayList<Item>();

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_locales, container, false);
            return rootView;

        }
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

             Locale[] locales = Locale.getAvailableLocales();

            for (Locale locale : locales) {

                String country = locale.getDisplayCountry();
                String code = locale.getCountry();
                Item item = new Item (country, code);

                if (country.trim().length() > 0 && !displayCountries.contains(item)) {

                    displayCountries.add(item);
                }
            }
            //Collections.sort(displayCountries); -  как можно отсортировать массив?

            CountresAdapter adapter = new CountresAdapter (getActivity(),
                    android.R.layout.simple_list_item_1, displayCountries);
            setListAdapter(adapter);

        }

        public void onListItemClick (android.widget.ListView l, View v, int position, long id){ // первый и последний параметры?

            Intent intent = new Intent (getActivity(), DisplayLocalesActivity.class);

            String nameCountry = displayCountries.get(position).getName();
            intent.putExtra ("LOCAL", nameCountry);
            startActivity(intent);

        }
    }

    class CountresAdapter extends ArrayAdapter {
        private LayoutInflater inflater;
        private List<Item> displayCountries;

        public CountresAdapter (Context context,  int resource, List<Item> displayCountries){
            super(context, resource, displayCountries);
            inflater = LayoutInflater.from(context);
            this.displayCountries = displayCountries; //   почему нельзя использовать в коде непосредственно переменную из параметров метода?

        }
        public View getView (int position, View convertView, ViewGroup parent){
            View v = convertView;
            Item item =  displayCountries.get(position);
            String dataCountry = item.getName() + "/n"+ item.getCode();

            if (v == null) {
                v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            TextView text1 = (TextView)v.findViewById(android.R.id.text1);

            text1.setText(dataCountry);

            return v;
        }


    }
    public class Item {
        private String country;
        private String code;

        public Item(String country, String code) {

            this.code = code;
            this.country = country;
        }

        public String getName() {
            return country;
        }

        public void setName(String name) {
            this.country = country;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

       /* public String toString() {
            String str = country+"/n"+code;
            return str;*/
        }


    }


