package com.mozidev.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

public class MyTabActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tab);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MyTabFragment())
                    .commit();
        }
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class MyTabFragment extends Fragment {

        public MyTabFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my_tab, container, false);

            TabHost tabHost;
            tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
            tabHost.setup();

            TabHost.TabSpec tabSpec = tabHost.newTabSpec("1");
            tabSpec.setIndicator("List of the Countries 1");
            tabSpec.setContent(new Intent(getActivity(),CountriesActivity.class));
            tabHost.addTab(tabSpec);

            tabSpec = tabHost.newTabSpec("2");
            tabSpec.setIndicator("List of the Countries 2");
            tabSpec.setContent(new Intent(getActivity(),LocalesActivity.class));
            tabHost.addTab(tabSpec);

            tabHost.setCurrentTabByTag("1");

            return rootView;
        }
    }

}
