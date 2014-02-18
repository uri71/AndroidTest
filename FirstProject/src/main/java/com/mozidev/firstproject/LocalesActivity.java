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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class  LocalesActivity extends FragmentActivity {

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

            Item item;
            Integer [] imgRes = {android.R.drawable.star_big_on,
                                android.R.drawable.btn_minus,
                                android.R.drawable.btn_plus,
                                android.R.drawable.btn_radio};
            int i = 0;
            for (Locale locale : locales) {

                String country = locale.getDisplayCountry();
                String code = locale.getCountry();

                if (country.trim().length() > 0) {

                    if (i<=3){
                        item = new Item (country, code, imgRes[i]);
                    }
                    else {
                        item = new Item(country, code, imgRes[i%4]);
                    }

                    i++;

                    if (!displayCountries.contains(item)) {
                        displayCountries.add(item);
                    }
                }
            }

            CountresAdapter adapter = new CountresAdapter (getActivity(),
                    R.layout.item_long_list, displayCountries);
            setListAdapter(adapter);

        }

        public void onListItemClick (android.widget.ListView l, View v, int position, long id){ // первый и последний параметры?

            Intent intent = new Intent (getActivity(), DisplayLocalesActivity.class);

            String str = "Lorem ipsum dolor sit amet, consectetur adipisicing elit \n " +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";

            //String nameCountry = displayCountries.get(position).getName();
            Integer imgRes = displayCountries.get(position).getImg();

            intent.putExtra ("LOCAL_NAME", str);
            intent.putExtra("IMG", imgRes);

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
            Integer imgRes = item.getImg();

            String dataCountry = item.getName() + "\n"+ item.getCode();

            if (v == null) {
                v = inflater.inflate(R.layout.item_long_list, parent, false);
            }

            ImageView img = (ImageView)v.findViewById(R.id.ivItemLL);
            TextView text1 = (TextView)v.findViewById(R.id.tvItemLL2);

            img.setImageResource(imgRes);
            text1.setText(dataCountry);

            return v;
        }


    }
    public class Item {
        private String country;
        private String code;
        private int imgRes;

        public Item(String country, String code, int imgRes) {

            this.code = code;
            this.country = country;
            this.imgRes = imgRes;
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

        public int getImg() {
            return imgRes;
        }

        public void setImg(Integer imgRes) {
            this.imgRes = imgRes;
        }

    }

}


