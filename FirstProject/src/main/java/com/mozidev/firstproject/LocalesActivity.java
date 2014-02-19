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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

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
        final String EXTRAS_COUNTRY ="EXTRAS_COUNTRY";
        final String EXTRAS_TEXT ="EXTRAS_TEXT";
        final String EXTRAS_IMAGE_RESOURSE = "EXTRAS_IMAGE_RESOURSE";
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_locales, container, false);
            return rootView;

        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);


            Locale[] locales = Locale.getAvailableLocales();


            List<Locale> localesList = Arrays.asList(locales);
            Collections.sort(localesList, new Comparator<Locale>() {
                @Override
                public int compare(Locale locale, Locale locale2) {
                    return locale.getDisplayName(Locale.ENGLISH).compareTo(locale2.getDisplayName(Locale.ENGLISH));
                }
            });


            Integer[] imgRes = {R.drawable.ukraine,
                    R.drawable.united_arab_emirates,
                    R.drawable.united_kingdom,
                    R.drawable.united_nations};
            int i = 0;

            ArrayList<Item> displayCountries = new ArrayList<Item>();
            for (Locale locale : localesList) {
                Item item;

                String country = locale.getDisplayName(Locale.ENGLISH);
                String code = "<No Code>";
                try {
                    code = locale.getDisplayCountry(Locale.ENGLISH);
                } catch (MissingResourceException e) {
                    e.printStackTrace();
                }
                if (i <= 3) {
                    item = new Item(country, code, imgRes[i]);
                } else {
                    item = new Item(country, code, imgRes[i % 4]);
                }

                i++;

                displayCountries.add(item);                                                                                //}
            }

            CountresAdapter adapter = new CountresAdapter(getActivity(),
                    R.layout.item_long_list, displayCountries);
            setListAdapter(adapter);

        }


        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);

            Intent intent = new Intent(getActivity(), DisplayLocalesActivity.class);

            String str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Cras tincidunt faucibus enim eu vestibulum. Pellentesque habitant morbi tristique " +
                    "senectus et netus et malesuada fames ac turpis egestas. Etiam lobortis in felis ullamcorper blandit. " +
                    "Quisque imperdiet tincidunt mi, ac faucibus purus blandit sed. Vestibulum ut lorem dignissim, " +
                    "placerat neque a, suscipit dui. Mauris porta dictum orci et feugiat. Etiam pulvinar elit sed vulputate egestas. " +
                    "Maecenas pharetra faucibus porta. Nam vitae massa diam. " +
                    "Nulla sagittis suscipit lectus, in aliquet sapien mollis quis. " +
                    "Mauris sodales, magna eu malesuada varius, odio massa hendrerit urna, eu viverra erat diam eu massa. " +
                    "Cras libero urna, hendrerit quis felis sit amet, faucibus mattis magna. " +
                    "Nulla facilisis vehicula suscipit. Etiam sollicitudin tristique feugiat. " +
                    "Sed nec orci nunc.";

            LocalesActivity.Item item = (LocalesActivity.Item) getListAdapter().getItem(position);

            Integer imgRes = item.getImg();
            String country = item.getName();

            intent.putExtra(EXTRAS_COUNTRY, country);
            intent.putExtra(EXTRAS_TEXT, str);
            intent.putExtra(EXTRAS_IMAGE_RESOURSE, imgRes);

            startActivity(intent);
        }
    }

    class CountresAdapter extends ArrayAdapter {
        private LayoutInflater inflater;
        private List<Item> displayCountries;
        private int resours;

        public CountresAdapter(Context context, int resource, List<Item> displayCountries) {
            super(context, resource, displayCountries);
            inflater = LayoutInflater.from(context);
            this.displayCountries = displayCountries;
            this.resours = resource;

        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            Item item = displayCountries.get(position);
            Integer imgRes = item.getImg();

            String dataCountry = item.getName() + "\n" + item.getCode();

            if (v == null) {
                v = inflater.inflate(resours, parent, false);
            }

            ImageView img = (ImageView) v.findViewById(R.id.ivItemLL);
            TextView text1 = (TextView) v.findViewById(R.id.tvItemLL2);

            img.setImageResource(imgRes);
            text1.setText(dataCountry);

            return v;
        }

        @Override
        public LocalesActivity.Item getItem(int position) {
            return displayCountries.get(position);
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


