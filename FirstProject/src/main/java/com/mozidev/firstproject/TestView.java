package com.mozidev.firstproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TestView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_spinner_fragment, new SpinnerFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SpinnerFragment extends Fragment implements View.OnClickListener {
        private Spinner spinner1, spinner2;
        private TextView tv_spinner_touch_item, tv_spinner_press_button;
        private Button btn_spinner1;

        public SpinnerFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_test_view, container, false);

            spinner1 = (Spinner) rootView.findViewById(R.id.spinner1);
            spinner2 = (Spinner) rootView.findViewById(R.id.spinner2);
            tv_spinner_touch_item = (TextView) rootView.findViewById(R.id.tv_spinner_touch_item);
            tv_spinner_press_button = (TextView) rootView.findViewById(R.id.tv_spinner_press_button);
            btn_spinner1 = (Button) rootView.findViewById(R.id.btn_spinner1);


            return rootView;
        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);



            ArrayAdapter<?> adapter =
                    ArrayAdapter.createFromResource(getActivity(), R.array.spinner1, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter);

            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String[] item = getResources().getStringArray(R.array.spinner1);
                    tv_spinner_touch_item.setText("Вы выбрали в spinner2  " + item[i]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            btn_spinner1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (spinner1.getSelectedItem() != null)
                tv_spinner_press_button.setText("Вы выбрали в spinner1  " + spinner1.getSelectedItem().toString());
        }
    }
}
