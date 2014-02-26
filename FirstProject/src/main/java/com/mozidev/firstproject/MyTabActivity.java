package com.mozidev.firstproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class MyTabActivity extends ActionBarActivity implements ActionBar.TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tab);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tabDiscover1 = mActionBar.newTab().setText("Countries 1").setTabListener(this);
        mActionBar.addTab(tabDiscover1);

        ActionBar.Tab tabDiscover2 = mActionBar.newTab().setText("Countries 2").setTabListener(this);
        mActionBar.addTab(tabDiscover2);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Fragment fragment;
        int position = tab.getPosition();
        switch (position) {
            case 0:
                fragment = new CountriesFragment();
                break;

            default:
                fragment = new SecondFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
