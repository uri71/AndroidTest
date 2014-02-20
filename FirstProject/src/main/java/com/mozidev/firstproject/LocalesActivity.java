package com.mozidev.firstproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        NewAcyncTask task;
        CountresAdapter adapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_locales, container, false);
            return rootView;

        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            task= new NewAcyncTask();
            task.execute();

        }

        class NewAcyncTask extends AsyncTask<Void, Void, Void> {

            ArrayList<Item> displayCountries;
            ProgressDialog progressDialog;

                                                                                /* protected NewTask (){
                                                                                 };*/
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(getActivity(), null, "Please, wait..", true, false);

                /*progressDialog =  new ProgressDialog (getActivity());
                progressDialog.setTitle("Please, wait...");
                progressDialog.show();*/
            }

            @Override
            protected Void doInBackground(Void... params) {

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

                displayCountries = new ArrayList<Item>();

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

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                adapter = new CountresAdapter(getActivity(),
                        R.layout.item_long_list, displayCountries);
                setListAdapter(adapter);

                progressDialog.dismiss();
                progressDialog = null;

            }
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);

            Intent intent = new Intent(getActivity(), DisplayLocalesActivity.class);

            String str = getString(R.string.lorem_ipsum);

            LocalesActivity.Item item = (LocalesActivity.Item) getListAdapter().getItem(position);

            Integer imgRes = item.getImg();
            String country = item.getName();

            intent.putExtra(EXTRAS_COUNTRY, country);
            intent.putExtra(EXTRAS_TEXT, str);
            intent.putExtra(EXTRAS_IMAGE_RESOURSE, imgRes);

            startActivity(intent);
        }

        AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
            private Item selectedItem;

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = (Item)adapterView.getItemAtPosition(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setMessage("Delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick (DialogInterface dialog, int wich){
                    adapter.remove(selectedItem);
                    adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick (DialogInterface dialog, int wich){
                        dialog.cancel();
                    }
                });

                builder.show();

                return false;
            }
        };
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


